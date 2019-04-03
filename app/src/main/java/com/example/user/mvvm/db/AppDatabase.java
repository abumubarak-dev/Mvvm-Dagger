package com.example.user.mvvm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.user.mvvm.Model.Post;

@Database(entities = Post.class,version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {


    static AppDatabase INSTANCE;
    public abstract PostDao postDao();

    public static AppDatabase getINSTANCE(Context context) {
        if (INSTANCE==null){
            synchronized (AppDatabase.class){
                if (INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"apidata.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
