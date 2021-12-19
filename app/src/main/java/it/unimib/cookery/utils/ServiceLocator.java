package it.unimib.cookery.utils;

import android.app.Application;

import it.unimib.cookery.database.RoomDatabase;


/**
 *  Registry to provide the dependencies for the classes
 *  used in the application.
 */
public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }



    public RoomDatabase getDao(Application application) {
        return RoomDatabase.getDatabase(application);
    }
}
