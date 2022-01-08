package it.unimib.cookery.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class RecipeStep {

    @PrimaryKey
    private int idStep;
    private int recipeId;

    @ColumnInfo(name = "n_steps")
    private int nStep;

    private String description;

    public RecipeStep(int nStep, String description) {
        this.nStep = nStep;
        this.description = description;
    }

    @Ignore
    public RecipeStep() {

    }


    public int getIdStep() {
        return idStep;
    }

    public void setIdStep(int idStep) {
        this.idStep = idStep;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        recipeId = recipeId;
    }



    public int getNStep() {
        return nStep;
    }

    public String getDescription() {
        return description;
    }

    public void setNStep(int nStep) {
        this.nStep = nStep;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
