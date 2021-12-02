package com.tecmilenio.actividad12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    //Campo nombre del usuario
    EditText editUser;
    //Campo correo
    EditText editEmail;
    //Campo contraseña
    EditText editPassword;

    //Botón de registro
    Button btn_create_account;
    //Botón de volver
    Button btn_back;

    //Manejador de la base de datos
    BDHandler BDHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editUser = findViewById(R.id.createAccountTextUser);
        editEmail = findViewById(R.id.createAccountTextEmail);
        editPassword = findViewById(R.id.createAccountTextPassword);

        btn_create_account = findViewById(R.id.btn_create_account);
        btn_create_account.setOnClickListener(this);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        BDHandler = new BDHandler(this,null,null,1);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(btn_create_account)){
            createUser();
        }else if(view.equals(btn_back)){
            goToLogin();
        }
    }

    public void  goToLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Registra un nuevo usuario
     */
    public void createUser(){
        //Definimos el nombre del usuario, email y contraseña
        String username = editUser.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        //Creamos un objeto tipo User
        User user = new User(username,email,password);

        //Agregamos el objeto a la base de datos
        long result = BDHandler.addUser(user);

        //Comprobamos si se agrego correctamente el registro
        if(result == -1){
            //No se logro registrar un nuevo usuario
            Toast fail =
                    Toast.makeText(getApplicationContext(),"Registro no exitoso",Toast.LENGTH_SHORT);
            fail.show();

            //Limpiamos las cajas de texto
            editUser.setText("");
            editEmail.setText("");
            editPassword.setText("");

        }else{
            //Se logro registrar un usuario
            Toast success =
                    Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT);
            success.show();
            //Regresamos a la pantalla inicial para logearnos
            goToLogin();
        }
    }
}