package we.chrisoli.lifestyletracker.ui.yesterday;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YesterdayViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YesterdayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Yesterday stats");
    }

    public LiveData<String> getText() {
        return mText;
    }
}