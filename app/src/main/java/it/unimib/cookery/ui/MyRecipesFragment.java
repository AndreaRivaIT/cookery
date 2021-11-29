package it.unimib.cookery.ui;

import android.os.Bundle;
import android.content.DialogInterface;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import it.unimib.cookery.R;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.adapters.AdapterClass;


public class MyRecipesFragment extends Fragment {


    /* robe di comodo */
    private String nomeRicettaTest = " pasta al forno";
    private static final String TAG = "premuto";

    /* fine robe di comodo */

    /* ottengo le stringhe costanti per dialog filtri */
    private Costants myRecipeCostants = new Costants();


    /* dichiaro un oggetto di tipoGridView */
    private GridView myRecipiesGridView;

    /* dichiaro un oggetto di tipo adapter */
    private AdapterClass adapter;

    /* dichiaro un oggetto di tipo SearchView */
    private SearchView recipiesSearch;

    /* dichiaro un oggetto di tipo chip*/
    private Chip chip;

    /* dichiaro un oggetto di tipo textButton*/
    private Button buttonFilter;

    /* crea l'array list di elementi da mostrare nell gridView */
    ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

    /* array list per i filtri */
    ArrayList<String> filterList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* creo un elemento di tipo view */
        View view = inflater.inflate(R.layout.fragment_my_recipes, container, false);

        // --- inizio codice gridview --

        /* ottengo la Gridview */
        myRecipiesGridView = view.findViewById(R.id.gridView);


        /* aggiungo gli elementi alla gridView */
        // recipeArrayList = risultato query database


        //  solo per prova //

        recipeArrayList.add(new Recipe("pasta al forno", "First course", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("risotto", "First course", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("arrosto", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));
        recipeArrayList.add(new Recipe("parmigina", "Main meal", R.drawable.spoonacular));


        // fine codice solo per prova //




        /* creo l'oggetto adapter e lo inizializzo con la view corrente e con l'array list*/
        adapter = new AdapterClass(getContext(), recipeArrayList); /* al posto di recipeArrayList si metterà il risultato della query al database */

        /*associo alla gridView l'adapter creato sopra */
        myRecipiesGridView.setAdapter(adapter);



        /* serve per intercettare l'oggetto premuto */
        myRecipiesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* stampa di debug */
                Log.d(TAG, "PREMUTO " + recipeArrayList.get(position).getName());


            }
        });

        //-- fine codice grid view --


        //-- inizio codice searchView --

        /* ottengo la search view */
        recipiesSearch = view.findViewById(R.id.searchViewMyRecipes);

        /* listener per la query */

        recipiesSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        //-- fine codice searchView--


        // -- inizio codice chip --

        /* ottengo l'elemento chip */
        chip = view.findViewById(R.id.addChip);


        /* listener dell'oggetto chip*/
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("premuto", "premuto chip add");

                /* fare update database*/

                // aggiungo la ricetta e la mostro sulla gridview
                recipeArrayList.add(new Recipe("aggiunta", "Desserts", R.drawable.ic_baseline_add_24));

                // adapter=new AdapterClass(getContext(), recipeArrayList);
                myRecipiesGridView.setAdapter(adapter);


            }
        });

        // -- fine codice chip --


        // --inizio codice button filter --

        // array di nomi che verranno mostrati sulla dialog
        String[] filterArray = {myRecipeCostants.FILTER0, myRecipeCostants.FILTER1, myRecipeCostants.FILTER2,
                myRecipeCostants.FILTER3, myRecipeCostants.FILTER4};

        // serve per salvare se un elemento è selezionato o meno
        boolean[] selectedFilter = new boolean[filterArray.length];


        /* ottengo l'oggetto button */
        buttonFilter = view.findViewById(R.id.buttonFilter);


        /* listener dell'oggetto buttonFilter */
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("premuto", "premuto button filter");

                // inizializza l'alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // setta il titolo
                builder.setTitle(myRecipeCostants.dialogTitle);

                // messo a false non permette di uscire dalla dialog se non premendo sul tasto close
                builder.setCancelable(false);

                // creo il menu a scelta multipla
                builder.setMultiChoiceItems(filterArray, selectedFilter, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        String filter = "";
                        switch (i) {
                            case 0:
                                filter = myRecipeCostants.FILTER0;
                                break;
                            case 1:
                                filter = myRecipeCostants.FILTER1;
                                break;
                            case 2:
                                filter = myRecipeCostants.FILTER2;
                                break;
                            case 3:
                                filter = myRecipeCostants.FILTER3;
                                break;
                            case 4:
                                filter = myRecipeCostants.FILTER4;
                                break;

                        }


                        // check condition
                        if (b) {
                            // quando la checkbox è selezionata
                            // creo l'elemento da aggiungere
                            // lo aggiungo
                            filterList.add(filter);


                        } else {
                            // quando deseleziono la checkbox rimuovo l'oggetto dalla lista
                            filterList.remove(filter);

                        }
                    }
                });

                // crea bottone ok
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // se premuto ok far partire la ricerca
                        adapter.applyFilter(filterList);

                    }

                });

                // crea bottone close
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // se premuto colose chiude l'alert dialog
                        dialogInterface.dismiss();
                    }
                });


                // mostra l'alert dialog
                builder.show();


            }
        });


        // -- fine codice button filter --


        // Inflate the layout for this fragment
        return view;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}