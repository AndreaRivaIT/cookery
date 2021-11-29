package it.unimib.cookery.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.model.Ingredient;
import it.unimib.cookery.model.RecipeStep;

public class IngredientChipAdapter extends RecyclerView.Adapter<IngredientChipAdapter.IngredientViewHolder>{
    private List<Ingredient> mListIngredients;
    private  int k = 0;
    public  void setData( List<Ingredient> list ){
        this.mListIngredients = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chip_ingredient, parent, false);

        return  new IngredientChipAdapter.IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = mListIngredients.get(position);
        if(ingredient == null){ return;}

        holder.tvIngredient.setText(ingredient.getIngredientName()+":");
        Log.d("test","quantità" + ingredient.getQuantity());
        holder.tvQuantity.setText(" "+ingredient.getQuantity() + "g");
    }

    @Override
    public int getItemCount() {
        if(mListIngredients != null){
            return  mListIngredients.size();
        }
        return 0;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIngredient;
        private TextView tvQuantity;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tv_ingredient);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
