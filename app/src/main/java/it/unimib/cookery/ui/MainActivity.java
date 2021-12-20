package it.unimib.cookery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import it.unimib.cookery.R;
import it.unimib.cookery.models.Pantry;
import it.unimib.cookery.models.PantryWithIngredientPantry;
import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.utils.ResponseCallbackDb;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private Menu drawerMenu;
    private DrawerLayout mDrawerLayout;
    private NavHostFragment mNavHostFragment;
    private NavController mNavController;
    private NavigationView mNavMenu;
    private List<PantryWithIngredientPantry> list;
    private boolean logged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //NavHostController configuration and homeFragment set as startupFragment
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.homeFragmentContainerView);
        mNavController = mNavHostFragment.getNavController();

        //BottomNavView configuration
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);

        //DrawerLayout configuration
        mDrawerLayout = findViewById(R.id.drawerLayout);

        //Hamburger icon listener
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //NavigationMenu config
        mNavMenu = findViewById(R.id.navigationView);

        setDrawerMenu();

    }

    //(Not that dynamic) Dynamic Drawer and header menu that changes when user login or logout
    public void setDrawerMenu() {

        View view = mNavMenu.getHeaderView(0);

        if(logged) {
            if(view.equals(null)) {
                mNavMenu.inflateHeaderView(R.layout.drawer_menu_header);
            } else {
                mNavMenu.removeHeaderView(view);
                mNavMenu.inflateHeaderView(R.layout.drawer_menu_header);
            }

            mNavMenu.getMenu().clear();
            mNavMenu.inflateMenu(R.menu.drawer_nav_menu);


        } else {
            if(view.equals(null)) {
                mNavMenu.inflateHeaderView(R.layout.drawer_menu_header_not_logged);
            } else {
                mNavMenu.removeHeaderView(view);
                mNavMenu.inflateHeaderView(R.layout.drawer_menu_header_not_logged);
            }

            mNavMenu.getMenu().clear();
            mNavMenu.inflateMenu(R.menu.drawer_menu_not_logged);
            mNavMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.login_drawer:
                            startActivity(new Intent(getApplicationContext(), login_register_user.class));
                    }
                    return false;
                }
            });
        }
    }


}