package it.unimib.cookery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Locale;


public class AdapterClass extends ArrayAdapter<Recipe> implements Filterable {

    private ArrayList<Recipe> RecipeArrayList;
    private ArrayList<Recipe> RecipeArrayListFiltered;



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


    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if(constraint == null || constraint.length() == 0){

                    filterResults.count = RecipeArrayList.size();
                    filterResults.values = RecipeArrayList;
                } else {

                    String searchStr = constraint.toString().toLowerCase();
                    ArrayList<Recipe> resultData = new ArrayList<>();

                    for(Recipe recipe: RecipeArrayList) {
                       String nameLowerCase= recipe.getName().toLowerCase();
                        if (nameLowerCase.contains(searchStr)){
                            resultData.add(recipe);

                        }
                        filterResults.count = resultData.size();
                        filterResults.values= resultData;
                    }

                }



                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                RecipeArrayListFiltered = (ArrayList<Recipe>) results.values;
                notifyDataSetChanged();
            }
        };


        return filter;

    }
}
