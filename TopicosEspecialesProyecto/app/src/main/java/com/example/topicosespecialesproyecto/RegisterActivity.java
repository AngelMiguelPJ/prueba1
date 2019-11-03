package com.example.topicosespecialesproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //variables del registro
    Button btnRegis;
    EditText mail;
    EditText user;
    EditText pass;

    // variables de firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Busqueda e identificacion del button y iniciar metodo
        btnRegis = (Button) findViewById(R.id.button3);
        btnRegis.setOnClickListener(this);

        //Busqueda e identificacion de EditText
        mail = (EditText) findViewById(R.id.edittext3);
        user = (EditText) findViewById(R.id.edittext4);
        pass = (EditText) findViewById(R.id.edittext5);

        //Iniciamos Firebase
        IniciarFirebase();

    }


    // Metodo para acceder a la base de datos
    private void IniciarFirebase() {
        // Inicia Firebase en la aplicacion
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onClick(View v) {

        // cambio de variables
        String email = mail.getText().toString();
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if (v == btnRegis){
            SaveRegister sv = new SaveRegister();
            sv.setUserId(username);
            sv.setEmail(email);
            sv.setUserName(username);
            sv.setPassword(password);
            databaseReference.child("Usuarios").child(sv.getUserId()).setValue(sv);
            Toast.makeText(this, "Registrado",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
    }
}
