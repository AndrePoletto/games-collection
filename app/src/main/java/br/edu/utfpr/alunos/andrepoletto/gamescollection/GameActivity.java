package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    public EditText gameNameEditText, releaseEditText;
    public Spinner producer_spinner;
    public RadioGroup radioGroupOne, radioGroupTwo;
    public RatingBar editRatingBar;
    public Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gameNameEditText = (EditText) findViewById(R.id.gameNameEditText);
        releaseEditText = (EditText) findViewById(R.id.releaseEditText);
        producer_spinner = (Spinner) findViewById(R.id.producer_spinner_edit);
        radioGroupOne = (RadioGroup) findViewById(R.id.radioGroupOneEdit);
        radioGroupTwo = (RadioGroup) findViewById(R.id.radioGroupTwoEdit);
        editRatingBar = (RatingBar) findViewById(R.id.editRatingBar);

        myCalendar = Calendar.getInstance();

        radioGroupOne.setOnCheckedChangeListener(listener1);
        radioGroupTwo.setOnCheckedChangeListener(listener2);
        releaseEditText.setOnClickListener(listener0);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private View.OnClickListener listener0 = new  View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Auto-generated method stub
            new DatePickerDialog(GameActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupTwo.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        releaseEditText.setText(sdf.format(myCalendar.getTime()));
    }

    public void showAddProducerActivity(View view){
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, AddProducerActivity.ASKRESULT);
    }

    public void saveGame(){}

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


    /**
     * Function to load the spinner data from SQLite database
     * */
    //private void loadSpinnerData() {
    // database handler
    //DatabaseHandler db = new DatabaseHandler(getApplicationContext());

    // Spinner Drop down elements
    //   List<String> labels = new ArrayList<>();
    //   labels.add("one");
    //   labels.add("two");/*db.getAllLabels()*/;

    // Creating adapter for spinner
    //  ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
    //         android.R.layout.simple_spinner_item, labels);

    // Drop down layout style - list view with radio button
    // dataAdapter
    //        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    //  producer_spinner.setAdapter(dataAdapter);
    //}
}
