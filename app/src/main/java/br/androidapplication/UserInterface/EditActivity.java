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

public class EditActivity  extends AppCompatActivity {
    private ScheduleRepository repository;
    static int id2;
    int assessmentID;
    int courseTermID;
    int courseAssessmentID;
    String assessmentType;
    String assessmentTitle;
    String assessmentEnd;

    EditText editAssessmentTitle;
    EditText editAssessmentType;
    EditText editAssessmentEnd;

    TermEntity currentTerm;
    CourseEntity currentCourse;
    AssessmentEntity currentAssessment;
    public static int numCourses;
    public static int numAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        assessmentID=getIntent().getIntExtra("assessmentID",-1);
        courseAssessmentID=getIntent().getIntExtra("courseAssessmentID",-1);
        assessmentTitle=getIntent().getStringExtra("AssessmentTitle");
        assessmentType=getIntent().getStringExtra("AssessmentType");
        assessmentEnd=getIntent().getStringExtra("AssessmentEnd");

        id2=getIntent().getIntExtra("addID",-1);


        //id2=courseAssessmentID;
        if (courseAssessmentID==-1)courseAssessmentID=-1;
        repository= new ScheduleRepository(getApplication());
        List<AssessmentEntity> allAssessments=repository.getAllAssessments();

        //This should be getCourseTermID()???
        for(AssessmentEntity a:allAssessments){
            if(a.getAssessmentID()==assessmentID)currentAssessment=a;
            //if(c.getCourseTermID()==courseAssessmentID)currentCourse=c;
        }

        editAssessmentTitle=findViewById(R.id.AssessmentTitle);;
        editAssessmentType=findViewById(R.id.AssessmentType);;
        editAssessmentEnd=findViewById(R.id.AssessmentEnd);;

        if(currentAssessment!=null){
            assessmentTitle=currentAssessment.getAssessmentTitle();
            assessmentType=currentAssessment.getAssessmentType();
            assessmentEnd=currentAssessment.getAssessmentEnd();
        }
        if(courseAssessmentID!=-1&&id2==-1){
            editAssessmentTitle.setText(assessmentTitle);
            editAssessmentType.setText(assessmentType);
            editAssessmentEnd.setText(assessmentEnd);
        }
//        repository= new ScheduleRepository(getApplication());
//        RecyclerView recyclerView = findViewById(R.id.associated_assessments);
//        final AssessmentAdapter adapter = new AssessmentAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        List<AssessmentEntity> filteredAssessments=new ArrayList<>();
//        //CourseAssessmentID is incorrectly showing 3
//        for(AssessmentEntity a:repository.getAllAssessments()){
//            if(a.getCourseAssessmentID()==courseAssessmentID)filteredAssessments.add(a);
//        }
//        numAssessments=filteredAssessments.size();
//        adapter.setWords(filteredAssessments);

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
                repository.delete(currentAssessment);
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
        return true;
    }

    //Intent to change to courseActivity
//    public void addAssessment(View view) {
//        Intent intent=new Intent(br.androidapplication.UserInterface.EditActivity.this,CourseActivity.class);
//        intent.putExtra("assessmentID",courseAssessmentID);
//        startActivity(intent);
//    }

    public void addAssessmentFromScreen(View view) {
        AssessmentEntity a;

        if (assessmentID != -1&&assessmentID !=0) {
//            List<AssessmentEntity> allAssessments = repository.getAllAssessments();
//            courseAssessmentID = allAssessments.get(allAssessments.size() - 1).getAssessmentID();
            a = new AssessmentEntity(assessmentID, courseAssessmentID, editAssessmentType.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentEnd.getText().toString());
        }else {
            List<AssessmentEntity> allAssessments = repository.getAllAssessments();
            assessmentID = allAssessments.get(allAssessments.size() - 1).getAssessmentID();
            a = new AssessmentEntity(++assessmentID, courseAssessmentID, editAssessmentType.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentEnd.getText().toString());        }
        repository.insert(a);
            Intent intent = new Intent (EditActivity.this, AssessmentActivity.class);
            intent.putExtra("courseID",courseAssessmentID);
            startActivity(intent);
    }
}
