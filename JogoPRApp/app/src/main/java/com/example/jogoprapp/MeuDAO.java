package com.example.jogoprapp;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MeuDAO {

    @Insert
    long inserirQuestao(Questoes questoes);

}
