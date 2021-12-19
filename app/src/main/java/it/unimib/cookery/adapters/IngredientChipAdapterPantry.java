package it.unimib.cookery.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.IngredientPantry;

public class IngredientChipAdapterPantry extends RecyclerView.Adapter<IngredientChipAdapterPantry.IngredientViewHolder>{
    private List<IngredientPantry> mListIngredients = new ArrayList<>();
    private  int k = 0;


    public  void setData( List<IngredientPantry> list){
        this.mListIngredients = list;
        Log.d("test"," PantryAdapter ---");
        for(int k=0 ; k <list.size(); k++){
            Log.d("test"," PantryAdapter " +list.get(k).getIngredientName()+"-"+list.get(k).getQuantity());
        }
        Log.d("test"," PantryAdapter --- Fine");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chip_ingredient, parent, false);
        return  new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = mListIngredients.get(position);
        if(ingredient == null){ return;}
        holder.tvIngredient.setText(ingredient.getIngredientName()+":");
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
