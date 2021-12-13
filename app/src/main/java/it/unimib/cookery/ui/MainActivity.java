package it.unimib.cookery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import it.unimib.cookery.R;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavHostFragment mNavHostFragment;
    private NavController mNavController;
    private NavigationView mNavMenu;
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


        // Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Hamburger icon listener
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


        setDrawerMenu();

    }

    //(Not that dynamic) Dynamic Drawer menu that changes when user login or logout
    public void setDrawerMenu() {
        mNavMenu = findViewById(R.id.navigationView);
        if (logged) {
            mNavMenu.getMenu().clear();
            mNavMenu.inflateMenu(R.menu.drawer_nav_menu);
        } else {
            mNavMenu.getMenu().clear();
            mNavMenu.inflateMenu(R.menu.drawer_menu_not_logged);
        }
    }
}