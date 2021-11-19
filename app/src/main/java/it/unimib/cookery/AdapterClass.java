package it.unimib.cookery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class AdapterClass extends ArrayAdapter<Recipe>{

    private ArrayList<Recipe> RecipeArrayList;



    public AdapterClass(@NonNull Context context, ArrayList<Recipe> recipesArrayList) {
        super(context, 0, recipesArrayList);

        RecipeArrayList = recipesArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       //View listitemView = convertView;
        if (convertView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        }

        /* ottiene la text view dell'elemento dell'array list e ne setta il nome*/
        ((TextView) convertView.findViewById(R.id.TextViewCardRicetta))
        .setText(RecipeArrayList.get(position).getName());

        /* ottiene l'image view dell'elemento dell'array list e ne setta l'immagine */
        ((ImageView) convertView.findViewById(R.id.ImageViewCardRicetta))
                .setImageResource(RecipeArrayList.get(position).getImgId());

       /*
        /* ottengo la posizione dell'oggetto */
        //Recipe recipe = getItem(position);
        /* associa la textView della card per le ricette */
       // TextView textViewCardRicetta = convertView.findViewById(R.id.TextViewCardRicetta);
        /* associa l' ImageView della card per le ricette */
       // ImageView imageViewCardRicetta = convertView.findViewById(R.id.ImageViewCardRicetta);
        /* setto il nome della ricetta nella card */
        //textViewCardRicetta.setText(recipe.getName());
        /* setto l'immagine nella card */
        //imageViewCardRicetta.setImageResource(recipe.getImgId());
        return convertView;
    }

}
