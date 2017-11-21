package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String MODE    = "MODE";
    //public static final String ID      = "ID";
    public static final int    NEW    = 1;
    public static final int    EDIT = 2;
    public static final int    VIEW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAboutActivity(View view) {
        //Creates the intent of initiate the AboutActivity
        Intent intent = new Intent(this, AboutActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, AboutActivity.ASKRESULT);
    }

    public void showAddGameActivity(View view) {
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, NEW);
        //Starts a new Activity
        startActivityForResult(intent, NEW);
    }

    public void showEditActivity(View view) {
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, EDIT);
        //Starts a new Activity
        startActivityForResult(intent, EDIT);
    }

    public void showViewActivity(View view) {
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, ViewGameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, VIEW);
        //Starts a new Activity
        startActivityForResult(intent, VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == NEW || requestCode == EDIT) && resultCode == Activity.RESULT_OK){
            //listaAdapter.notifyDataSetChanged();
        }
    }
}
