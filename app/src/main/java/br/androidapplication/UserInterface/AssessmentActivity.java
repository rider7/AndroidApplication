package br.androidapplication.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.Entity.AssessmentEntity;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;

    public class AssessmentActivity  extends AppCompatActivity {
        private ScheduleRepository repository;
        static int id2;
        int assessmentID;
        int courseID;
        int courseTermID;
        int courseAssessmentID;
        String courseTitle;
        String courseStart;
        String courseEnd;
        String courseStatus;
        String instructorName;
        String instructorNumber;
        String instructorEmail;
        String notes;
//        String termTitle;
//        String termStart;
//        String termEnd;


        EditText editCourseTitle;
        EditText editCourseStart;
        EditText editCourseEnd;
        EditText editCourseStatus;
        EditText editInstructorName;
        EditText editInstructorNumber;
        EditText editInstructorEmail;
        EditText editNotes;
//        EditText editTermTitle;
//        EditText editTermStart;
//        EditText editTermEnd;

        TermEntity currentTerm;
        CourseEntity currentCourse;
        AssessmentEntity currentAssessment;
        public static int numCourses;
        public static int numAssessments;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_assessment);
            courseTermID=getIntent().getIntExtra("termID",-1);
            courseID=getIntent().getIntExtra("courseID",-1);
            courseTitle=getIntent().getStringExtra("title");
            courseStart=getIntent().getStringExtra("start");
            courseEnd=getIntent().getStringExtra("end");
            courseStatus=getIntent().getStringExtra("status");
            instructorName=getIntent().getStringExtra("name");
            instructorNumber=getIntent().getStringExtra("number");
            instructorEmail=getIntent().getStringExtra("email");
            notes=getIntent().getStringExtra("notes");
            id2=courseAssessmentID;
//            courseAssessmentID=getIntent().getIntExtra("courseID",-1);
//            courseTitle=getIntent().getStringExtra("courseTitle");;
//            courseStart=getIntent().getStringExtra("courseStart");;
//            courseEnd=getIntent().getStringExtra("courseEnd");;
//            courseStatus=getIntent().getStringExtra("courseStatus");;
//            instructorName=getIntent().getStringExtra("instructorName");;
//            instructorNumber=getIntent().getStringExtra("instructorNumber");;
//            instructorEmail=getIntent().getStringExtra("instructorAddress");;
            //id2=courseAssessmentID;
            if (courseID==-1)courseID=-1;
            repository= new ScheduleRepository(getApplication());
            List<CourseEntity> allCourses=repository.getAllCourses();

            //This should be getCourseTermID()???
            for(CourseEntity c:allCourses){
                if(c.getCourseID()==courseID)currentCourse=c;
                //if(c.getCourseTermID()==courseAssessmentID)currentCourse=c;
            }

            editCourseTitle=findViewById(R.id.CourseTitle);;
            editCourseStart=findViewById(R.id.CourseStart);;
            editCourseEnd=findViewById(R.id.CourseEnd);;
            editCourseStatus=findViewById(R.id.CourseStatus);;
            editInstructorName=findViewById(R.id.InstructorName);;
            editInstructorNumber=findViewById(R.id.InstructorNumber);;
            editInstructorEmail=findViewById(R.id.InstructorEmail);;
            editNotes=findViewById(R.id.Notes);

            if(currentCourse!=null){
                courseTitle=currentCourse.getCourseTitle();
                courseStart=currentCourse.getCourseStart();
                courseEnd=currentCourse.getCourseEnd();
                courseStatus=currentCourse.getCourseStatus();
                instructorName=currentCourse.getInstructorName();
                instructorNumber=currentCourse.getInstructorNumber();
                instructorEmail=currentCourse.getInstructorAddress();
                notes=currentCourse.getNotes();
            }
//            if(courseAssessmentID!=-1&&id2==-1){
            if(courseID!=-1){
                editCourseTitle.setText(courseTitle);
                editCourseStart.setText(courseStart);
                editCourseEnd.setText(courseEnd);
                editCourseStatus.setText(courseStatus);
                editInstructorName.setText(instructorName);
                editInstructorNumber.setText(instructorNumber);
                editInstructorEmail.setText(instructorEmail);
                editNotes.setText(notes);
            }
            repository= new ScheduleRepository(getApplication());
            RecyclerView recyclerView = findViewById(R.id.associated_assessments);
            final AssessmentAdapter adapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<AssessmentEntity> filteredAssessments=new ArrayList<>();
            //CourseAssessmentID is incorrectly showing 3
            for(AssessmentEntity a:repository.getAllAssessments()){
                if(a.getCourseAssessmentID()==courseID)filteredAssessments.add(a);
            }
            numAssessments=filteredAssessments.size();
            adapter.setWords(filteredAssessments);

            // calling the action bar
            ActionBar actionBar = getSupportActionBar();

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.delete) {
                if(numAssessments==0) {
                    repository.delete(currentCourse);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Can't delete a course with assessments",Toast.LENGTH_LONG).show();// make another toast
                }
            }
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_delete, menu);
            getMenuInflater().inflate(R.menu.menu_sharing, menu);
            return true;
        }


        //Intent to change to courseActivity
        public void addAssessment(View view) {
            Intent intent=new Intent(br.androidapplication.UserInterface.AssessmentActivity.this,EditActivity.class);
            intent.putExtra("assessmentID",courseAssessmentID);
            intent.putExtra("courseAssessmentID",courseID);
            intent.putExtra("addID",0);
            startActivity(intent);
        }



        public void addCourseFromScreen(View view) {
            CourseEntity c;

            if (courseID != -1) {
//                List<CourseEntity> allCourses = repository.getAllCourses();
//                courseAssessmentID = allCourses.get(allCourses.size() - 1).getCourseID();

                c = new CourseEntity(courseID, courseTermID, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorNumber.getText().toString(), editInstructorEmail.getText().toString(), editNotes.getText().toString());
            }else {
                List<CourseEntity> allCourses = repository.getAllCourses();
                courseID = allCourses.get(allCourses.size() - 1).getCourseID();

                c = new CourseEntity(++courseID, courseTermID, editCourseTitle.getText().toString(),  editCourseStart.getText().toString(), editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorNumber.getText().toString(), editInstructorEmail.getText().toString(), editNotes.getText().toString());
            }
            repository.insert(c);
            //May need update instead of insert
            Intent intent = new Intent (AssessmentActivity.this, CourseActivity.class);
            intent.putExtra("termID",courseTermID);
            startActivity(intent);
        }
}
