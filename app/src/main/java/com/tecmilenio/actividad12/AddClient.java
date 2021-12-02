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
 * Use the {@link AddClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClient extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClient.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClient newInstance(String param1, String param2) {
        AddClient fragment = new AddClient();
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

    //Campos de nombre, apellidos, correo y teléfono
    EditText editNameClient;
    EditText editFirstNameClient;
    EditText editSecondNameClient;
    EditText editEmailClient;
    EditText editPhoneClient;

    //Botones de cancelar registro y agregar registro
    Button btnCancelRegister;
    Button btnAddRegister;

    BDHandler bdHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);

        //Iniciliazo los campos
        editNameClient = view.findViewById(R.id.nameClientRegister);
        editFirstNameClient = view.findViewById(R.id.firstNameClientRegister);
        editSecondNameClient = view.findViewById(R.id.secondNameClientRegister);
        editEmailClient = view.findViewById(R.id.emailClientRegister);
        editPhoneClient = view.findViewById(R.id.phoneClientRegister);

        //Inicializo los botones
        btnCancelRegister = view.findViewById(R.id.btnCancelClientRegister);
        btnCancelRegister.setOnClickListener(this);
        btnAddRegister = view.findViewById(R.id.btnAddClientRegister);
        btnAddRegister.setOnClickListener(this);

        bdHandler = new BDHandler(getContext(),null,null,1);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnCancelRegister)){
            cleanBoxes();
        }else if(v.equals(btnAddRegister)) {
            addClient();
        }
    }

    public void addClient(){
        String nameClient = editNameClient.getText().toString().trim();
        String firstNameClient = editFirstNameClient.getText().toString().trim();
        String secondNameClient = editSecondNameClient.getText().toString().trim();
        String emailClient = editEmailClient.getText().toString().trim();
        String phoneClient = editPhoneClient.getText().toString().trim();

        //Creamos un objeto de tipo cliente
        Clients clients = new Clients(nameClient,firstNameClient,secondNameClient,emailClient,phoneClient);

        //Insertamos nuestro objeto en la base de datos
        long result = bdHandler.addClients(clients);

        if(result == -1){
            //No se logro registrar un nuevo usuario
            Toast fail =
                    Toast.makeText(getActivity(),"Registro no exitoso," +
                            " intentalo de nuevo",Toast.LENGTH_SHORT);
            fail.show();
        }else{
            //Se logro registrar un usuario
            Toast success =
                    Toast.makeText(getActivity(),"Registro " +
                            "exitoso",Toast.LENGTH_SHORT);
            success.show();
            //Limpiamos las cajas de texto
            cleanBoxes();
        }
    }

    public void cleanBoxes(){
        editNameClient.setText("");
        editFirstNameClient.setText("");
        editSecondNameClient.setText("");
        editEmailClient.setText("");
        editPhoneClient.setText("");
    }
}