package com.tecmilenio.actividad12;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditInformationBook extends AppCompatActivity implements View.OnClickListener {

    EditText editNameBookEdition;
    EditText editCategoryBookEdition;
    EditText editAutorBookEdition;

    Button btnSaveBookRegister;

    Book book;

    BDHandler bdHandler;

    boolean isEdited = false;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information_book);

        editNameBookEdition = findViewById(R.id.nameBookEdition);
        editCategoryBookEdition = findViewById(R.id.categoryBookEdition);
        editAutorBookEdition = findViewById(R.id.autorBookEdition);
        btnSaveBookRegister = findViewById(R.id.btnSaveBookRegister);
        btnSaveBookRegister.setOnClickListener(this);

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

        bdHandler = new BDHandler(EditInformationBook.this,null,null,1);

        book = bdHandler.showInformationBook(id);

        if(book != null){
            editNameBookEdition.setText(book.getName());
            editCategoryBookEdition.setText(book.getCategory());
            editAutorBookEdition.setText(book.getAutor());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnSaveBookRegister)) {
            if (!editNameBookEdition.getText().toString().equals("") &&
                    !editAutorBookEdition.getText().toString().equals("") &&
                    !editCategoryBookEdition.getText().toString().equals("")) {

                isEdited = bdHandler.editBook(id, editNameBookEdition.getText().toString(),
                        editAutorBookEdition.getText().toString(),
                        editCategoryBookEdition.getText().toString());

                if (isEdited) {
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show();
                    goBackToShowInformationBook();
                } else {
                    Toast.makeText(this, "No se edito correctamente", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void goBackToShowInformationBook(){
        Intent intent = new Intent(this,Home.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
