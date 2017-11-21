package br.edu.utfpr.alunos.andrepoletto.gamescollection;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.utfpr.alunos.andrepoletto.gamescollection.dao.GameDao;
import br.edu.utfpr.alunos.andrepoletto.gamescollection.model.Game;

public class MainActivity extends AppCompatActivity {
    public static final String MODE    = "MODE";
    //public static final String ID      = "ID";
    public static final int    NEW    = 1;
    public static final int    EDIT = 2;
    //public static final int    VIEW = 3;

    public ListView gamesList;
    public Game gameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamesList = (ListView) findViewById(R.id.gamesList);

        registerForContextMenu(gamesList);
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch(which){
                case DialogInterface.BUTTON_POSITIVE:
                    deleteGame(gameSelected);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onResume(){
        loadList();
        super.onResume();
    }

    private void loadList() {
        GameDao dao = new GameDao(this);
        List<Game> games = dao.searchGames();
        dao.close();

        ArrayAdapter<Game> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, games);
        gamesList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            case R.id.aboutMenuItem:
                showAboutActivity();
                return true;
            case R.id.addGameMenu:
                showAddGameActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        gameSelected = (Game) gamesList.getItemAtPosition(info.position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editMenuItem:
                showEditGameActivity();
                return true;
            case R.id.deleteMenuItem:
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
                return super.onContextItemSelected(item);
        }
    }

    public void showAboutActivity() {
        //Creates the intent of initiate the AboutActivity
        Intent intent = new Intent(this, AboutActivity.class);
        //Starts a new Activity
        startActivityForResult(intent, AboutActivity.ASKRESULT);
    }

    public void showAddGameActivity() {
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, NEW);
        //Starts a new Activity
        startActivityForResult(intent, NEW);
    }

    public void showEditGameActivity() {
        //Creates the intent of initiate the GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        //Adds info on intent
        intent.putExtra(MODE, EDIT);
        //Starts a new Activity
        startActivityForResult(intent, EDIT);
    }

    public void deleteGame(Game game){
        GameDao dao = new GameDao(this);
        dao.delete(game);
        dao.close();
        loadList();
    }
}
