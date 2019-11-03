package com.example.topicosespecialesproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topicosespecialesproyecto.SaveRegister;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Variables
    Button btn1;
    Button btn2;
    EditText editTextUser;
    EditText editTextPass;

    // variables de firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // busqueda de las variables
        // button de iniciar sesion
        btn1 = (Button) findViewById(R.id.button);
        // button de ir a la pagina de registro
        btn2 = (Button) findViewById(R.id.button2);


        // Inicializacion de edit text
        editTextUser = (EditText) findViewById(R.id.edittext1);
        editTextPass = (EditText) findViewById(R.id.edittext2);

        //Iniciamos Firebase
        IniciarFirebase();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(editTextUser.getText().toString(),editTextPass.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void IniciarFirebase() {
        // Inicia Firebase en la aplicacion

        firebaseDatabase = FirebaseDatabase.getInstance();
        users = firebaseDatabase.getReference("Usuarios");
    }

    private void signIn(final String username,final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()){
                    if (!username.isEmpty()){
                        SaveRegister  login = dataSnapshot.child(username).getValue(SaveRegister.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Bienvenido",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ActivityApplication.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Contraseña incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Usuario no registrado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
