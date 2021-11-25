package it.unimib.cookery.adapters;

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

import it.unimib.cookery.R;
import it.unimib.cookery.models.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private ArrayList<Recipe> recipeArrayList;

    public RecipeAdapter(@NonNull Context context, ArrayList<Recipe> recipeArrayList) {
        super(context, 0, recipeArrayList);
        this.recipeArrayList = recipeArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.textView)).setText(recipeArrayList.get(position).getName());
        ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(recipeArrayList.get(position).getImageId());

        return convertView;
    }

}
