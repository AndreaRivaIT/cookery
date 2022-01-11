package it.unimib.cookery.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.adapters.RecipeProcedureAdapter;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.IngredientPantry;
import it.unimib.cookery.models.StepApi;
import it.unimib.cookery.repository.IngredientAndStepReopistory;
import it.unimib.cookery.utils.ResponseCallbackStepAndIngredients;

public class SingleRecipeActivity extends AppCompatActivity implements ResponseCallbackStepAndIngredients {

    //variabili per il caricamento dinamico degli steps e degli ingredienti
    private RecyclerView rcvSteps;
    private RecipeProcedureAdapter recipeProcedureAdapter;
    private RecyclerView rcvChips;
    private IngredientChipAdapter ingredientChipAdapter;

    //variabili per la modifica delle persone per la ricetta
    private ImageButton btnAdd;
    private ImageButton btnRemove;
    private TextView tvAmountPeople;
    private TextView tvQuantity;
    private ImageView recipeImage;
    private TextView textView_title_recipe;
    private TextView noSteps;
    private int nPerson;

    // varibile per moidificare la ricetta
    private String type;

    // variabile per ottenere info ricetta
    private String imageUrl;
    private int recipeId;
    private int servings;

    // oggetto per le costanti
    private Costants costants = new Costants();

    // bottoni per modifica e creazione ricetta
    private ImageButton modifyRecipe;
    private ImageButton deleteRecipe;

    // codice di test
    private ArrayList<String> stepRecived;
    private ArrayList<IngredientApi> ingredienteRecived;
    private IngredientAndStepReopistory ingredientAndStepReopistory =
            new IngredientAndStepReopistory(this);
    private ArrayList<IngredientApi> missingIngredients;
    private ArrayList<String> pantryIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        stepRecived = new ArrayList<>();
        ingredienteRecived = new ArrayList<>();

        // ottengo l'intent che ha avviato l'activity
        Intent intent = getIntent();

        // ottengo i riferimenti ai vari elementi della ui
        textView_title_recipe = findViewById(R.id.textView_title_recipe);
        tvAmountPeople = findViewById(R.id.tv_amount);
        btnRemove = findViewById(R.id.btn_remove);
        modifyRecipe = findViewById(R.id.ButtonEditRecipe);
        deleteRecipe = findViewById(R.id.ButtonDeleteRecipe);
        recipeImage = findViewById(R.id.image_single_recipe);
        btnAdd = findViewById(R.id.btn_add);
        noSteps = findViewById(R.id.voidStep);

        // setto il nome della ricetta
        textView_title_recipe.setText(intent.getStringExtra(costants.RECIPE_NAME));

        // ottengo parametri comuni dall'intent
        type = intent.getStringExtra(costants.TYPE);
        imageUrl = intent.getStringExtra(costants.RECIPE_IMAGE);
        recipeId = intent.getIntExtra(costants.RECIPE_ID, 0);
        pantryIngredients = intent.getStringArrayListExtra(costants.PANTRY);

        if(type.equals(costants.OTHER)) {
            stepRecived = intent.getStringArrayListExtra(costants.STEP_ARRAYLIST);
            ingredienteRecived = intent.getParcelableArrayListExtra(costants.INGREDIENT_ARRAYLIST);
            servings = intent.getIntExtra(costants.RECIPE_SERVINGS, 0);
            loadImage();
            setStepAdapter();
            createChips();
            setnPerson(servings);

            if(servings > 1)
                tvAmountPeople.setText(servings + costants.PEOPLE);
            else {
                tvAmountPeople.setText(servings + costants.PERSON);
                btnRemove.setEnabled(false);

            }

        }else if(type.equals(costants.READY_TO_COOCK)){
            missingIngredients = new ArrayList<>();
            missingIngredients = intent.getParcelableArrayListExtra(costants.MISSING_INGREDIENTS);
            loadImage();
            ingredientAndStepReopistory.getRecipeSteps(recipeId);
            ingredientAndStepReopistory.getRecipeIngredients(recipeId, false);

        }else {
            // ottengo i bottoni per modificare/eliminare la ricetta
            modifyRecipe.setVisibility(View.VISIBLE);
            deleteRecipe.setVisibility(View.VISIBLE);
            modifyRecipe.setClickable(true);
            deleteRecipe.setClickable(true);

            // le informazioni ottenute dal database

        }

        modifyRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("premuto", "modifyRecipe");
            }
        });
        deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // cancellare ricetta da database

                onBackPressed();

                Log.d("premuto", "deleteRecipe");
            }
        });





        btnAdd.setOnClickListener(v -> {
            setnPerson(servings + 1);
            if (servings == 1) {
                tvAmountPeople.setText(servings + costants.PERSON);
                btnRemove.setEnabled(false);
            } else {
                tvAmountPeople.setText(servings + costants.PEOPLE);
                btnRemove.setEnabled(true);
                createChips();
            }

        });
        btnRemove.setOnClickListener(v -> {
            if (servings > 1) {
                setnPerson(servings - 1);
                if (servings == 1) {
                    tvAmountPeople.setText(servings + costants.PERSON);
                    btnRemove.setEnabled(false);
                } else
                    tvAmountPeople.setText(servings + costants.PEOPLE);
            }
            createChips();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void loadImage(){

        if (imageUrl == null) {
            recipeImage.setImageResource(R.drawable.ic_baseline_broken_image_24);
        } else {

            // chiamate Api per ottenere ingredienti e step
            Glide.with(this).load(imageUrl).
                    into(recipeImage);
        }

    }


/*
    private void createChipsSubcard() {
        // inflate chips utilizzado il FlexboxLayoutManager per non avere l'impedimento delle colonne
        rcvChips = findViewById(R.id.rcv_chips);
        ingredientChipAdapter = new IngredientChipAdapter();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        rcvChips.setLayoutManager(flexboxLayoutManager);
        rcvChips.setFocusable(false);
        rcvChips.setNestedScrollingEnabled(false);

        if(pantryIngredients == null)
            ingredientChipAdapter.setData(ingredienteRecived);
        else
            ingredientChipAdapter.setDataPantry(ingredienteRecived, pantryIngredients);

        rcvChips.setAdapter(ingredientChipAdapter);

    }*/




    private void createChips() {
        // inflate chips utilizzado il FlexboxLayoutManager per non avere l'impedimento delle colonne
        rcvChips = findViewById(R.id.rcv_chips);
        ingredientChipAdapter = new IngredientChipAdapter();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        rcvChips.setLayoutManager(flexboxLayoutManager);
        rcvChips.setFocusable(false);
        rcvChips.setNestedScrollingEnabled(false);

        if(missingIngredients == null && type.equals(costants.READY_TO_COOCK) )

            ingredientChipAdapter.setData(ingredienteRecived);

        else if(missingIngredients != null)
            ingredientChipAdapter.setData(ingredienteRecived, missingIngredients);
        else
            ingredientChipAdapter.setDataPantry(ingredienteRecived, pantryIngredients);

        rcvChips.setAdapter(ingredientChipAdapter);

    }

    private void setStepAdapter() {

       if(stepRecived.size()==0)
           noSteps.setVisibility(View.VISIBLE);
       else {
           //Inflate degli steps procedimento della ricetta
           rcvSteps = findViewById(R.id.rcv_steps);
           recipeProcedureAdapter = new RecipeProcedureAdapter();
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
           rcvSteps.setLayoutManager(linearLayoutManager);
           rcvSteps.setFocusable(false);
           rcvSteps.setNestedScrollingEnabled(false);
           recipeProcedureAdapter.setData(stepRecived);
           rcvSteps.setAdapter(recipeProcedureAdapter);
       }
    }


    private void setnPerson(int n) {
        for (int i = 0; i < ingredienteRecived.size(); i++) {
            double qBase = ingredienteRecived.get(i).getAmount() / servings;
            // da sistemare la precisione del double in qualche modo

            if (ingredienteRecived.get(i).getUnit().equals("") ||
                    ingredienteRecived.get(i).getUnit().equalsIgnoreCase("large") ||
                    ingredienteRecived.get(i).getUnit().equalsIgnoreCase("serving") ||
                    ingredienteRecived.get(i).getUnit().equalsIgnoreCase("medium")
            )
                ingredienteRecived.get(i).setAmount(Math.round(qBase * n));
            else
                ingredienteRecived.get(i).setAmount(Math.round((qBase * n) * 100.0) / 100.0);


            Log.d("test", "nome:" + ingredienteRecived.get(i).getName() + "- quantita:" + ingredienteRecived.get(i).getAmount());
        }
        servings = n;
    }


    @Override
    public void onResponseRecipeSteps(List<StepApi> steps) {

        ArrayList<String> stringSteps = new ArrayList<>();


        if(steps != null) {

            for (int i = 0; i < steps.size(); i++)
                stringSteps.add(steps.get(i).getStep());
        }

      /*  for (StepApi st : steps)
            stringSteps.add(st.getStep());*/


        stepRecived = stringSteps;
        setStepAdapter();


    }

    @Override
    public void onResponseRecipeIngredients(List<IngredientApi> ingredients, int servings) {

        this.servings = servings;
        ingredienteRecived = (ArrayList<IngredientApi>) ingredients;
        createChips();
        setnPerson(servings);
        if (servings > 1)
            tvAmountPeople.setText(servings + costants.PEOPLE);
        else {
            tvAmountPeople.setText(servings + costants.PERSON);
            btnRemove.setEnabled(false);
        }
    }

    @Override
    public void onFailureIngredientAndStep(int errorMessage) {

        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT).show();
    }
}