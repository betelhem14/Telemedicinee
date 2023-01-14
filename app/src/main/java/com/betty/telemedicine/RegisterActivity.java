package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText Username,pass,Email,confirm;
    Button register;
    TextView existing;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Username = findViewById(R.id.editTextUsername2);
        pass = findViewById(R.id.editTextPassword1);
        Email = findViewById(R.id.editTextEmail);
        confirm = findViewById(R.id.editTextConfirm);
        register = findViewById(R.id.buttonRegister);
        existing = findViewById(R.id.textViewLogin);

        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Username.getText().toString();
                String pasword = pass.getText().toString();
                String mail = Email.getText().toString();
                String pass2 = confirm.getText().toString();
                db = new DB(RegisterActivity.this);



                if(name.equals("")||pasword.equals("")||pass2.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else
                {
                    if(pasword.equals(pass2))
                    {
                        Boolean checkuser = db.checkusername(name);
                        if(checkuser==false)

                        {
                            if(isValid(pasword)) {
                                Boolean insert = db.insertData(name, mail, pasword);

                                if(insert==true)
                                {
                                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                }
                                else

                                {
                                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                } }
                            else {
                                Toast.makeText(RegisterActivity.this, "passwords must contain 8 characters,atleast 1 number and special character", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        } }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    } } } });
    }
    public static boolean isValid(String password){
        int f1=0, f2=0,f3=0;
        if (password.length()<8){
            return false;
        }
        else{
            for (int p=0;p<password.length();p++){
                if (Character.isLetter(password.charAt(p))){
                    f1=1;
                }
            }
            for (int r=0;r<password.length();r++){
                if (Character.isDigit(password.charAt(r))){
                    f2=1;
                }
            }
            for (int s=0;s<password.length();s++){
                char c = password.charAt(s);
                if(c>=33 && c<=46 || c==46){
                    f3=1;
                }
            }
            if (f1== 1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}
