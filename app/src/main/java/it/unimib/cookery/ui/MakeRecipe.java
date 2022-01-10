package it.unimib.cookery.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.adapters.MakeRecipeSearchAdapter;
import it.unimib.cookery.adapters.RecipeProcedureAdapter;
import it.unimib.cookery.adapters.SearchChipAdapter;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.RecipeStep;
import it.unimib.cookery.models.StepApi;
import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.utils.ResponseCallbackDb;

public class MakeRecipe extends AppCompatActivity implements ResponseCallbackDb {

    private Button searchIngredientBtn, addStepBtn, saveBtn, saveBtnStep;
    private RecyclerView ingredientListRV,addIngredientListRV;

    private MakeRecipeSearchAdapter searchChipAdapter;
    private IngredientChipAdapter ingredientChipAdapter;
    private RecipeProcedureAdapter stepAdapter;

    private Dialog ingredientDialog;
    private Dialog stepDialog;

    private SearchView searchView;
    private DatabasePantryRepository db;

    private EditText addStepEt;

    private static ArrayList<IngredientApi> ingredientsList = new ArrayList<>();
    private static ArrayList<RecipeStep> stepsList = new ArrayList<>();

    public static void updateArrayList(IngredientApi ingredient) {
        ingredientsList.add(ingredient);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_recipe);

        // initializing RV and views
        addStepBtn = findViewById(R.id.add_step_button);
        searchIngredientBtn = findViewById(R.id.ingredient_button);
        ingredientListRV = findViewById(R.id.ingredient_list);

        //setting db
        db = new DatabasePantryRepository(this.getApplication(), this);

        //setting adapters
        searchChipAdapter = new MakeRecipeSearchAdapter();
        ingredientChipAdapter = new IngredientChipAdapter();

        //add ingredient
        searchIngredientBtn.setOnClickListener(v -> {
            openDialogAddIngredient(v);
        });
        
        //add step
        addStepBtn.setOnClickListener(v -> {
            openDialogAddStep(v);
        });
    }

    private void openDialogAddStep(View view) {
        int counter = 1;
        stepDialog = new Dialog(view.getContext());
        stepDialog.setContentView(R.layout.add_step_dialog);
        stepDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        addStepEt = stepDialog.findViewById(R.id.add_step_et);
        String description = addStepEt.getText().toString();

        step = new RecipeStep(nStep, description);

        saveBtnStep = stepDialog.findViewById(R.id.ingredient_dialog_btn);
        saveBtnStep.setOnClickListener(v -> {
            stepsList.add(counter, step);
        });

    }

    public void openDialogAddIngredient(View view) {
        ingredientDialog = new Dialog(view.getContext());
        ingredientDialog.setContentView(R.layout.add_ingredient_dialog);
        ingredientDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //setting RV
        addIngredientListRV = ingredientDialog.findViewById(R.id.ingredient_dialog_rv);

        //ingredientList RV layoutmanager
        FlexboxLayoutManager flexboxLayoutManagerIngredientListRv = new FlexboxLayoutManager(this);
        ingredientListRV.setLayoutManager(flexboxLayoutManagerIngredientListRv);
        ingredientListRV.setFocusable(false);
        ingredientListRV.setNestedScrollingEnabled(false);
        ingredientListRV.setAdapter(ingredientChipAdapter);

        //ingredientListSearch RV layoutmanager
        FlexboxLayoutManager flexboxLayoutManagerIngredientRv = new FlexboxLayoutManager(this);
        addIngredientListRV.setLayoutManager(flexboxLayoutManagerIngredientRv);
        addIngredientListRV.setFocusable(false);
        addIngredientListRV.setNestedScrollingEnabled(false);
        addIngredientListRV.setAdapter(searchChipAdapter);

        searchView = ingredientDialog.findViewById(R.id.ingredient_dialog_sv);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 3) {
                    db.readIngredientApi(newText);
                }
                return false;
            }
        });

        // saving the ingredients(data) on the adapter
        saveBtn = ingredientDialog.findViewById(R.id.ingredient_dialog_btn);
        saveBtn.setOnClickListener(v -> {
            ingredientChipAdapter.setData(ingredientsList);
            ingredientDialog.dismiss();
        });

        ingredientDialog.show();
    }

    @Override
    public void onResponse(Object obj) {

    }

    @Override
    public void onResponseSearchIngredient(Object obj) {
        if (obj != null) {
            if (obj instanceof List) {
                List<IngredientApi> listIngredient = (List) obj;
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createSearchChips(listIngredient);
                    }
                });
            }
        }
    }

    @Override
    public void onFailure(String errorMessage) {

    }

    private void createSearchChips(List listData) {
        searchChipAdapter.setData(listData);
    }

    @Override
    public void onDestroy() {
        ingredientsList.removeAll(ingredientsList);
        super.onDestroy();
    }
}