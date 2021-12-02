package com.tecmilenio.actividad12;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Campo nombre del usuario
    EditText editUser;
    //Campo contraseña
    EditText editPassword;

    //Botón inicia sesión
    Button login;
    //Botón de registro de usuario
    Button register;

    //Manejador de la base de datos
    BDHandler BDHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = findViewById(R.id.editTextTextPersonName);
        editPassword = findViewById(R.id.editTextTextPassword);

        login = findViewById(R.id.btn_create_account);
        login.setOnClickListener(this);

        register = findViewById(R.id.btn_back);
        register.setOnClickListener(this);

        BDHandler = new BDHandler(this,null,null,1);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(login)){
            goToPrincipalScreen();
        }else if(view.equals(register)){
            goToRegisterScreen();
        }
    }

    public void goToPrincipalScreen(){
        //Obtengo la información de los campos
        String user = editUser.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(BDHandler.getAccess(user,password)){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }else{
            Toast fail =
                    Toast.makeText(getApplicationContext(),"No existe este usuario",Toast.LENGTH_SHORT);
            fail.show();
        }
    }

    public void goToRegisterScreen(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}
