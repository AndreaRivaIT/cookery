package it.unimib.cookery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import java.util.ArrayList;


public class MyRecipesFragment extends Fragment {


   /* robe di comodo */
   private String nomeRicettaTest =" pasta al forno";
    private  static  final  String TAG = "premuto";

    /* fine robe di comodo */


    /* dichiaro un oggetto di tipoGridView */
    private GridView  myRecipiesGridView;

    /* dichiaro un oggetto di tipo adapter */
    private AdapterClass adapter;

    /* dichiaro un oggetto di tipo SearchView */
    private SearchView recipiesSearch;


    /* crea l'array list di elementi da mostrare nell gridView */
    ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();




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

           for (int i = 0; i <= 20; i++) {
               if (i % 2 == 0)
                   recipeArrayList.add(new Recipe(nomeRicettaTest, R.drawable.spoonacular));
               else
                   recipeArrayList.add(new Recipe("arrosto", R.drawable.spoonacular));
           }


           /* creo l'oggetto adapter e lo inizializzo con la view corrente e con l'array list*/
         adapter = new AdapterClass(getContext(), recipeArrayList);

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

        // Inflate the layout for this fragment
        return view;

    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("on destroy", "chiamato on destroy fragment");


    }
}