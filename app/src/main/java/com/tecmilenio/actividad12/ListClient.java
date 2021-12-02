package com.tecmilenio.actividad12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListClient extends Fragment implements SearchView.OnQueryTextListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListClient.
     */
    // TODO: Rename and change types and number of parameters
    public static ListClient newInstance(String param1, String param2) {
        ListClient fragment = new ListClient();
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

    SearchView searchBarClient;
    RecyclerView listOfClientsRV;
    ArrayList<Book> listClientArray;
    BDHandler bdHandler;
    ListClientAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_client, container, false);

        //Barra de busqueda
        searchBarClient = view.findViewById(R.id.searchBarClient);
        searchBarClient.setOnQueryTextListener(this);

        //Lista de libros
        listOfClientsRV = view.findViewById(R.id.listOfClientsRV);
        listOfClientsRV.setLayoutManager(new LinearLayoutManager(getContext()));

        bdHandler = new BDHandler(getContext(),null,null,1);

        listClientArray = new ArrayList<>();

        adapter = new ListClientAdapter(bdHandler.showClients());
        listOfClientsRV.setAdapter(adapter);
        return view;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterRecords(newText);
        return false;
    }
}