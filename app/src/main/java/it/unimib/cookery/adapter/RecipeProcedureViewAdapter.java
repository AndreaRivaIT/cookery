package it.unimib.cookery.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.model.RecipeStep;

public class RecipeProcedureViewAdapter extends ArrayAdapter<RecipeStep> {

    private List<RecipeStep> stepsArrayList;
    public int k=0;
    public RecipeProcedureViewAdapter(@NonNull Context context, int resource, @NonNull List<RecipeStep> objects) {
        super(context, resource, objects);
        stepsArrayList = objects;
    }

    // permette di definire il file di layout di cui vogliamo fare l'inflate
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_recipe_item,parent ,false);
        }
        k++;
        Log.d("test", " k = "+ k + " position = " + position + " - procedimento = " + stepsArrayList.get(position).getDescription());

        ((TextView)convertView.findViewById(R.id.textView_step)).setText("Step "+ stepsArrayList.get(position).getnStep());
        ((TextView)convertView.findViewById(R.id.textView_description_step)).setText(stepsArrayList.get(position).getDescription());

        return convertView;
    }

}