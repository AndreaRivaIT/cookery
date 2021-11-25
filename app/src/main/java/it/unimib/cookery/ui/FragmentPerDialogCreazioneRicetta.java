package it.unimib.cookery.ui;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import it.unimib.cookery.R;


public class FragmentPerDialogCreazioneRicetta extends Fragment {


    /* dichiaro le variabili a cui associerò i bottoni */
    private Button aggiungiIngrediente;
    private Button aggiungiStep;

    /* dichiaro una una variabile per ottenere la quantità dalla dialog */
    private int quantità;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_per_dialog_creazione_ricetta, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* da mettere commenti */

        aggiungiIngrediente = view.findViewById(R.id.buttonIngrediente);

        aggiungiIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog ingredientDialog = new Dialog(getContext());

                ingredientDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                ingredientDialog.setCancelable(false);

                ingredientDialog.setContentView(R.layout.layout_ingredient_dialog);

                TextView ingredientName = ingredientDialog.findViewById(R.id.IngredientName);

                ingredientName.setText("nome ingrediente");


                EditText editText = ingredientDialog.findViewById(R.id.IngredientEditText);

                Log.d("debug", "" + editText);

                Button addButton = ingredientDialog.findViewById(R.id.addButton);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if ((Integer.parseInt(editText.getText().toString())) > 0)
                            quantità = Integer.parseInt(editText.getText().toString());
                        else
                            Toast.makeText(getContext(), "impossible to insert this quantity", Toast.LENGTH_SHORT).show();

                        Log.d("debug", "quantità" + quantità);

                    }
                });


                Button deleteButton = ingredientDialog.findViewById(R.id.deleteButton);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ingredientDialog.dismiss();

                        Log.d("debug", "" + quantità);
                    }
                });

                ingredientDialog.show();


            }
        });


    }
}