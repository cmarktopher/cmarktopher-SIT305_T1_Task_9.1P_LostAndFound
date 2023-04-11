package com.application.lostandfound.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.application.lostandfound.DataAccessObjects.FoundDataAccessObject;
import com.application.lostandfound.DataAccessObjects.LostDataAccessObject;
import com.application.lostandfound.Models.FoundDataModel;
import com.application.lostandfound.Models.LostDataModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for our database
 * Just a note:
 * The docs mention that it is a good idea to use the singleton pattern
 * to get the database if we are working in a single process (which we are)
 * Thus, I will implement a singleton for this class using this guide:
 * https://developer.android.com/codelabs/android-room-with-a-view#7
 */


@Database(entities = {LostDataModel.class, FoundDataModel.class}, version = 1)
public abstract class LostAndFoundDatabase extends RoomDatabase {

    // Keep track of the single instance of this database
    private static volatile LostAndFoundDatabase INSTANCE;

    // Our DAO containing our queries
    public abstract LostDataAccessObject lostDao();
    public abstract FoundDataAccessObject foundDao();

    // Thread related - to my understanding, this allows database operations to occur on background threads
    // We will need the ExecutorService to execute the database queries.
    private final int NUMBER_OF_THREADS = 4;

    private ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // This is how we handle the singleton to ensure we only get back only one instance of the LostAndFound database.
    public static LostAndFoundDatabase getDatabase(final Context context) {

        if (INSTANCE == null){
            synchronized (LostAndFoundDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LostAndFoundDatabase.class, "lost_and_found_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}
