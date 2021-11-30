package br.androidapplication.UserInterface;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.R;

import android.content.Intent;
import android.widget.EditText;

public class TermActivity extends AppCompatActivity {
    int id;
    String title;
    EditText editTitle;
    private ScheduleRepository repository;


    //New code based on My Bicycle Cohort transferring mainactivity to termactivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //PartDetail.id2=-1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        repository = new ScheduleRepository(getApplication());
        repository.getAllTerms();// this is really just to set up the database if there isn't one on your device yet-otherwise synch errors later
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerm(repository.getAllTerms());

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    //Intent to change to courseActivity
    public void addTerm(View view) {
        Intent intent = new Intent(TermActivity.this, CourseActivity.class);
        startActivity(intent);
    }
}

// 11-27 original code not working
 //   @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_term);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //termTitle from adapter
//        title = getIntent().getStringExtra("termTitle");
//        //termname from term_list_item
//        editTitle = findViewById(R.id.termname);
//        repository = new ScheduleRepository(getApplication());
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    //Used to update terms
//    public void saveTerm(View view){
//        String screenTitle = editTitle.getText().toString();
//        if(title == null) {
//            TermEntity newTerm = new TermEntity(++id, screenTitle, "test", "test2");
//            repository.insert(newTerm);
//        } else {
//            TermEntity oldTerm =  new TermEntity(getIntent().getIntExtra("termID", -1), screenTitle, "test", "test2");
//            repository.update(oldTerm);
//        }
//    }

