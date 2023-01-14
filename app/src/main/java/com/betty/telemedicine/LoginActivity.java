package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userName,Password;
    Button login;
    TextView tv;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        userName = findViewById(R.id.editTextUser);
        Password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = userName.getText().toString();
                String pass = Password.getText().toString();
                db = new DB(LoginActivity.this);
                if (User.length()==0 || pass.length()==0)
                    Toast.makeText(LoginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                else {

                    Boolean checkuserpass = db.checkusernamepassword(User, pass);

                    if (checkuserpass == true)


                        startActivity(new Intent(LoginActivity.this, home.class));

                    else
                        Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();


                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}
