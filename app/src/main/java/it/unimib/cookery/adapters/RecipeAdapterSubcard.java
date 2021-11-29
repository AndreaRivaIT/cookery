package it.unimib.cookery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import it.unimib.cookery.R;
import it.unimib.cookery.models.Recipe;

public class RecipeAdapterSubcard extends RecyclerView.Adapter<RecipeAdapterSubcard.Viewholder>{

    private Context context;
    private ArrayList<Recipe> recipeArrayList;

    public RecipeAdapterSubcard(Context context, ArrayList<Recipe> recipeArrayList) {
        this.context = context;
        this.recipeArrayList = recipeArrayList;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestS);
            imageView = itemView.findViewById(R.id.imageViewTestS);
        }
    }

    @NonNull
    @Override
    public RecipeAdapterSubcard.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_subcard, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterSubcard.Viewholder holder, int position) {
        Recipe model = recipeArrayList.get(position);
        holder.textView.setText(model.getName());
        holder.imageView.setImageResource(model.getImageId());
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
