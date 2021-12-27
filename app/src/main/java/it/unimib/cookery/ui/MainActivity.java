package it.unimib.cookery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import it.unimib.cookery.R;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.Pantry;
import it.unimib.cookery.models.PantryWithIngredientPantry;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.repository.RecipeRepository;
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

public class MainActivity extends AppCompatActivity implements ResponseCallbackDb{

    private Menu drawerMenu;
    private DrawerLayout mDrawerLayout;
    private NavHostFragment mNavHostFragment;
    private NavController mNavController;
    private NavigationView mNavMenu;
    private List<PantryWithIngredientPantry> list;
    private boolean logged = false;


    private RecipeRepository db;
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


        /*creazione ricetta*/
        String urlImg ="https://www.cucchiaio.it/content/cucchiaio/it/ricette/2009/12/ricetta-lasagne-bolognese/_jcr_content/header-par/image_single.img.jpg/1462958827968.jpg";

        Recipe recipeTest = new Recipe(1,urlImg,"Lasagna");
        Recipe recipeTestA = new Recipe("pasta al forno", "First course", R.drawable.spoonacular);
        Recipe recipeTestB = new Recipe("risotto", "First course", R.drawable.spoonacular);
        Recipe recipeTestC= new Recipe("arrosto", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestD = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestE = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestF = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestG = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestH = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestI = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestL = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        Recipe recipeTestM = new Recipe("parmigina", "Main meal", R.drawable.spoonacular);
        db = new RecipeRepository(getApplication(),this);
        /*db.createRecipe(recipeTest);
        db.createRecipe(recipeTestA);
        db.createRecipe(recipeTestB);
        db.createRecipe(recipeTestC);
        db.createRecipe(recipeTestD);
        db.createRecipe(recipeTestE);
        db.createRecipe(recipeTestF);
        db.createRecipe(recipeTestG);
        db.createRecipe(recipeTestH);
        db.createRecipe(recipeTestI);
        db.createRecipe(recipeTestL);
        db.createRecipe(recipeTestM);*/


        IngredientApi ingredientApi = new IngredientApi(11547,"tomato puree");
        IngredientApi ingredientApiA = new IngredientApi(1012047,"sea salt");
        IngredientApi ingredientApiB = new IngredientApi(1116,"yogurt");
        IngredientApi ingredientApiC = new IngredientApi(98846,"cocoa nibs");
        IngredientApi ingredientApiD = new IngredientApi(19165,"cocoa powder");
        IngredientApi ingredientApiE = new IngredientApi(12104,"coconut");
        IngredientApi ingredientApiF = new IngredientApi(98929,"coconut aminos");
        IngredientApi ingredientApiG = new IngredientApi(93746,"coconut butter");
        IngredientApi ingredientApiH = new IngredientApi(12115,"coconut cream");
        IngredientApi ingredientApiI = new IngredientApi(1032050,"coconut extract");
        IngredientApi ingredientApiL = new IngredientApi(10114037,"brandy");
        IngredientApi ingredientApiM = new IngredientApi(18064,"bread");
        IngredientApi ingredientApiN = new IngredientApi(10120129,"bread flour");
        IngredientApi ingredientApiO = new IngredientApi(10011693,"canned tomatoes");
        IngredientApi ingredientApiP = new IngredientApi(10115121,"canned tuna");
        IngredientApi ingredientApiQ = new IngredientApi(10716050,"cannellini beans");
        IngredientApi ingredientApiR = new IngredientApi(10093727,"cheese tortellini");
        IngredientApi ingredientApiS = new IngredientApi(9070,"cherry");
        IngredientApi ingredientApiT = new IngredientApi(12098,"chestnuts");
        IngredientApi ingredientApiU = new IngredientApi(11168,"corn");
        IngredientApi ingredientApiV = new IngredientApi(2012,"coriander");
        IngredientApi ingredientApiW = new IngredientApi(10118192,"cookies");
        IngredientApi ingredientApiZ = new IngredientApi(20137,"cooked quinoa");
        IngredientApi ingredientApiY = new IngredientApi(98853,"gnocchi");

        DatabasePantryRepository dbIngredient;
        dbIngredient = new DatabasePantryRepository(getApplication(),this);
        /*dbIngredient.create(ingredientApi);
        dbIngredient.create(ingredientApiA);
        dbIngredient.create(ingredientApiB);
        dbIngredient.create(ingredientApiC);
        dbIngredient.create(ingredientApiD);
        dbIngredient.create(ingredientApiE);
        dbIngredient.create(ingredientApiF);
        dbIngredient.create(ingredientApiG);
        dbIngredient.create(ingredientApiH);
        dbIngredient.create(ingredientApiI);
        dbIngredient.create(ingredientApiL);
        dbIngredient.create(ingredientApiM);
        dbIngredient.create(ingredientApiN);
        dbIngredient.create(ingredientApiO);
        dbIngredient.create(ingredientApiP);
        dbIngredient.create(ingredientApiQ);
        dbIngredient.create(ingredientApiR);
        dbIngredient.create(ingredientApiS);
        dbIngredient.create(ingredientApiT);
        dbIngredient.create(ingredientApiU);
        dbIngredient.create(ingredientApiV);
        dbIngredient.create(ingredientApiZ);
        dbIngredient.create(ingredientApiW);
        dbIngredient.create(ingredientApiY);*/



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




    @Override
    public void onResponse(Object obj) {

    }

    @Override
    public void onResponseSearchIngredient(Object obj) {

    }

    @Override
    public void onFailure(String errorMessage) {

    }
}