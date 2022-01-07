package it.unimib.cookery.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.models.IngredientApi;

public class IngredientChipAdapter extends RecyclerView.Adapter<IngredientChipAdapter.IngredientViewHolder> {
    private List<IngredientApi> mListIngredients;
    private ArrayList<IngredientApi> missingIngredients;
    private int k = 0;


    public void setData(List<IngredientApi> list) {
        this.mListIngredients = list;
        notifyDataSetChanged();

    }

    public void setData(List<IngredientApi> list, ArrayList<IngredientApi> missingIngredients ) {
        this.mListIngredients = list;
        this.missingIngredients = missingIngredients;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chip_ingredient, parent, false);


        return new IngredientViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        // per ora parzialmente funzionante
        // da sistemare bene con richiesta database
        boolean missing = false;

        IngredientApi ingredient = mListIngredients.get(position);
        if (ingredient == null) {
            return;
        }


        if(missingIngredients != null && missingIngredients.contains(ingredient)) {

           missing = true;


        }



        holder.tvIngredient.setText(ingredient.getName() + ":");



        switch (ingredient.getUnit()) {

            case "teaspoons":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " tsps");
                break;
            case "teaspoon":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " tsp");
                break;
            case "Teaspoons":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " tsps");
                break;
            case "Teaspoon":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " tsp");
                break;
            case "Tablespoons":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " Tbsps");
                break;
            case "Tablespoon":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " Tbsp");
                break;
            case "tablespoons":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " Tbsps");
                break;
            case "tablespoon":
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " Tbsp");
                break;
            default:
                holder.tvQuantity.setText(" " + ingredient.getAmount() + " " + ingredient.getUnit());
                break;

        }


        if(missing) {
              holder.tvIngredient.setTextColor(Color.RED);
            holder.tvQuantity.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        if (mListIngredients != null) {
            return mListIngredients.size();
        }
        return 0;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIngredient;
        private TextView tvQuantity;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tv_ingredient);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }

    }
}
