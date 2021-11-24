package br.androidapplication.Database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.androidapplication.Database.ScheduleDatabase;
import br.androidapplication.DAO.TermDAO;
//import br.androidapplication.DAO.CourseDAO;
//import br.androidapplication.DAO.Assessment;
import br.androidapplication.Entity.TermEntity;
//import br.androidapplication.Entity.CourseEntity;
//import br.androidapplication.Entity.AssessmentEntity;


public class ScheduleRepository {
    private TermDAO mTermDAO;
    private List<TermEntity> mAllTerms;
    private int termID;
    //Course
    //Assessment

    private static final int NUMBER_OF_THREADS = 4;
    //the write executor An object that executes submitted Runnable tasks
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Constructor to get db instance
    public ScheduleRepository(Application application){
        ScheduleDatabase db = ScheduleDatabase.getDatabase(application);
        mTermDAO = db.termDAO();
        //course
        //assessment

        //Try catch for thread timing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    //Used to get the full list of terms
    public List<TermEntity> getAllTerms(){
        databaseWriteExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }
    //Insert for term
    public void insert (TermEntity termEntity) {
        databaseWriteExecutor.execute(()->{
            mTermDAO.insert(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Delete for term
    public void delete (TermEntity termEntity) {
        databaseWriteExecutor.execute(() -> {
            mTermDAO.delete(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Course list
    //Course insert
    //Course delete
    //Assessment list
    //Assessment insert
    //Assessment delete
}

