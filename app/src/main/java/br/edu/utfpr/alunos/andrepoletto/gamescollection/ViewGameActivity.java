package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.utfpr.alunos.andrepoletto.gamescollection.dao.GameDao;
import br.edu.utfpr.alunos.andrepoletto.gamescollection.model.Game;

public class ViewGameActivity extends AppCompatActivity {

    public static final String GAME = "GAME";

    public TextView nameTxt, releaseTxt, producerTxt, platformTxt, rateTxt;
    public Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameTxt = (TextView) findViewById(R.id.nameField);
        releaseTxt = (TextView) findViewById(R.id.releaseField);
        producerTxt = (TextView) findViewById(R.id.producerField);
        platformTxt = (TextView) findViewById(R.id.platformField);
        rateTxt = (TextView) findViewById(R.id.rateField);

        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra(GAME);
        if (game != null){
            nameTxt.setText(game.getTitle());
            releaseTxt.setText(game.getRelease());
            producerTxt.setText(game.getProducer());
            platformTxt.setText(game.getPlataform());
            rateTxt.setText(game.getRate().toString() );
        }
    }

    public void editGame(Game game){
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(GAME, game);
        //Starts a new Activity
        startActivity(intent);
        finish();
    }

    public void deleteGame(Game game){
        GameDao dao = new GameDao(this);
        dao.delete(game);
        dao.close();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_selected, menu);
        return true;
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch(which){
                case DialogInterface.BUTTON_POSITIVE:
                    deleteGame(game);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            case R.id.editGameMenu:
                editGame(game);
                return true;
            case R.id.deleteGameMenu:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(R.string.title_alert);
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setMessage(R.string.msgAlert);

                builder.setPositiveButton(R.string.option_yes, listener);
                builder.setNegativeButton(R.string.option_no, listener);

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
