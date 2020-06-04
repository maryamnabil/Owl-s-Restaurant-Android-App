package com.example.chefrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private final static String URL_LOGIN = "http://10.0.2.2:8000/users/login";
    public final static String EMPTY_FIELDS_ERROR = "Please make sure you filled all required fields";

    private EditText emailEt;
    private EditText passwordEt;
    private String email;
    private String password;
    private Button signIn;
    User user;
    String result="";
    JsonObjectRequest request;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEt= findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        signIn= (Button) findViewById(R.id.signIn_btn);
        queue = Volley.newRequestQueue(this);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= returnStringValueofET(emailEt);
                password = returnStringValueofET(passwordEt);
                if(email.length() == 0 || password.length() == 0){
                    Toast.makeText(Login.this,  EMPTY_FIELDS_ERROR, Toast.LENGTH_SHORT).show();
                }
                else{
                    user=new User(email,password);
//                    result = loginRequest();
//                    if(result =="success"){
//                        Toast.makeText(Login.this,  "horray", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Login.this,  result, Toast.LENGTH_SHORT).show();
//                    }
                    // Go to Home Trial
                    goToHome();
                }

            }
        });

    }

    private String loginRequest() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        request = new JsonObjectRequest(
                Request.Method.POST, URL_LOGIN, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("info", response.toString());
                        try {

                            String error = response.getString("error");
                            Log.i("info", error);
                            if (!response.has("error")) {
                                result = "success";
                            } else {
                                result = response.getString("error");
                            }
                        } catch (JSONException e) {
                            result = e.toString();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result = error.toString();
                    }
                }) {


        };
        queue.add(request);
        return result;
    }
    private String returnStringValueofET(EditText et){
        String value;
        value =et.getText().toString();
        return  value;
    }


    public void signUp(View view) {
        Intent signUp = new Intent(this,SignUp.class);
        startActivity(signUp);
    }

    public void fogotPassword(View view) {
    }
    public void goToHome(){
        Intent home = new Intent(Login.this,Home.class);
        startActivity(home);
        finish();
    }
}
