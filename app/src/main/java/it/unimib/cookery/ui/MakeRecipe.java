package it.unimib.cookery.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.adapters.MakeRecipeSearchAdapter;
import it.unimib.cookery.adapters.RecipeProcedureAdapter;
import it.unimib.cookery.adapters.SearchChipAdapter;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeStep;
import it.unimib.cookery.models.StepApi;
import it.unimib.cookery.repository.DatabasePantryRepository;
import it.unimib.cookery.utils.ResponseCallbackDb;

public class MakeRecipe extends AppCompatActivity implements ResponseCallbackDb {

    private Button searchIngredientBtn, addStepBtn, saveBtn, saveBtnStep, saveRecipe;

    private RecyclerView ingredientListRV,addIngredientListRV, stepRV;

    private MakeRecipeSearchAdapter searchChipAdapter;
    private IngredientChipAdapter ingredientChipAdapter;
    private RecipeProcedureAdapter stepAdapter;

    private Dialog ingredientDialog;
    private Dialog stepDialog;

    private String description;

    private RecipeStep step;

    private SearchView searchView;

    private DatabasePantryRepository db;

    private EditText addStepEt, recipeNameEt, numServ;

    private Spinner typeSpinner;

    private static ArrayList<IngredientApi> ingredientsList = new ArrayList<>();
    private static ArrayList<RecipeStep> stepsList = new ArrayList<>();
    private static ArrayList<String> stepsListString = new ArrayList<>();
    private static ArrayList<Recipe> recipesList = new ArrayList<>();

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
        stepRV = findViewById(R.id.step_list);
        recipeNameEt = findViewById(R.id.make_recipe_name);
        typeSpinner = findViewById(R.id.type_spinner);
        numServ = findViewById(R.id.numServ);
        saveRecipe = findViewById(R.id.save_recipe);
        numServ = findViewById(R.id.numServ);

        //setting up typeSpinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        //setting db
        db = new DatabasePantryRepository(this.getApplication(), this);

        //saving recipe
        saveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe newRecipe = new Recipe(recipeNameEt.getText().toString(), typeSpinner.getContext().toString(), 1);

                //setting up db
                makeDb(newRecipe);
                finish();
            }
        });

        //setting adapters
        searchChipAdapter = new MakeRecipeSearchAdapter();
        ingredientChipAdapter = new IngredientChipAdapter();
        stepAdapter = new RecipeProcedureAdapter();

        //add ingredient
        searchIngredientBtn.setOnClickListener(v -> {
            openDialogAddIngredient(v);
        });

        //add step
        addStepBtn.setOnClickListener(v -> {
            openDialogAddStep(v);
        });

    }

    private void makeDb(Recipe recipe) {
        db.create(recipe);
    }

    private void openDialogAddStep(View view) {
        stepDialog = new Dialog(view.getContext());
        stepDialog.setContentView(R.layout.add_step_dialog);
        stepDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //setting steps RV
        LinearLayoutManager flexboxLayoutManagerStepListRv = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stepRV.setLayoutManager(flexboxLayoutManagerStepListRv);
        stepRV.setFocusable(false);
        stepRV.setNestedScrollingEnabled(true);
        stepRV.setAdapter(stepAdapter);

        addStepEt = stepDialog.findViewById(R.id.add_step_et);

        saveBtnStep = stepDialog.findViewById(R.id.ingredient_dialog_btn);
        saveBtnStep.setOnClickListener(v -> {
            stepsListString.clear();
            description = addStepEt.getText().toString();
            step = new RecipeStep(description);

            stepsList.add(step);
            recipeStepParse(stepsList);
            stepAdapter.setData(stepsListString);
            stepDialog.dismiss();
        });
        stepDialog.show();
    }

    private void recipeStepParse(ArrayList<RecipeStep> stepsList) {
        for(int j = 0; j < stepsList.size(); j++) {
            stepsListString.add(stepsList.get(j).getDescription());
        }
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
                } else {
                    searchChipAdapter.setData(null);
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
        stepsList.removeAll(stepsList);
        stepsListString.removeAll(stepsListString);
        super.onDestroy();
    }

}