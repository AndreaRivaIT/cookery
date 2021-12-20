package it.unimib.cookery.repository;

import android.app.Application;
import android.util.Log;

import it.unimib.cookery.database.IngredientPantryDao;
import it.unimib.cookery.database.PantryDao;
import it.unimib.cookery.database.RoomDatabase;
import it.unimib.cookery.models.IngredientPantry;
import it.unimib.cookery.models.Pantry;
import it.unimib.cookery.utils.ResponseCallbackDb;
import it.unimib.cookery.utils.ServiceLocator;


/**
 * Repository to get the news using the API
 * provided by NewsApi.org (https://newsapi.org).
 */
public class DatabasePantryRepository {

    private static final String TAG = "DatabasePantryRepository";

    private final Application mApplication;
    private final PantryDao mPantryDao;
    private final IngredientPantryDao ingredientPantryDao;
    private final ResponseCallbackDb mResponseCallbackDb;


    public DatabasePantryRepository(Application application, ResponseCallbackDb responseCallback) {
        this.mApplication = application;
        RoomDatabase roomDatabase = ServiceLocator.getInstance().getDao(application);

        //gestione Pantry
        this.mPantryDao = roomDatabase.PantryDao();
        this.ingredientPantryDao = roomDatabase.IngredientPantryDao();
        //this.mRecipeDao = newsRoomDatabase.recipeDao();
        //this.mStepDao = newsRoomDatabase.stepDao();
        this.mResponseCallbackDb = responseCallback;
    }

    //Crud
    //Create
    public void create(Object obj){
        saveObj(obj);
    }
    private void saveObj(Object obj){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //oggetto che descrive il tipo di dispensa
                if(obj instanceof Pantry) {
                    mPantryDao.insertPantry((Pantry) obj);
                }
                //oggetto che descrive il tipo di ingrediente della dispensa
                else if(obj instanceof IngredientPantry){
                    Log.d("test","ingrediente");
                    ingredientPantryDao.insertIngredientPantry((IngredientPantry) obj);
                }
                //notificare l'aggiornamento
                synchronized(DatabasePantryRepository.this){
                    DatabasePantryRepository.this.notifyAll();
                }

            }
        };
        new Thread(runnable).start();
    }

    //Read

    public void read(Object obj){
        readObj(obj);
    }


    private void readObj(Object obj) {
        Runnable runnable = new Runnable() {
            @Override
            public void  run() {
                mResponseCallbackDb.onResponse(mPantryDao.pantryWithIngredientPantry(1));
            }
        };
        new Thread(runnable).start();
    }

   public void readAllIngredientPantry() {
        Runnable runnable = new Runnable() {
            @Override
            public void  run() {
                mResponseCallbackDb.onResponse(ingredientPantryDao.ingredientPantryALL());
            }
        };
        new Thread(runnable).start();
    }

    //Update

    //Delete
}
