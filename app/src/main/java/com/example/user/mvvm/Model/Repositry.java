package com.example.user.mvvm.Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.mvvm.Model.api.ApiClient;
import com.example.user.mvvm.db.AppDatabase;
import com.example.user.mvvm.db.PostDao;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositry {

    private static final String TAG = "Repositry";
    private static final int FRESH_TIMEOUT_IN_MINUTES = 3;
    private Repositry INSTANCE;
    PostDao postDao;
    AppDatabase appDatabase;

    public Repositry getINSTANCE() {
        if (INSTANCE==null){
            INSTANCE=new Repositry();
        }

        return INSTANCE;
    }

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

    public void fetchFromNetwork(Context context){

        appDatabase=AppDatabase.getINSTANCE(context);

        postDao=appDatabase.postDao();

        boolean b=(postDao.getref(getMaxRefreshTime(new Date()))!=null);

        if (!b){
            ApiClient.getINSTANCE().getApiEndPoint().getPostCall()
                    .enqueue(new Callback<List<Post>>() {

                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                            List<Post> a=new ArrayList<>();

                            a.addAll(response.body());

                            Date date= new Date();

                            for (int i = 0; i<a.size(); i++){

                                Post post=a.get(i);

                                post.setDate(date);

                                Completable.fromAction(()->postDao.insert(post))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new CompletableObserver() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onComplete() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }
                                        });

                            }

                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {

                            Log.i("fail", "onFailure: " + t.getMessage());

                        }

                    });
        }else{

            ApiClient.getINSTANCE().getApiEndPoint().getPostCall()
                    .enqueue(new Callback<List<Post>>() {

                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                            List<Post> a=new ArrayList<>();

                            a.addAll(response.body());

                            Date date= new Date();

                            for (int i = 0; i<a.size(); i++){

                                Post post=a.get(i);

                                post.setDate(date);

                                Completable.fromAction(()->postDao.insert(post))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new CompletableObserver() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onComplete() {

                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }
                                        });

                            }

                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {

                            Log.i("fail", "onFailure: " + t.getMessage());

                        }

                    });

        }


    }

    public Flowable<List<Post>> getDataFromDb(Context context){

        fetchFromNetwork(context);

        return postDao.getAllPost();
    }

}
