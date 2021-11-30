package br.androidapplication.DAO;

import br.androidapplication.Entity.CourseEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Interface used to map SQL queries to a function TermDAO
@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity course);

    @Delete
    void delete(CourseEntity course);

    @Update
    void update(CourseEntity course);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table ORDER BY courseID ASC")
    List<CourseEntity> getAllCourses();
}
