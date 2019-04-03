package com.example.user.mvvm.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.user.mvvm.Model.Post;
import com.example.user.mvvm.Model.Repositry;

import java.util.List;

import io.reactivex.Flowable;

public class PostViewModel extends AndroidViewModel {

   private Flowable<List<Post>> listFlowable;
   private Repositry repositry;
   Application application;

    public PostViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        repositry=new Repositry().getINSTANCE();

    }

    public void init(){
        if (listFlowable!=null){
            return;
        }

        listFlowable=repositry.getDataFromDb(application.getApplicationContext());

    }

    public Flowable<List<Post>> getPostLiveData(){

        return listFlowable;
    }
}
