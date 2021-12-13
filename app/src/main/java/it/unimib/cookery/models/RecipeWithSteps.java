package it.unimib.cookery.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class RecipeWithSteps {
    @Embedded public Recipe Recipe;
    @Relation(
            parentColumn = "idRecipe",
            entityColumn = "recipeId"
            //associateBy = @Junction(RecipeStepsCrossRef.class)
    )
    public List<RecipeStep> stepsList;
}
