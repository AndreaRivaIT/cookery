package it.unimib.cookery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class MyRecipesFragment extends Fragment {

    /* dichiaro un oggetto di tipoGridView */
    private GridView  myRecipiesGridView;
    private String nomeRicettaTest =" pasta al forno";
    private  static  final  String TAG = "premuto";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /* creo un elemento di tipo view */
        View view = inflater.inflate(R.layout.fragment_my_recipes, container, false);

        /* ottengo la view */
        myRecipiesGridView = view.findViewById(R.id.gridView);

        /* crea l'array list di elementi da mostrare nell gridView */
        ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

        /* aggiungo gli elementi alla gridView */
        for(int i=0; i<20; i++){
            recipeArrayList.add(new Recipe(nomeRicettaTest, R.drawable.ic_baseline_add_24));
        }


        /* creo l'oggetto adapter e lo inizializzo con la view corrente e con l'array list*/
        AdapterClass adapter = new AdapterClass(getContext(), recipeArrayList);

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

        // Inflate the layout for this fragment
        return view;




    }


    /*quando la vista è stata creata */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }


}