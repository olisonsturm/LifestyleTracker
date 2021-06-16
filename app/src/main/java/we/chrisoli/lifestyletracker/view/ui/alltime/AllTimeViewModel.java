package we.chrisoli.lifestyletracker.view.ui.alltime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllTimeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllTimeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("All time stats");
    }

    public LiveData<String> getText() {
        return mText;
    }
}