package com.example.user.mvvm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.mvvm.Model.Post;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Post post);

    @Query("SELECT * FROM post")
    Flowable<List<Post>> getAllPost();


    @Query("SELECT * FROM post WHERE date > :time LIMIT 1")
    Post getref(Date time);
}
