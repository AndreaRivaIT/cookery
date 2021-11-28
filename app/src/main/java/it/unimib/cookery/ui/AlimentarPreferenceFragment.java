package it.unimib.cookery.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import it.unimib.cookery.R;


public class AlimentarPreferenceFragment extends Fragment {

    /* dichiaro gli oggetti checkbox */
    private CheckBox gluten, lactose, nuts, vegetarian, vegan, pescetarian;

    private ArrayList<String> intolleranceChoosen = new ArrayList<>();
    private ArrayList<String> preferencesChoosen = new ArrayList<>();

    /* stringhe costanti per check box */

     private static final String GLUTEN = "Gluten";
     private static final String LACTOSE = "Lactose";
     private static final String NUTS = "Nuts";
     private static final String VEGAN= "Vegan";
     private static final String VEGETARIAN = "Vegetarian";
     private static final String PESCETARIAN = "Pescetarian";


    public AlimentarPreferenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alimentar_preference, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        gluten = view.findViewById(R.id.checkBoxIntolleranceGluten);
        lactose = view.findViewById(R.id.checkBoxIntolleranceLactose);
        nuts = view.findViewById(R.id.checkBoxIntolleranceNuts);
        vegetarian = view.findViewById(R.id.checkBoxPreferencesVegetarian);
        vegan = view.findViewById(R.id.checkboxPreferencesVegan);
        pescetarian = view.findViewById(R.id.checkBoxPreferencesPescetarian);


        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("premuto", "premuto bottone save");

                // svuoto gli array list che memorizzano intolleranze e preferenze alimentari
                intolleranceChoosen.clear();
                preferencesChoosen.clear();

                // se gli elementi sono selezionati gli aggiungo agli array list

                if (gluten.isChecked())
                    intolleranceChoosen.add(GLUTEN);

                if (lactose.isChecked())
                    intolleranceChoosen.add(LACTOSE);

                if (nuts.isChecked())
                    intolleranceChoosen.add(NUTS);

                if (vegan.isChecked())
                    preferencesChoosen.add(VEGAN);

                if (vegetarian.isChecked())
                    preferencesChoosen.add(VEGETARIAN);

                if (pescetarian.isChecked())
                    preferencesChoosen.add(PESCETARIAN);


                for (String s : intolleranceChoosen)
                    Log.d("stampa", "intollerance " + s);

                for (String s : preferencesChoosen)
                    Log.d("stampa", "preferences " + s);


            }


        });

    }
}