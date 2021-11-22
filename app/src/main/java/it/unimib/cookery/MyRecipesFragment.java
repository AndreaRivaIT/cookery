package it.unimib.cookery;

import android.os.Bundle;
import android.content.DialogInterface;
import java.util.Collections;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import com.google.android.material.chip.Chip;

import java.util.ArrayList;


public class MyRecipesFragment extends Fragment {


   /* robe di comodo */
   private String nomeRicettaTest =" pasta al forno";
    private  static  final  String TAG = "premuto";

    /* fine robe di comodo */

    /* costanti */

    public static final String FILTER0 = "stuzzichini";
    public static final String FILTER1 = "primi";
    public static final String FILTER2 = "secondi";
    public static final String FILTER3 = "contorni ";
    public static final String FILTER4 = "dolci";




    /* dichiaro un oggetto di tipoGridView */
    private GridView  myRecipiesGridView;

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

          Recipe prova = new Recipe();
          recipeArrayList=prova.getArrayList();


           /* creo l'oggetto adapter e lo inizializzo con la view corrente e con l'array list*/
         adapter = new AdapterClass(getContext(), recipeArrayList); /* al posto di recipeArrayList si metterà il risultato della query al database */

        /*associo alla gridView l'adapter creato sopra */
       myRecipiesGridView.setAdapter(adapter);



        /* serve per intercettare l'oggetto premuto */
        myRecipiesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* stampa di debug */
                Log.d(TAG, "PREMUTO "+recipeArrayList.get(position));
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
                recipeArrayList.add(new Recipe("prova aggiunta", "dolci", R.drawable.ic_baseline_add_24));
                adapter.addRecipe();


            }
        });

       // -- fine codice chip --



        // --inizio codice button filter --

        // array di nomi che verranno mostrati sulla dialog
        String[] filterArray = {FILTER0,FILTER1,FILTER2,FILTER3,FILTER4};

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
                builder.setTitle("Select recipe type");

                // messo a false non permette di uscire dalla dialog se non premendo sul tasto close
                builder.setCancelable(false);

                // creo il menu a scelta multipla
                builder.setMultiChoiceItems(filterArray, selectedFilter, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        String filter = "";
                        switch (i){
                            case 0: filter=FILTER0; break;
                            case 1: filter=FILTER1; break;
                            case 2: filter=FILTER2; break;
                            case 3: filter=FILTER3; break;
                            case 4: filter=FILTER4; break;

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

        Log.d("on destroy", "chiamato on destroy fragment");


    }
}