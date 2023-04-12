package com.application.lostandfound.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.Repositories.LostAndFoundItemRepository;
import java.util.List;

/**
 * This acts as a bridge between the UI and the repository.
 * Also, we can use this to share information between the fragments if needed.
 * According to the guide, we can use the view model to handle the data aspects of the app and leave activities and fragments to handle the UI.
 */
public class LostFoundViewModel extends AndroidViewModel {

    // Just a note with this, I am pretty sure it will be better to use dependency injection to pass in the reference to the repository.
    // If I use this architecture for higher level tasks, I will look into DI libraries for android.
    private LostAndFoundItemRepository lostAndFoundItemRepository;

    private final LiveData<List<LostFoundDataModel>> allLostAndFoundItems;

    public LostFoundViewModel(@NonNull Application application) {

        super(application);

        lostAndFoundItemRepository = new LostAndFoundItemRepository(application);
        allLostAndFoundItems = lostAndFoundItemRepository.getAllLostItems();
    }

    public LiveData<List<LostFoundDataModel>> getAllLostAndFoundItems() {

        return allLostAndFoundItems;
    }

    public void insert(LostFoundDataModel newItem) {

        lostAndFoundItemRepository.insert(newItem);
    }
}
