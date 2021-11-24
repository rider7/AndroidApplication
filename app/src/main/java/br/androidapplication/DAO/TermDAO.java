package br.androidapplication.DAO;
import br.androidapplication.Entity.TermEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Interface used to map SQL queries to a function TermDAO
    @Dao
    public interface TermDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(TermEntity term);

        @Delete
        void delete(TermEntity term);

        @Update
        void update(TermEntity term);

        @Query("DELETE FROM term_table")
        void deleteAllTerms();

        @Query("SELECT * FROM term_table ORDER BY termID ASC")
        List<TermEntity> getAllTerms();
















}
