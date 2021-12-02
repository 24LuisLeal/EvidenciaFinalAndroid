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
 * Use the {@link ListBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBook extends Fragment implements SearchView.OnQueryTextListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListBook.
     */
    // TODO: Rename and change types and number of parameters
    public static ListBook newInstance(String param1, String param2) {
        ListBook fragment = new ListBook();
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

    SearchView searchBarBook;
    RecyclerView listOfBooksRV;
    ArrayList<Book> listBookArray;
    BDHandler bdHandler;
    ListBookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_book, container, false);

        //Barra de busqueda
        searchBarBook = view.findViewById(R.id.searchBarBook);
        searchBarBook.setOnQueryTextListener(this);

        //Lista de libros
        listOfBooksRV = view.findViewById(R.id.listOfBooksRV);
        listOfBooksRV.setLayoutManager(new LinearLayoutManager(getContext()));

        bdHandler = new BDHandler(getContext(),null,null,1);

        listBookArray = new ArrayList<>();

        adapter = new ListBookAdapter(bdHandler.showBooks());
        listOfBooksRV.setAdapter(adapter);

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