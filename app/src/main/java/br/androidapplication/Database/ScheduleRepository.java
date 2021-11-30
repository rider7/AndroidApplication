package br.androidapplication.Database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.androidapplication.Database.ScheduleDatabase;
import br.androidapplication.DAO.TermDAO;
import br.androidapplication.DAO.CourseDAO;
import br.androidapplication.DAO.AssessmentDAO;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.Entity.AssessmentEntity;


public class ScheduleRepository {
    //Term
    private TermDAO mTermDAO;
    private List<TermEntity> mAllTerms;
    private int termID;
    //Course
    private CourseDAO mCourseDAO;
    private List<CourseEntity> mAllCourses;
    private int courseID;
    //Assessment
    private AssessmentDAO mAssessmentDAO;
    private List<AssessmentEntity> mAllAssessments;
    private int assessmentID;

    private static final int NUMBER_OF_THREADS = 4;
    //the write executor An object that executes submitted Runnable tasks
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Constructor to get db instance
    public ScheduleRepository(Application application) {
        ScheduleDatabase db = ScheduleDatabase.getDatabase(application);
        //term
        mTermDAO = db.termDAO();
        //course
        mCourseDAO = db.courseDAO();
        //assessment
        mAssessmentDAO = db.assessmentDAO();

        //Try catch for thread timing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Used to get the full list of terms
    public List<TermEntity> getAllTerms() {
        databaseWriteExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    //Insert for term
    public void insert(TermEntity termEntity) {
        databaseWriteExecutor.execute(() -> {
            mTermDAO.insert(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Delete for term
    public void delete(TermEntity termEntity) {
        databaseWriteExecutor.execute(() -> {
            mTermDAO.delete(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(TermEntity termEntity) {
        databaseWriteExecutor.execute(() -> {
            mTermDAO.update(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Used to get the full list of courses
    public List<CourseEntity> getAllCourses() {
        databaseWriteExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    //Insert for course
    public void insert(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(() -> {
            mCourseDAO.insert(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Delete for course
    public void delete(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(() -> {
            mCourseDAO.delete(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Update for course
    public void update(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(() -> {
            mCourseDAO.update(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


    //Course list
    //Course insert
    //Course delete
    //Assessment list
    //Assessment insert
    //Assessment delete

