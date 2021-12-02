package com.tecmilenio.actividad12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    //Pantalla de lista de libros
    ListBook listBook = new ListBook();
    //Pantalla para agregar libros
    AddBook addBook = new AddBook();
    //Pantalla de lista de clientes
    ListClient listClient = new ListClient();
    //Pantalla para agregar clientes
    AddClient addClient = new AddClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Primera vista que mostramos
        loadFragment(listBook);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(listBook);
                    return true;
                case R.id.secondFragment:Fragment:
                    loadFragment(addBook);
                    return true;
                case R.id.thirdFragment:Fragment:
                    loadFragment(listClient);
                    return true;
                case R.id.fourthFragment:
                    loadFragment(addClient);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}