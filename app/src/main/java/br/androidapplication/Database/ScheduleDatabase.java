package br.androidapplication.Database;


import br.androidapplication.DAO.AssessmentDAO;
import br.androidapplication.DAO.CourseDAO;
import br.androidapplication.DAO.TermDAO;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.Entity.AssessmentEntity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Annotate database with entity and version number
@Database(entities = {TermEntity.class, CourseEntity.class, AssessmentEntity.class}, version = 7, exportSchema = false)

public abstract class ScheduleDatabase extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    //determines the number of threads
    private static final int NUMBER_OF_THREADS = 4;
    //the write executor An object that executes submitted Runnable tasks
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile ScheduleDatabase INSTANCE;

    static ScheduleDatabase getDatabase(final Context context) {
        //If no instance of the database then build one named schedule_database.db
        if (INSTANCE == null) {
            synchronized (ScheduleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ScheduleDatabase.class, "schedule_database.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.

                TermDAO mTermDao = INSTANCE.termDAO();
                CourseDAO mCourseDao = INSTANCE.courseDAO();
                AssessmentDAO mAssessmentDao = INSTANCE.assessmentDAO();

                // Delete database data in the beginning.
                //mTermDao.deleteAllTerms();
                //mCourseDao.deleteAllCourses();
                //mAssessmentDao.deleteAllAssessments();

                //Insert test data to make sure db and tables are setup correctly
                TermEntity term = new TermEntity(3, "Spring", "10/12/25", "12/13/27");
                mTermDao.insert(term);
                term = new TermEntity(1, "Fall", "10/12/25", "12/13/27");
                mTermDao.insert(term);
                term = new TermEntity(2, "Summer", "10/12/25", "12/13/27");
                mTermDao.insert(term);

                //Course data
                CourseEntity course = new CourseEntity(1, 2, "Math", "12/10/21", "12/11/21", "Completed", "Steven Jobs", "210-256-4578", "stevejobs@gmail.com", "dont look at my notes");
                mCourseDao.insert(course);
                course = new CourseEntity(2, 3, "English", "12/10/21", "12/11/21", "Completed", "Matthew Johnson", "210-256-4578", "matt@gmail.com", "my notes lalalala");
                mCourseDao.insert(course);
                course = new CourseEntity(1, 3, "Test", "12/10/21", "12/11/21", "Completed", "Matthew Johnson", "210-256-4578", "matt@gmail.com", null);
                mCourseDao.insert(course);

                //Assessment data
                AssessmentEntity assessment = new AssessmentEntity(1, 3, "Performance", "MidTerm", "12/10/21", "12/11/21");
                mAssessmentDao.insert(assessment);
                assessment = new AssessmentEntity(2, 1, "Objective", "Final", "12/10/21", "12/11/21");
                mAssessmentDao.insert(assessment);
            });
        }
    };































}
