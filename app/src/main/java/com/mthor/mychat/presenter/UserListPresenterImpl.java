package com.mthor.mychat.presenter;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mthor.mychat.model.UserModel;
import com.mthor.mychat.view.UserListContract;

import java.util.ArrayList;
import java.util.List;

public class UserListPresenterImpl implements UserListPresenter{

    private UserListContract view; // Interfaz de vista que maneja la interacción con la interfaz de usuario
    private FirebaseFirestore db; // Instancia de Firebase Firestore para acceder a la base de datos

    // Constructor del presentador que recibe la vista
    public UserListPresenterImpl(UserListContract view) {
        this.view = view;
        this.db = FirebaseFirestore.getInstance(); // Inicializa la instancia de Firestore
    }

    // Método para cargar la lista de usuarios desde Firestore
    @Override
    public void loadUsers() {
        // Obtén la referencia a la colección de usuarios en Firestore
        db.collection("usuarios")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<UserModel> userList = new ArrayList<>(); // Crea una lista para almacenar los usuarios

                    // Itera sobre los documentos obtenidos de la colección
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        UserModel user = document.toObject(UserModel.class); // Convierte el documento a un objeto UserModel
                        userList.add(user); // Agrega el usuario a la lista
                    }

                    // Muestra los usuarios en la vista
                    view.displayUsers(userList);
                })
                .addOnFailureListener(e -> {
                    // Muestra un mensaje de error en caso de fallo
                    view.showError("Error al cargar los usuarios: " + e.getMessage());
                });
    }

}
