package br.androidapplication.UserInterface;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.androidapplication.Database.ScheduleRepository;
import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;

import android.content.Intent;
import android.widget.EditText;

import java.util.Objects;

public class TermActivity extends AppCompatActivity {
    int id;
    String title;
    EditText editTitle;
    ScheduleRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_list_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //termTitle from adapter
        title = getIntent().getStringExtra("termTitle");
        //termname from term_list_item
        editTitle = findViewById(R.id.termname);
        repository = new ScheduleRepository(getApplication());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Used to update terms
    public void saveTerm(View view){
        String screenTitle = editTitle.getText().toString();
        if(title == null) {
            TermEntity newTerm = new TermEntity(++id, screenTitle, "test", "test2");
            repository.insert(newTerm);
        } else {
            TermEntity oldTerm =  new TermEntity(getIntent().getIntExtra("termID", -1), screenTitle, "test", "test2");
            repository.update(oldTerm);
        }
    }
}
