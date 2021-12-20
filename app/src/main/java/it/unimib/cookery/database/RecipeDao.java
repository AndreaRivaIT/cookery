package it.unimib.cookery.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeStep;


/**
 * Data Access Object (DAO) that provides methods that can be used to query,
 * update, insert, and delete data in the database.
 * https://developer.android.com/training/data-storage/room/accessing-data
 */
@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Insert
    void insertRecipeList(List<Recipe> recipe);

    @Insert
    void insertAll(Recipe... recipe);

    @Insert
    void insertAlla(Recipe... recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("DELETE FROM Recipe")
    void deleteAll();

    @Query("SELECT * FROM Recipe ORDER BY idRecipe DESC LIMIT 1")
    public List<Recipe> lastRecipe();


    @Delete
    void deleteAllWithoutQuery(Recipe... recipe);
}

