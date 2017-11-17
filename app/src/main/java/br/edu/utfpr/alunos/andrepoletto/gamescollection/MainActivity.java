package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAboutActivity(View view) {
        //Creates the intent of initiate the SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, SecondActivity.ASKRESULT);
    }

    public void showAddGameActivity(View view) {
        //Creates the intent of initiate the SecondActivity
        Intent intent = new Intent(this, ThirdActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, ThirdActivity.ASKRESULT);
    }
}
