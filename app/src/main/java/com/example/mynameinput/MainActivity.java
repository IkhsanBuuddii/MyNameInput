package com.example.mynameinput;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, emailInput, phoneInput, passwordInput;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextName);
        emailInput = findViewById(R.id.editTextEmail);
        phoneInput = findViewById(R.id.editTextPhone);
        passwordInput = findViewById(R.id.editTextPassword);
        submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        if (name.isEmpty()) {
            nameInput.setError("Name is required");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Invalid email");
            return;
        }

        if (!Patterns.PHONE.matcher(phone).matches() || phone.length() < 10) {
            phoneInput.setError("Invalid phone number");
            return;
        }

        String passwordError = checkPasswordRules(password);
        if (passwordError != null) {
            passwordInput.setError(passwordError);
            return;
        }

        Toast.makeText(this, "All inputs valid!\nWelcome, " + name, Toast.LENGTH_LONG).show();
    }

    private String checkPasswordRules(String password) {
        if (password.length() < 8) {
            return "Password minimal 8 karakter";
        }
        if (!password.matches(".*[0-9].*")) {
            return "Password harus mengandung angka";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password harus mengandung huruf kecil";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password harus mengandung huruf besar";
        }
        if (!password.matches(".*[@#$%^&+=!].*")) {
            return "Password harus mengandung simbol (@#$%^&+=!)";
        }
        return null; // Password valid
    }
}
