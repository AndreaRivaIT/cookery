package it.unimib.cookery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import it.unimib.cookery.R;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.utils.ResponseCallbackDb;

public class MakeRecipe extends AppCompatActivity implements ResponseCallbackDb {

    private Button searchIngredientBtn;
    private RecyclerView ingredientListRV;
    private DatabasePantryRepository db;
    private ArrayList<IngredientApi> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_recipe);

        // initializing RV and views
        searchIngredientBtn = findViewById(R.id.ingredient_button);
        ingredientListRV = findViewById(R.id.ingredient_list);

        //setting db
        db = new DatabasePantryRepository(this.getApplication(), this);
        db.readAllIngredientApi();

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