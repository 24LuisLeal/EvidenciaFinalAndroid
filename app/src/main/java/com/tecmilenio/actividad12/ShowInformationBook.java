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

public class ShowInformationBook extends AppCompatActivity implements View.OnClickListener{

    EditText editNameBookEdition;
    EditText editCategoryBookEdition;
    EditText editAutorBookEdition;

    Button btnSaveBookRegister;
    Button btnEditBookRegister;
    Button btnDeleteBookRegister;

    Book book;

    BDHandler bdHandler;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information_book);

        editNameBookEdition = findViewById(R.id.nameBookEdition);
        editCategoryBookEdition = findViewById(R.id.categoryBookEdition);
        editAutorBookEdition = findViewById(R.id.autorBookEdition);

        btnSaveBookRegister = findViewById(R.id.btnSaveBookRegister);

        btnEditBookRegister = findViewById(R.id.btnEditBookRegister);
        btnEditBookRegister.setOnClickListener(this);

        btnDeleteBookRegister = findViewById(R.id.btnDeleteBookRegister);
        btnDeleteBookRegister.setOnClickListener(this);

        btnSaveBookRegister.setVisibility(View.INVISIBLE);

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

        book = bdHandler.showInformationBook(id);

        if(book != null){
            editNameBookEdition.setText(book.getName());
            editCategoryBookEdition.setText(book.getCategory());
            editAutorBookEdition.setText(book.getAutor());

            editNameBookEdition.setInputType(InputType.TYPE_NULL);
            editCategoryBookEdition.setInputType(InputType.TYPE_NULL);
            editAutorBookEdition.setInputType(InputType.TYPE_NULL);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnEditBookRegister)){
            Intent intent = new Intent(this,EditInformationBook.class);
            intent.putExtra("ID",id);
            startActivity(intent);
        }else if(v.equals(btnDeleteBookRegister)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Quieres eliminar este libro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(bdHandler.deleteBook(id)){
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