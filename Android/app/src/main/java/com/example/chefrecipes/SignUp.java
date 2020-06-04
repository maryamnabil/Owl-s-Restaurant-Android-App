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

public class SignUp extends AppCompatActivity {
    private final static String URL_REGISTER = "http://10.0.2.2:8000/users/register";
    private final static String MATCH_PASSWORD_ERROR = "Please make sure two passwords are the same";
    public final static String EMPTY_FIELDS_ERROR = "Please make sure you filled all required fields";

    private EditText emailEt;
    private EditText passwordEt;
    private EditText confirmpasswordEt;
    private EditText phoneEt;
    private EditText fullnameEt;
    private String email;
    private String password;
    private String fullname;
    private String confirmpassword;
    private String phone;
    private Button signUp;
    String result="";
    User user;
    JsonObjectRequest request;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailEt= findViewById(R.id.emailaddress_et);
        passwordEt = findViewById(R.id.password_et);
        phoneEt = findViewById(R.id.phone_et);
        fullnameEt = findViewById(R.id.fullname_et);
        confirmpasswordEt = findViewById(R.id.confirm_password_et);
        signUp = findViewById(R.id.signUp_btn);
        queue = Volley.newRequestQueue(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= returnStringValueofET(emailEt);
                password = returnStringValueofET(passwordEt);
                phone = returnStringValueofET(phoneEt);
                fullname= returnStringValueofET(fullnameEt);
                confirmpassword = returnStringValueofET(confirmpasswordEt);
                user = new User(email,password,confirmpassword,phone,fullname);
                if (user.getEmail().length() == 0 || user.getPassword().length() == 0 || user.getFullname().length() == 0
                        || user.getPhone().length() == 0 || user.getConfirmPassword().length()==0){
                    Toast.makeText(SignUp.this, EMPTY_FIELDS_ERROR , Toast.LENGTH_SHORT).show();

                } else {
                    goToOtp();
//                   signupRequest();


                }

            }

        });
    }


    private void signupRequest() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("confirmpassword", user.getConfirmPassword());
        params.put("fullname", user.getFullname());
        params.put("phone", user.getPhone());
        request = new JsonObjectRequest(Request.Method.POST, URL_REGISTER, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("info", response.toString());
                Toast.makeText(SignUp.this,  "success", Toast.LENGTH_SHORT).show();
//                goToOtp();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this,  error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
        goToOtp();
    }


    private String returnStringValueofET(EditText et){
        String value;
        value =et.getText().toString();
        return  value;
    }

    public void goToOtp(){
        Intent otp = new Intent(SignUp.this,Otp.class);
        startActivity(otp);
        finish();
    }
}
