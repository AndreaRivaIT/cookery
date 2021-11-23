package it.unimib.cookery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import it.unimib.cookery.R;
import it.unimib.cookery.adapter.RecipeProcedureAdapter;
import it.unimib.cookery.adapter.RecipeProcedureViewAdapter;
import it.unimib.cookery.model.Ingredient;
import it.unimib.cookery.model.Recipe;
import it.unimib.cookery.model.RecipeStep;

public class SingleRecipeActivity extends AppCompatActivity {
    private Recipe recipe;
    private Ingredient pasta;
    private Ingredient pomodoro;
    private Ingredient carciofi;
    private Ingredient sale;
    private Ingredient pepe;

    private  RecipeStep step1;
    private  RecipeStep step2;
    private  RecipeStep step3;
    private  RecipeStep step4;
    private  RecipeStep step5;

    //indianoo power
    private RecyclerView rcvSteps;


    private RecipeProcedureAdapter recipeProcedureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        //inizializare gli oggetto ricetta, riceverà id e poi verra fatta una chiamata api con ID
        recipe = new Recipe("Pasta al Pomodoro",5);
        // inizializzazione oggetti ingredienti
        pasta = new Ingredient(1,"pasta",180);
        pomodoro = new Ingredient(2,"pomodoro",80);
        carciofi = new Ingredient(3,"carciofi",120);
        sale = new Ingredient(4,"sale",4);
        pepe = new Ingredient(5,"pepe",8);

        step1 = new RecipeStep(1,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step2 = new RecipeStep(2,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        step3 = new RecipeStep(3,"Lorem Ipsum is simply dummy text oLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.f the printing and typesetting industry.");
        step4 = new RecipeStep(4,"Lorem Ipsum is simply dummy text of the printing and typesettiLorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.ng industry.");
        step5 = new RecipeStep(5,"Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        //aggiunta degli ingredienti alla ricetta
        recipe.addIngredient(pasta);
        recipe.addIngredient(pomodoro);
        recipe.addIngredient(carciofi);
        recipe.addIngredient(sale);
        recipe.addIngredient(pepe);

        recipe.addStep(step1);
        recipe.addStep(step2);
        recipe.addStep(step3);
        recipe.addStep(step4);
        recipe.addStep(step5);

        recipe.setStepsList(step1);
        recipe.setStepsList(step2);
        recipe.setStepsList(step3);
        recipe.setStepsList(step4);
        recipe.setStepsList(step5);


        //List view Ma non va bene
        //ListView listView =  findViewById(R.id.listViewSteps);
        //listView.setFocusable(false);
        //listView.setNestedScrollingEnabled(false);
        //CardView cardView =findViewById(R.id.cardSteps);
        //RecipeProcedureViewAdapter recipeProcedureViewAdapter = new RecipeProcedureViewAdapter(getBaseContext(),
        //android.R.layout.simple_list_item_1, recipe.getStepsList());
        //listView.setAdapter(recipeProcedureViewAdapter);
        //cardView(recipeProcedureViewAdapter);

        //Indianoooo power
        rcvSteps = findViewById(R.id.rcv_steps);


        //category
        recipeProcedureAdapter = new RecipeProcedureAdapter();
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //rcvSteps.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        rcvSteps.setLayoutManager(linearLayoutManager);
        rcvSteps.setFocusable(false);
        rcvSteps.setNestedScrollingEnabled(false);

        recipeProcedureAdapter.setData(recipe.getStepsList());
        rcvSteps.setAdapter(recipeProcedureAdapter);


    }
}