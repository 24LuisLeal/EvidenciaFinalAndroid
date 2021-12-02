package com.tecmilenio.actividad12;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditInformationClients extends AppCompatActivity implements View.OnClickListener{

    EditText nameClientEdition;
    EditText firstNameClientEdition;
    EditText secondNameClientEdition;
    EditText emailClientEdition;
    EditText phoneClientEdition;

    Button btnSaveClientRegister;

    Clients clients;

    BDHandler bdHandler;

    boolean isEdited = false;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information_book);

        nameClientEdition = findViewById(R.id.nameClientEdition);
        firstNameClientEdition = findViewById(R.id.firstNameClientEdition);
        secondNameClientEdition = findViewById(R.id.secondNameClientEdition);
        emailClientEdition = findViewById(R.id.emailClientEdition);
        phoneClientEdition = findViewById(R.id.phoneClientEdition);

        btnSaveClientRegister = findViewById(R.id.btnSaveClientRegister);

        if(savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if(extra == null){
                id = Integer.parseInt(null);
            }else{
                id = extra.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        bdHandler = new BDHandler(EditInformationClients.this,null,null,1);

        clients = bdHandler.showInformationClients(id);

        if(clients != null){
            nameClientEdition.setText(clients.getName());
            firstNameClientEdition.setText(clients.getFirstName());
            secondNameClientEdition.setText(clients.getSecondName());
            emailClientEdition.setText(clients.getEmail());
            phoneClientEdition.setText(clients.getPhone());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnSaveClientRegister)) {
            if (!nameClientEdition.getText().toString().equals("") &&
                    !firstNameClientEdition.getText().toString().equals("") &&
                    !secondNameClientEdition.getText().toString().equals("") &&
                    !emailClientEdition.getText().toString().equals("") &&
                    !phoneClientEdition.getText().toString().equals("")) {

                isEdited = bdHandler.editClients(id, nameClientEdition.getText().toString(),
                        firstNameClientEdition.getText().toString(),
                        secondNameClientEdition.getText().toString(),
                        emailClientEdition.getText().toString(),
                        phoneClientEdition.getText().toString());

                if (isEdited) {
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show();
                    goBackToShowInformationClients();
                } else {
                    Toast.makeText(this, "No se edito correctamente", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void goBackToShowInformationClients(){
        Intent intent = new Intent(this,Home.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
