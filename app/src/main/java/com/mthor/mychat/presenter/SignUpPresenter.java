package com.mthor.mychat.presenter;

import com.google.firebase.auth.FirebaseUser;
import com.mthor.mychat.model.SignUpModel;
import com.mthor.mychat.view.SignUpContract;

import java.util.HashMap;
import java.util.Map;

public class SignUpPresenter {

    private SignUpContract.View view;
    private SignUpModel model;

    public SignUpPresenter(SignUpContract.View view, SignUpModel model) {
        this.view = view;
        this.model = model;
    }

    public void registerUser() {
        String name = view.getName(); // Obtiene el nombre del usuario desde la vista
        String email = view.getEmail(); // Obtiene el correo electrónico del usuario desde la vista
        String password = view.getPassword(); // Obtiene la contraseña del usuario desde la vista

        // Verifica si algún campo está vacío
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            view.showToast("Por favor, completa todos los campos"); // Muestra un mensaje de advertencia en la vista
            return; // Sale del método si algún campo está vacío
        }

        // Verifica si la contraseña tiene al menos 6 caracteres
        if (password.length() < 6) {
            view.showToast("La contraseña debe tener al menos 6 caracteres"); // Muestra un mensaje de advertencia en la vista
            return; // Sale del método si la contraseña es demasiado corta
        }

        // Llama al método del modelo para registrar al usuario en Firebase Authentication
        model.registerUser(email, password, new SignUpModel.RegistroCallback() {
            @Override
            public void onSuccess(Object result) {
                FirebaseUser user = (FirebaseUser) result; // Obtiene el usuario registrado
                Map<String, Object> userData = new HashMap<>(); // Crea un mapa para almacenar los datos del usuario
                userData.put("name", name); // Agrega el nombre del usuario al mapa
                userData.put("email", email); // Agrega el correo electrónico del usuario al mapa

                // Llama al método del modelo para almacenar los datos del usuario en Firestore
                model.storeUserData(user, userData, new SignUpModel.RegistroCallback() {
                    @Override
                    public void onSuccess(Object result) {
                        view.showToast("Registro exitoso"); // Muestra un mensaje de éxito en la vista
                        view.clearInputFields(); // Limpia los campos de entrada en la vista
                        view.navigateToLogin(); // Navega a la pantalla de inicio de sesión en la vista
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.showToast("Error al registrar en Firestore"); // Muestra un mensaje de error en la vista
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                view.showToast("Error al registrar usuario"); // Muestra un mensaje de error en la vista
            }
        });
    }
}
