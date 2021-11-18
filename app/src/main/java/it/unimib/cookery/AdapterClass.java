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

    public AdapterClass(@NonNull Context context, ArrayList<Recipe> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }
        /* ottengo la posizione dell'oggetto */
        Recipe recipe = getItem(position);
        /* associa la textView della card per le ricette */
        TextView textViewCardRicetta = listitemView.findViewById(R.id.TextViewCardRicetta);
        /* associa l' ImageView della card per le ricette */
        ImageView imageViewCardRicetta = listitemView.findViewById(R.id.ImageViewCardRicetta);
        /* setto il nome della ricetta nella card */
        textViewCardRicetta.setText(recipe.getName());
        /* setto l'immagine nella card */
        imageViewCardRicetta.setImageResource(recipe.getImgId());
        return listitemView;
    }

}
