package br.androidapplication.UserInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;

public class CourseActivity  extends AppCompatActivity {
    private ScheduleRepository repository;
    
    int courseID;
    int courseTermID;
    String courseTitle;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructorName;
    String instructorNumber;
    String instructorEmail;
    String termTitle;
    String termStart;
    String termEnd;


    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editCourseStatus;
    EditText editInstructorName;
    EditText editInstructorNumber;
    EditText editInstructorEmail;
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;

    TermEntity currentTerm;
    CourseEntity currentCourse;
    public static int numCourses;

    //Why is the Term Adapter not passing the values to onCreate for the CourseActivity?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseTermID=getIntent().getIntExtra("termID",2);
        if (courseTermID==-1)courseTermID=-1;
        repository= new ScheduleRepository(getApplication());
        List<TermEntity> allTerms=repository.getAllTerms();

        for(TermEntity t:allTerms){
            if(t.getTermID()==courseTermID)currentTerm=t;
        }

//        name=getIntent().getStringExtra("TermName");
//        price=getIntent().getStringExtra("TermPrice");
        editTermTitle=findViewById(R.id.TermTitle);
        editTermStart=findViewById(R.id.TermStart);
        editTermEnd=findViewById(R.id.TermEnd);
//        editCourseTitle=findViewById(R.id.TermName);
//        editCourseStart=findViewById(R.id.TermPrice);
//        editCourseEnd=findViewById(R.id.TermName);
//        editCourseStatus=findViewById(R.id.TermPrice);
//        editInstructorName=findViewById(R.id.TermName);
//        editInstructorNumber=findViewById(R.id.TermPrice);
//        editInstructorEmail=findViewById(R.id.TermPrice);

        if(currentTerm!=null){
            termTitle=currentTerm.getTermTitle();
            termStart=currentTerm.getTermStart();
            termEnd=currentTerm.getTermEnd();
        }
        if(courseTermID!=-1){
            editTermTitle.setText(termTitle);
            editTermStart.setText(termStart);
            editTermEnd.setText(termEnd);
        }
        repository= new ScheduleRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.associated_courses);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CourseEntity> filteredCourses=new ArrayList<>();
        for(CourseEntity c:repository.getAllCourses()){
            if(c.getCourseTermID()==courseTermID)filteredCourses.add(c);
        }
        numCourses=filteredCourses.size();
        adapter.setWords(filteredCourses);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void addPart(View view) {
//        Intent intent=new Intent(PartActivity.this,PartDetail.class);
//        intent.putExtra("TermID",Id);
//        startActivity(intent);
//    }

    public void addTermFromScreen(View view) {
        TermEntity t;

        if (courseTermID != -1)
            t = new TermEntity(courseTermID, editTermTitle.getText().toString(),  editTermStart.getText().toString(), editTermEnd.getText().toString());
        else {
            List<TermEntity> allTerms = repository.getAllTerms();
            courseTermID = allTerms.get(allTerms.size() - 1).getTermID();
            t = new TermEntity(++courseTermID, editTermTitle.getText().toString(),  editTermStart.getText().toString(), editTermEnd.getText().toString());
        }
        repository.insert(t);
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_delete, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.delete) {
//            if(numParts==0) {
//                inventoryManagementRepository.delete(currentTerm);
//            }
//            else{
//                Toast.makeText(getApplicationContext(),"Can't delete a Term with parts",Toast.LENGTH_LONG).show();// make another toast
//            }
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}