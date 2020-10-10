package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.elanilsondejesus.com.notpadbr.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
//    private MaterialSearchView searchViewPesquisa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // pesquisar
//        searchViewPesquisa = findViewById(R.id.searchPesquisa);
//        ///configuração da toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);


        // abrir o drawer ao clicar na imagem menu
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.menuToolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //configurando a navegacao
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this,R.id.navigation_header);
        NavigationUI.setupWithNavController(navigationView,navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        //configurar bottom pesquisar
//        MenuItem item = menu.findItem(R.id.pesquisa);
//        searchViewPesquisa.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opcoes:




                break;
        }
        return super.onOptionsItemSelected(item);
    }


}