package com.example.test_epic.ui.UploadImage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UploadImageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UploadImageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}