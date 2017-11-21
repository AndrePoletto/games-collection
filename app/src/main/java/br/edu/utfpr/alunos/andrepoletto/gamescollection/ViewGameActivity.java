package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ViewGameActivity extends AppCompatActivity {
    public static final String MODE    = "MODE";
    public static final int    EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void editGame(){
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, EDIT);
        //Starts a new Activity
        startActivityForResult(intent, EDIT);
    }

    public void deleteGame(){}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_selected, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            case R.id.editGameMenu:
                editGame();
                return true;
            case R.id.deleteGameMenu:
                deleteGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
