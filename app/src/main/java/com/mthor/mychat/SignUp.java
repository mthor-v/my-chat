package com.mthor.mychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mthor.mychat.model.SignUpModel;
import com.mthor.mychat.presenter.SignUpPresenter;
import com.mthor.mychat.view.SignUpContract;

public class SignUp extends AppCompatActivity implements SignUpContract.View {

    // Componentes de la interfaz de usuario
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;
    private TextView textViewLogin;

    // Presentador
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Inicialización de componentes de la interfaz de usuario
        editTextName = findViewById(R.id.usernameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        btnRegister = findViewById(R.id.registerButton);
        textViewLogin = findViewById(R.id.loginText);

        // Creación del presentador
        presenter = new SignUpPresenter(this, new SignUpModel());

        // Configuración de los listeners
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerUser();
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    @Override
    public void showToast(String message) {
        // Mostrar un mensaje de tostada
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearInputFields() {
        // Limpiar los campos de entrada
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void navigateToLogin() {
        // Navegar a la actividad de inicio de sesión
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String getName() {
        // Obtener el nombre ingresado por el usuario
        return editTextName.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        // Obtener el correo electrónico ingresado por el usuario
        return editTextEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        // Obtener la contraseña ingresada por el usuario
        return editTextPassword.getText().toString().trim();
    }
}