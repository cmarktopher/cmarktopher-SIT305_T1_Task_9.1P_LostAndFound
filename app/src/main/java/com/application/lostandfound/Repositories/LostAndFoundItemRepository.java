package com.application.lostandfound.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.application.lostandfound.DataAccessObjects.LostFoundDataAccessObject;
import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.ViewModels.LostFoundViewModel;

import java.util.List;

/**
 * Me adding this class was due to me following the guide indicated in the main activity.
 * However, I understand why a repository is important.
 * By using this, we can easily swap out implementations for data access.
 * Here, we have a repository that interacts with a local database.
 * While outside of the scope of this task, we can create and swap this out with a new repository and have that interact with a server based database for example.
 * Since we are using a view model, that class would then reference the respective repository we want to use.
 */
public class LostAndFoundItemRepository {

    private LostFoundDataAccessObject lostDataAccessObject;
    private LiveData<List<LostFoundDataModel>> allLostItems;

    public LostAndFoundItemRepository(Application application){

        lostDataAccessObject = LostAndFoundDatabase.getDatabase(application).lostDao();
        allLostItems = lostDataAccessObject.getAllLostItems();
    }

    public LiveData<List<LostFoundDataModel>> getAllLostItems() {
        return allLostItems;
    }

    public void insert(LostFoundDataModel newItem){

        LostAndFoundDatabase.databaseWriteExecutor.execute(() -> {
            lostDataAccessObject.insertNewLostItem(newItem);
        });
    }

    public void delete(LostFoundDataModel itemToDelete){

        LostAndFoundDatabase.databaseWriteExecutor.execute(() -> {
            lostDataAccessObject.deleteLostItem(itemToDelete.uid);
        });
    }

}
