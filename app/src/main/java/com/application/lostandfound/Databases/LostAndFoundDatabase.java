package com.application.lostandfound.Databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.application.lostandfound.DataAccessObjects.LostFoundDataAccessObject;
import com.application.lostandfound.Models.LostFoundDataModel;

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


@Database(entities = {LostFoundDataModel.class}, version = 3)
public abstract class LostAndFoundDatabase extends RoomDatabase {

    // Keep track of the single instance of this database
    private static volatile LostAndFoundDatabase INSTANCE;

    // Our DAO containing our queries
    public abstract LostFoundDataAccessObject lostDao();

    // Thread related - to my understanding, this allows database operations to occur on background threads
    // We will need the ExecutorService to execute the database inserts and deletes.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // This is how we handle the singleton to ensure we only get back only one instance of the LostAndFound database.
    public static LostAndFoundDatabase getDatabase(final Context context) {

        // Basically, if there is no instance of the database, then we create one and assign it to INSTANCE
        if (INSTANCE == null){
            synchronized (LostAndFoundDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LostAndFoundDatabase.class, "lost_and_found_database").build();
                }
            }
        }

        return INSTANCE;
    }
}
