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

import java.util.ArrayList;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.adapters.RecipeProcedureAdapter;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeStep;

public class SingleRecipeActivity extends AppCompatActivity {
    public Recipe recipe;
    private Ingredient pasta;
    private Ingredient pomodoro;
    private Ingredient carciofi;
    private Ingredient sale;
    private Ingredient pepeA;
    private Ingredient pepeB;
    private Ingredient besciamella;

    private RecipeStep step1;
    private RecipeStep step2;
    private RecipeStep step3;
    private RecipeStep step4;
    private RecipeStep step5;

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
    private int nPerson;

    // varibile per moidificare la ricetta
    private String editable;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);
        setRicettaAppoggio();

        stepRecived = new ArrayList<>();
        ingredienteRecived = new ArrayList<>();


        // ottengo l'intent che ha avviato l'activity
        Intent intent = getIntent();

        // ottengo l'oggetto textView interessato
        TextView textView_title_recipe = findViewById(R.id.textView_title_recipe);

        // setto il nome della ricetta
        textView_title_recipe.setText(intent.getStringExtra(costants.RECIPE_NAME));

        recipeImage = findViewById(R.id.image_single_recipe);

        // ottengo la stringa che mi dice se la ricetta è modificabile
        editable = intent.getStringExtra(costants.EDITABLE);
        imageUrl = intent.getStringExtra(costants.RECIPE_IMAGE);
        servings = intent.getIntExtra(costants.RECIPE_SERVINGS, 0);

        // da controllare
        recipeId = intent.getIntExtra(costants.RECIPE_ID, 0);

        /*codice di test funzionante */

        stepRecived = intent.getStringArrayListExtra(costants.STEP_ARRAYLIST);
        ingredienteRecived = intent.getParcelableArrayListExtra(costants.INGREDIENT_ARRAYLIST);

        Log.d("servings", "" + servings);

        tvAmountPeople = findViewById(R.id.tv_amount);
        btnRemove = findViewById(R.id.btn_remove);

        setnPerson(servings);
        if (servings > 1)
            tvAmountPeople.setText(servings + costants.PEOPLE);
        else {
            tvAmountPeople.setText(servings + costants.PERSON);
            btnRemove.setEnabled(false);
        }


        createChips();


        Log.d("SingleRecipe", "size " + ingredienteRecived.size());

        for (IngredientApi ing : ingredienteRecived) {
            Log.d("SingleRecipe", "" + ing.toString());
        }


        //  Log.d("SingleRecipe", ""+stepRecived.get(0));


        modifyRecipe = findViewById(R.id.ButtonEditRecipe);
        deleteRecipe = findViewById(R.id.ButtonDeleteRecipe);

        // se la ricetta è modificabile mostro i bottoni
        if (editable.equals("true")) {
            // ottengo i bottoni per modificare/eliminare la ricetta
            modifyRecipe.setVisibility(View.VISIBLE);
            deleteRecipe.setVisibility(View.VISIBLE);
            modifyRecipe.setClickable(true);
            deleteRecipe.setClickable(true);
            // caricare immagine ottenuta da query database


        } else {

            if (imageUrl == null) {
                recipeImage.setImageResource(R.drawable.ic_baseline_broken_image_24);
            } else {

                // chiamate Api per ottenere ingredienti e step
                Glide.with(this).load(imageUrl).
                        into(recipeImage);
            }
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


        //Inflate degli steps procedimento della ricetta
        rcvSteps = findViewById(R.id.rcv_steps);
        recipeProcedureAdapter = new RecipeProcedureAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSteps.setLayoutManager(linearLayoutManager);
        rcvSteps.setFocusable(false);
        rcvSteps.setNestedScrollingEnabled(false);
        recipeProcedureAdapter.setData(stepRecived);
        rcvSteps.setAdapter(recipeProcedureAdapter);

        btnAdd = findViewById(R.id.btn_add);


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

    private void createChips() {
        // inflate chips utilizzado il FlexboxLayoutManager per non avere l'impedimento delle colonne
        rcvChips = findViewById(R.id.rcv_chips);
        ingredientChipAdapter = new IngredientChipAdapter();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        rcvChips.setLayoutManager(flexboxLayoutManager);
        rcvChips.setFocusable(false);
        rcvChips.setNestedScrollingEnabled(false);
        ingredientChipAdapter.setData(ingredienteRecived);
        rcvChips.setAdapter(ingredientChipAdapter);

    }


    //da cancellare solo per lo sviluppo
    public void setRicettaAppoggio() {
        //inizializare gli oggetto ricetta, riceverà id e poi verra fatta una chiamata api con ID
        recipe = new Recipe("Pasta al Pomodoro", "First course", 2);
        // inizializzazione oggetti ingredienti
        pasta = new Ingredient(1, "pasta", 180);
        pomodoro = new Ingredient(2, "pomodoro", 80);
        carciofi = new Ingredient(3, "carciofi", 120);
        sale = new Ingredient(4, "sale", 4);
        pepeA = new Ingredient(5, "pepe", 8);
        pepeB = new Ingredient(5, "pepe", 8);
        besciamella = new Ingredient(6, "besciamella", 158);

        step1 = new RecipeStep(1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step2 = new RecipeStep(2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step3 = new RecipeStep(3, "Lorem Ipsum is simply dummy text oLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.f the printing and typesetting industry.");
        step4 = new RecipeStep(4, "Lorem Ipsum is simply dummy text of the printing and typesettiLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.ng industry.");
        step5 = new RecipeStep(5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        //aggiunta degli ingredienti alla ricetta
        recipe.setStepsList(step1);
        recipe.setStepsList(step2);
        recipe.setStepsList(step3);
        recipe.setStepsList(step4);
        recipe.setStepsList(step5);
        recipe.setIngredientList(pasta);
        recipe.setIngredientList(pomodoro);
        recipe.setIngredientList(carciofi);
        recipe.setIngredientList(sale);
        recipe.setIngredientList(pepeA);
        recipe.setIngredientList(pepeB);
        recipe.setIngredientList(besciamella);
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


}