package com.example.jogoprapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class BancoDeDados extends RoomDatabase {

    private static BancoDeDados INSTANCE;
    public abstract MeuDAO meuDAO();

    public static BancoDeDados getBancoDeDados(final Context context){
        if (INSTANCE == null){
            synchronized (BancoDeDados.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BancoDeDados.class, "bd_questoes").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
