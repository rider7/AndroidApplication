package br.androidapplication.UserInterface;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.androidapplication.Database.ScheduleDatabase;
import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;
//Main entry point into the application
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterButton(View view) {
        Intent intent= new Intent(MainActivity.this,TermActivity.class);
        startActivity(intent);
        //test
    }
}

//    private ScheduleRepository repository;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        //Create repository object
//        ScheduleRepository repository = new ScheduleRepository(getApplication());
//        //Create termEntity object with following values
//        TermEntity termEntity = new TermEntity(1,"Spring","12/12/12", "01/23/12");
//        //repository.delete(termEntity);
//        //Pass the termEntity object with the values into the insert method of the repository class
//        repository.insert(termEntity);
//
//        //Get list of terms from the db using repository instance with method get allterms
//        List<TermEntity> allTerms = repository.getAllTerms();
//        //Set list on recyclerview create recyclerview
//        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
//        //create adapter
//        final TermAdapter termAdapter = new TermAdapter(this);
//        //Put adapter on recyclerview
//        recyclerView.setAdapter(termAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //Set the terms on the term adapter with the all terms list made above
//        termAdapter.setTerm(allTerms);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
////    public void enterButton(View view) {
////        Intent intent= new Intent(MainActivity.this,TermActivity.class);
////        startActivity(intent);
////    }
//}