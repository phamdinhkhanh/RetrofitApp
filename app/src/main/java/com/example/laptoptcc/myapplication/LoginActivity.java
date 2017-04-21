package com.example.laptoptcc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laptoptcc.myapplication.database.model.LoginCredentials;
import com.example.laptoptcc.myapplication.database.model.RegisterResponse;
import com.example.laptoptcc.myapplication.database.model.TaskManager;
import com.example.laptoptcc.myapplication.database.model.TaskResponse;
import com.example.laptoptcc.myapplication.networks.jsonmodel.LoginBodyJson;
import com.example.laptoptcc.myapplication.services.RegisterServices;
import com.example.laptoptcc.myapplication.setting.SharePref;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    private Button btLogin;
    private Button btRegister;

    private Button btGetTask;
    private Button btAddTask;
    private Button btDelete;

    private EditText edUsername;
    private EditText edPassword;

    private EditText etLocal_id;
    private EditText etColor;
    private EditText etDone;
    private EditText etName;

    private String username;
    private String password;
    private String accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btLogin = (Button) findViewById(R.id.login);
        btRegister = (Button) findViewById(R.id.register);

        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);

        btGetTask = (Button) findViewById(R.id.btGetTask);
        btAddTask = (Button) findViewById(R.id.btAddTask);
        btDelete = (Button) findViewById(R.id.btDelete);

        etLocal_id = (EditText) findViewById(R.id.etLocal_id);
        etColor = (EditText) findViewById(R.id.etColor);
        etDone = (EditText) findViewById(R.id.etDone);
        etName = (EditText) findViewById(R.id.etName);



        SharePref.init(this);
        setupUI();
    }

    private void setupUI() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempLogin();
            }
        });

        btGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskManager.instance.getTaskFromServer();
            }
        });

        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskResponse newTask = new TaskResponse(
                        etName.getText().toString(),
                        etColor.getText().toString(),
                        etDone.getText().toString(),
                        etLocal_id.getText().toString()
                );

                TaskManager.instance.addNewTask(newTask);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String localID = etLocal_id.getText().toString();
                TaskManager.instance.deletask(localID);
            }
        });
    }


    private void register() {
        username = edUsername.getText().toString();
        password = edPassword.getText().toString();
        Log.d(TAG,String.format("username: %s; password: %s",username,password));
        sendRegister(username,password);
    }

    private void sendRegister(String username, String password){
        //1. Create services
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://retrofitserver.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        RegisterServices registerServices = retrofit.create(RegisterServices.class);

        //2. Create requestbody
        MediaType mediaType = MediaType.parse("application/json");
        String registerJson = new Gson().toJson(new LoginBodyJson(username,password));
        RequestBody requestBody = RequestBody.create(mediaType,registerJson);


        //3. Call request
        Call<RegisterResponse> registerResponseCall = registerServices.register(requestBody);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    Log.d(TAG,String.format("Register success, %s",response.body().getAccesstoken()));
                    makeText(LoginActivity.this, "register success", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 400) {
                    Log.d(TAG,"Failure to register");
                    makeText(LoginActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d(TAG,"Failure to register");
                makeText(LoginActivity.this, "Failure to register", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attempLogin() {
        username = edUsername.getText().toString();
        password = edPassword.getText().toString();
        Log.d(TAG,String.format("username: %s; password: %s",username,password));
        sendLogin(username,password);
    }

    private void sendLogin(String username, String password) {
        LoginCredentials loginCredentials = new LoginCredentials(username,password);
        TaskManager.instance.login(loginCredentials);

//        //1. Create services
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://retrofitserver.herokuapp.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        LoginServices loginServices = retrofit.create(LoginServices.class);
//
//        //2. Create requestbody
//        MediaType mediaType = MediaType.parse("application/json");
//        final String loginJson = new Gson().toJson(new LoginBodyJson(username,password));
//        final RequestBody requestBody = RequestBody.create(mediaType,loginJson);
//
//
//        //3. Call request
//        final Call<RegisterResponse> loginResponseCall = loginServices.login(requestBody);
//
//
//        loginResponseCall.enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//                if (response.code() == 200) {
//                    Log.d(TAG,String.format("accesstoken: %s return: %s",response.body().getAccesstoken(),"Login success"));
//                    //put accessToken
//                    accessToken = response.body().getAccesstoken();
//                    SharePref.instance.put(new LoginCredentials(username,password,accessToken));
//                    makeText(LoginActivity.this, response.body().getAccesstoken(), Toast.LENGTH_SHORT).show();
//                }
//                if (response.code() == 400) {
//                    Log.d(TAG,"Failure to register");
//                    makeText(LoginActivity.this, "No internet connection to login", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                Log.d(TAG,"Failure to login");
//                makeText(LoginActivity.this, "Failure to login", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
