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

    private ArrayList<Recipe> listdata;
    private ArrayList<Recipe> filterData;





    public AdapterClass(@NonNull Context context, ArrayList<Recipe> recipesArrayList) {
        super(context, 0, recipesArrayList);

        listdata = recipesArrayList;
        filterData = new ArrayList<>(listdata);

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
        .setText(listdata.get(position).getName());

        /* ottiene l'image view dell'elemento dell'array list e ne setta l'immagine */
        ((ImageView) convertView.findViewById(R.id.ImageViewCardRicetta))
                .setImageResource(listdata.get(position).getImgId());

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


    public Filter getFilter(){
        return filterNotification;
    }


    private Filter filterNotification = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                ArrayList<Recipe> filterList = new ArrayList<>();

                if(constraint == null || constraint.length() == 0){

                    filterList.addAll(filterData);

                } else {

                    String searchStr = constraint.toString().toLowerCase().trim();
                    ArrayList<Recipe> resultData = new ArrayList<>();

                    for(Recipe recipe: filterData) {
                       String nameLowerCase= recipe.getName().toLowerCase();
                        if (nameLowerCase.contains(searchStr)){
                            filterList.add(recipe);

                        }

                    }

                }

                FilterResults results = new FilterResults();
                results.values = filterList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                listdata.clear();
                listdata.addAll((ArrayList<Recipe>)filterResults.values);
                notifyDataSetChanged();
            }
        };




    }

