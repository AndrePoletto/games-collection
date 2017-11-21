package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AddProducerActivity extends AppCompatActivity {
    public static final int ASKRESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_add);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void saveProducer(){}
    public void cancelAdd(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_selected_to_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            case R.id.saveMenu:
                saveProducer();
                return true;
            case R.id.cancelMenu:
                cancelAdd();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
