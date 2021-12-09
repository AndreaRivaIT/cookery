package it.unimib.cookery.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayoutManager;

import it.unimib.cookery.R;
import it.unimib.cookery.adapters.AdapterClass;
import it.unimib.cookery.adapters.IngredientChipAdapter;
import it.unimib.cookery.adapters.RecipeProcedureAdapter;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.Ingredient;
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

    private  RecipeStep step1;
    private  RecipeStep step2;
    private  RecipeStep step3;
    private  RecipeStep step4;
    private  RecipeStep step5;


    //variabili per il caricamento dinamico degli steps e degli ingredienti
    private RecyclerView rcvSteps;
    private RecipeProcedureAdapter recipeProcedureAdapter;
    private RecyclerView rcvChips;
    private IngredientChipAdapter  ingredientChipAdapter;

    //variabili per la modifica delle persone per la ricetta
    private ImageButton btnAdd;
    private ImageButton btnRemove;
    private TextView tvAmountPeople;
    private TextView tvQuantity;
    private ImageView recipeImage;
    private int nPerson;

    // varibile per moidificare la ricetta
    private String editable;

    // oggetto per le costanti
    private Costants costants = new Costants();


    // bottoni per modifica e creazione ricetta
    private ImageButton modifyRecipe;
    private ImageButton deleteRecipe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);
        setRicettaAppoggio();
        createChips();

        // ottengo l'intent che ha avviato l'activity
        Intent intent = getIntent();


        // ottengo l'oggetto textView interessato
        TextView textView_title_recipe = findViewById(R.id.textView_title_recipe);
        // setto il nome della ricetta
        textView_title_recipe.setText(intent.getStringExtra(costants.RECIPE_NAME));

        recipeImage = findViewById(R.id.image_single_recipe);

        // ottengo la stringa che mi dice se la ricetta è modificabile
        editable = intent.getStringExtra(costants.EDITABLE);

        modifyRecipe = findViewById(R.id.ButtonEditRecipe);
        deleteRecipe = findViewById(R.id.ButtonDeleteRecipe);
        // non trova i bottoni non so perchè
        // se la ricetta è modificabile mostro i bottoni
        if (editable.equals("true")) {
            // ottengo i bottoni per modificare/eliminare la ricetta
            modifyRecipe.setVisibility(View.VISIBLE);
            deleteRecipe.setVisibility(View.VISIBLE);
            modifyRecipe.setClickable(true);
            deleteRecipe.setClickable(true);
            // caricare immagine ottenuta da query database

        }
         else {

             // caricherà immagine ottenuta da Api

            Glide.with(this).load("https://spoonacular.com/recipeImages/716429-312x231.jpg").
                    into(recipeImage);
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

                // non so come tornare indetro al fragment


                Log.d("premuto", "deleteRecipe");
            }
        });


        //Inflate degli steps procedimento della ricetta
        rcvSteps = findViewById(R.id.rcv_steps);
        recipeProcedureAdapter = new RecipeProcedureAdapter();
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        rcvSteps.setLayoutManager(linearLayoutManager);
        rcvSteps.setFocusable(false);
        rcvSteps.setNestedScrollingEnabled(false);
        recipeProcedureAdapter.setData(recipe.getStepsList());
        rcvSteps.setAdapter(recipeProcedureAdapter);

        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        tvAmountPeople = findViewById(R.id.tv_amount);





        btnAdd.setOnClickListener(v -> {
            recipe.setnPerson(recipe.getnPerson() + 1);
            if ( recipe.getnPerson() == 1){
                tvAmountPeople.setText(recipe.getnPerson() +" Persona");
            }
            else{
                tvAmountPeople.setText(recipe.getnPerson() +" Persone");
                createChips();
            }

        });
        btnRemove.setOnClickListener(v -> {
            if(recipe.getnPerson() > 1){
                recipe.setnPerson(recipe.getnPerson() - 1);;
                if (recipe.getnPerson() == 1)
                    tvAmountPeople.setText(recipe.getnPerson() +" Persona");
                else
                    tvAmountPeople.setText(recipe.getnPerson() +" Persone");
            }
            createChips();
        });








    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void createChips(){
        // inflate chips utilizzado il FlexboxLayoutManager per non avere l'impedimento delle colonne
        rcvChips = findViewById(R.id.rcv_chips);
        ingredientChipAdapter = new IngredientChipAdapter();
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager( this);
        rcvChips.setLayoutManager(flexboxLayoutManager);
        rcvChips.setFocusable(false);
        rcvChips.setNestedScrollingEnabled(false);
        ingredientChipAdapter.setData(recipe.getIngredientList());
        rcvChips.setAdapter(ingredientChipAdapter);

    }



    //da cancellare solo per lo sviluppo
    public void setRicettaAppoggio(){
        //inizializare gli oggetto ricetta, riceverà id e poi verra fatta una chiamata api con ID
        recipe = new Recipe("Pasta al Pomodoro","First course", 2);
        // inizializzazione oggetti ingredienti
        pasta = new Ingredient(1,"pasta",180);
        pomodoro = new Ingredient(2,"pomodoro",80);
        carciofi = new Ingredient(3,"carciofi",120);
        sale = new Ingredient(4,"sale",4);
        pepeA = new Ingredient(5,"pepe",8);
        pepeB = new Ingredient(5,"pepe",8);
        besciamella = new Ingredient(6, "besciamella", 158);

        step1 = new RecipeStep(1,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step2 = new RecipeStep(2,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step3 = new RecipeStep(3,"Lorem Ipsum is simply dummy text oLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.f the printing and typesetting industry.");
        step4 = new RecipeStep(4,"Lorem Ipsum is simply dummy text of the printing and typesettiLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.ng industry.");
        step5 = new RecipeStep(5,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
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
}