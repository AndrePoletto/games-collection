package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.utfpr.alunos.andrepoletto.gamescollection.dao.GameDao;
import br.edu.utfpr.alunos.andrepoletto.gamescollection.model.Game;

public class GameActivity extends AppCompatActivity {

    public EditText gameNameEditText, releaseEditText, producerEditText;
    public RadioGroup radioGroupOne, radioGroupTwo;
    public RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gameNameEditText = (EditText) findViewById(R.id.gameNameEditText);
        releaseEditText = (EditText) findViewById(R.id.releaseEditText);
        producerEditText = (EditText) findViewById(R.id.producerEditText);
        radioGroupOne = (RadioGroup) findViewById(R.id.radioGroupOneEdit);
        radioGroupTwo = (RadioGroup) findViewById(R.id.radioGroupTwoEdit);
        ratingBar = (RatingBar) findViewById(R.id.editRatingBar);

        radioGroupOne.setOnCheckedChangeListener(listener1);
        radioGroupTwo.setOnCheckedChangeListener(listener2);
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupTwo.setOnCheckedChangeListener(null); // remove the listener before clearing
                radioGroupTwo.clearCheck(); // clear the second RadioGroup!
                radioGroupTwo.setOnCheckedChangeListener(listener2); //reset the listener
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupOne.setOnCheckedChangeListener(null);
                radioGroupOne.clearCheck();
                radioGroupOne.setOnCheckedChangeListener(listener1);
            }
        }
    };

    public void showAddProducerActivity(View view){
        //Creates the intent of initiate the AddProducerActivity
        Intent intent = new Intent(this, AddProducerActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, AddProducerActivity.ASKRESULT);
    }

    public void saveGame(){
        GameDao dao = new GameDao(this);
        Game game = new Game();
        game.setTitle(gameNameEditText.getText().toString());
        game.setRelease(releaseEditText.getText().toString());
        game.setProducer(producerEditText.getText().toString());
        switch (radioGroupOne.getCheckedRadioButtonId()){
            case R.id.xBoxRadioButton:
                game.setPlataform("xBox One");
                break;
            case R.id.psRadioButton:
                game.setPlataform("Playstation 4");
                break;
        }

        switch (radioGroupTwo.getCheckedRadioButtonId()) {
            case R.id.NinRadioButton:
                game.setPlataform("Nintendo Switch");
                break;
            case R.id.pcRadioButton:
                game.setPlataform("PC");
                break;
            default:
                break;
        }


        game.setRate(Double.valueOf(ratingBar.getProgress()));

        dao.insert(game);
        dao.close();

        Toast.makeText(this, getString(R.string.successMsg) + game.getTitle() + getString(R.string.endSuccessMsg), Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void cancelEdit(){
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
                saveGame();
                return true;
            case R.id.cancelMenu:
                cancelEdit();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}
