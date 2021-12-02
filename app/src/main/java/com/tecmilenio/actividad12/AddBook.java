package com.tecmilenio.actividad12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBook extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBook.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBook newInstance(String param1, String param2) {
        AddBook fragment = new AddBook();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText editNameBookRegister;
    EditText editCategoryBookRegister;
    EditText editAutorBookRegister;

    Button btnCancelBookRegister;
    Button btnAddBookRegister;

    BDHandler bdHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        editNameBookRegister = view.findViewById(R.id.nameBookRegister);
        editCategoryBookRegister = view.findViewById(R.id.categoryBookRegister);
        editAutorBookRegister = view.findViewById(R.id.autorBookRegister);

        btnCancelBookRegister = view.findViewById(R.id.btnCancelBookRegister);
        btnCancelBookRegister.setOnClickListener(this);
        btnAddBookRegister = view.findViewById(R.id.btnAddBookRegister);
        btnAddBookRegister.setOnClickListener(this);

        bdHandler = new BDHandler(getContext(),null,null,1);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnAddBookRegister)){
            addBook();
        }else if(v.equals(btnCancelBookRegister)){
            cleanBoxes();
        }
    }

    public void addBook(){
        String nameBook = editNameBookRegister.getText().toString().trim();
        String category = editCategoryBookRegister.getText().toString().trim();
        String autor = editAutorBookRegister.getText().toString().trim();


        Book book = new Book(nameBook,autor,category);

        long result = bdHandler.addBooks(book);

        if(result == -1){
            Toast fail =
                    Toast.makeText(getActivity(),"Registro no exitoso, intentalo de nuevo",Toast.LENGTH_SHORT);
            fail.show();
        }else{
            Toast success =
                    Toast.makeText(getActivity(),"Registro " +
                            "exitoso",Toast.LENGTH_SHORT);
            success.show();
            //Limpiamos las cajas de texto
            cleanBoxes();
        }
    }

    public void cleanBoxes(){
        editNameBookRegister.setText("");
        editCategoryBookRegister.setText("");
        editAutorBookRegister.setText("");
    }
}