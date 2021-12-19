package it.unimib.cookery.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.IngredientPantry;
import it.unimib.cookery.models.Pantry;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeIngredientCrossRef;
import it.unimib.cookery.models.RecipeStep;


/*@Database(entities = {Recipe.class, RecipeStep.class, RecipeIngredientCrossRef.class,
        Ingredient.class, IngredientPantry.class, Pantry.class}, version = Costants.DATABASE_VERSION)*/

public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract RecipeDao recipeDao();
    public abstract StepDao stepDao();
    private static volatile RoomDatabase INSTANCE;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (RoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class,
                            Costants.DATABASE_NAME).build();
                }
            }
        }
        return  INSTANCE;
    }
}
