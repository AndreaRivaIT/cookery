package it.unimib.cookery.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PantryWithIngredientPantry {
    @Embedded
    public Recipe Recipe;
    @Relation(
            parentColumn = "idPantry",
            entityColumn = "pantryId"
            //associateBy = @Junction(RecipeStepsCrossRef.class)
    )
    public List<RecipeStep> stepsList;
}
