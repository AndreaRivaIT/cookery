package it.unimib.cookery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.model.RecipeStep;

public class RecipeProcedureAdapter extends RecyclerView.Adapter<RecipeProcedureAdapter.StepViewHolder>{

    private List<RecipeStep> mListStep;
    public  void setData(List<RecipeStep> list){
        this.mListStep =list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step_recipe, parent, false);
        return  new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        RecipeStep recipeStep = mListStep.get(position);
        if(recipeStep == null){ return;}
        holder.tvNStep.setText("Step "+ recipeStep.getnStep()+" :");
        holder.tvDescription.setText(recipeStep.getDescription());
    }

    @Override
    public int getItemCount() {
        if(mListStep != null){
            return  mListStep.size();
        }
        return 0;
    }

    public class StepViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNStep;
        private TextView tvDescription;
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNStep = itemView.findViewById(R.id.tv_n_step);
            tvDescription = itemView.findViewById(R.id.tv_descripion);
        }
    }
}
