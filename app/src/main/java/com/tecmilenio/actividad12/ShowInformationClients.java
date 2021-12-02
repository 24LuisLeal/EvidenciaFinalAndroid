package com.tecmilenio.actividad12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowInformationClients extends AppCompatActivity implements View.OnClickListener{

    EditText nameClientEdition;
    EditText firstNameClientEdition;
    EditText secondNameClientEdition;
    EditText emailClientEdition;
    EditText phoneClientEdition;

    Button btnDeleteClientRegister;
    Button btnEditClientRegister;
    Button btnSaveClientRegister;

    Clients clients;

    BDHandler bdHandler;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information_clients);

        nameClientEdition = findViewById(R.id.nameClientEdition);
        firstNameClientEdition = findViewById(R.id.firstNameClientEdition);
        secondNameClientEdition = findViewById(R.id.secondNameClientEdition);
        emailClientEdition = findViewById(R.id.emailClientEdition);
        phoneClientEdition = findViewById(R.id.phoneClientEdition);

        btnSaveClientRegister = findViewById(R.id.btnSaveClientRegister);

        btnEditClientRegister = findViewById(R.id.btnEditClientRegister);
        btnEditClientRegister.setOnClickListener(this);

        btnDeleteClientRegister = findViewById(R.id.btnDeleteClientRegister);
        btnDeleteClientRegister.setOnClickListener(this);

        btnSaveClientRegister.setVisibility(View.INVISIBLE);

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

        bdHandler = new BDHandler(this,null,null,1);

        clients = bdHandler.showInformationClients(id);

        if(clients != null){
            nameClientEdition.setText(clients.getName());
            firstNameClientEdition.setText(clients.getFirstName());
            secondNameClientEdition.setText(clients.getSecondName());
            emailClientEdition.setText(clients.getEmail());
            phoneClientEdition.setText(clients.getPhone());

            nameClientEdition.setInputType(InputType.TYPE_NULL);
            firstNameClientEdition.setInputType(InputType.TYPE_NULL);
            secondNameClientEdition.setInputType(InputType.TYPE_NULL);
            emailClientEdition.setInputType(InputType.TYPE_NULL);
            phoneClientEdition.setInputType(InputType.TYPE_NULL);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnEditClientRegister)){
            Intent intent = new Intent(this,EditInformationClients.class);
            intent.putExtra("ID",id);
            startActivity(intent);
        }else if(v.equals(btnDeleteClientRegister)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Quieres eliminar este cliente?")
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(bdHandler.deleteClients(id)){
                                goBack();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }
    public void goBack(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}