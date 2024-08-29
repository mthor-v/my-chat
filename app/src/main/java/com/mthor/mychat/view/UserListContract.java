package com.mthor.mychat.view;

import com.mthor.mychat.model.UserModel;

import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para la vista de lista de usuarios.
 */
public interface UserListContract {
    /**
     * Muestra la lista de usuarios en la interfaz de usuario.
     * @param users Lista de modelos de usuarios.
     */
    void displayUsers(List<UserModel> users);

    /**
     * Muestra un mensaje de error en la interfaz de usuario.
     * @param message Mensaje de error a mostrar.
     */
    void showError(String message);
}
