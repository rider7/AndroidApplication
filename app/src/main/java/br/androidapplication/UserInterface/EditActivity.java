package br.androidapplication.UserInterface;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.Entity.AssessmentEntity;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;

public class EditActivity  extends AppCompatActivity {
    private ScheduleRepository repository;
    public static int numAlert;
    static int id2;
    int assessmentID;
    int courseTermID;
    int courseAssessmentID;
    String assessmentType;
    String assessmentTitle;
    String assessmentStart;
    String assessmentEnd;

    EditText editAssessmentTitle;
    EditText editAssessmentType;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;

    TermEntity currentTerm;
    CourseEntity currentCourse;
    AssessmentEntity currentAssessment;
    public static int numCourses;
    public static int numAssessments;

    Calendar myCalendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener  myDate;
    Long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        assessmentID=getIntent().getIntExtra("assessmentID",-1);
        courseAssessmentID=getIntent().getIntExtra("courseAssessmentID",-1);
        assessmentTitle=getIntent().getStringExtra("AssessmentTitle");
        assessmentType=getIntent().getStringExtra("AssessmentType");
        assessmentStart=getIntent().getStringExtra("AssessmentStart");
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
        editAssessmentStart=findViewById(R.id.AssessmentStart);;
        editAssessmentEnd=findViewById(R.id.AssessmentEnd);;

        if(currentAssessment!=null){
            assessmentTitle=currentAssessment.getAssessmentTitle();
            assessmentType=currentAssessment.getAssessmentType();
            assessmentStart=currentAssessment.getAssessmentStart();
            assessmentEnd=currentAssessment.getAssessmentEnd();
        }
        if(courseAssessmentID!=-1&&id2==-1){
            editAssessmentTitle.setText(assessmentTitle);
            editAssessmentType.setText(assessmentType);
            editAssessmentStart.setText(assessmentStart);
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
        if (id == R.id.sharing) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            // (Optional) Here we're setting the title of the content
            sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message title");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }
        if (id == R.id.start_notification) {
            String assessmentTitle = editAssessmentTitle.getText().toString();
            String dateFromScreen = editAssessmentStart.getText().toString();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();

            Intent intent = new Intent(EditActivity.this, MyReceiver.class);
            intent.putExtra("key", "The following assessment starts today: " + assessmentTitle);
            PendingIntent sender = PendingIntent.getBroadcast(EditActivity.this, ++MainActivity.numAlert, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            //long date=1336226501635L;
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        }

            if (id == R.id.end_notification) {
                String assessmentTitle2=editAssessmentTitle.getText().toString();
                String dateFromScreen2=editAssessmentEnd.getText().toString();
            String myFormat2 = "MM/dd/yy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);
            Date myDate2 = null;
            try {
                myDate2=sdf2.parse(dateFromScreen2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger2 = myDate2.getTime();

                Intent intent2=new Intent(EditActivity.this,MyReceiver.class);
                intent2.putExtra("key","The following assessment ends today: " + assessmentTitle2);
                PendingIntent sender2= PendingIntent.getBroadcast(EditActivity.this,++MainActivity.numAlert,intent2,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                //long date=1336226501635L;
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
                repository.delete(currentAssessment);
                Intent intent = new Intent (EditActivity.this, AssessmentActivity.class);
                intent.putExtra("courseID",courseAssessmentID);
                startActivity(intent);
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
            a = new AssessmentEntity(assessmentID, courseAssessmentID, editAssessmentType.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString());
        }else {
            List<AssessmentEntity> allAssessments = repository.getAllAssessments();
            assessmentID = allAssessments.get(allAssessments.size() - 1).getAssessmentID();
            a = new AssessmentEntity(++assessmentID, courseAssessmentID, editAssessmentType.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString());        }
        repository.insert(a);
            Intent intent = new Intent (EditActivity.this, AssessmentActivity.class);
            intent.putExtra("courseID",courseAssessmentID);
            startActivity(intent);
    }
}
