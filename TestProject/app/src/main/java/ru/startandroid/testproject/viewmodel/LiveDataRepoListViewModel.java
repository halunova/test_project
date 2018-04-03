package ru.startandroid.testproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.startandroid.testproject.api.UserRepo;

/**
 * Created by Виктория on 28.03.2018.
 */

public class LiveDataRepoListViewModel extends ViewModel {
    private MutableLiveData<List<UserRepo>> myRepoList = new MutableLiveData<>();

    private List<UserRepo> myList;

    public  LiveDataRepoListViewModel(){

    }

}
