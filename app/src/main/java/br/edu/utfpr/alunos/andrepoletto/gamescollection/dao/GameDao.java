package br.edu.utfpr.alunos.andrepoletto.gamescollection.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.alunos.andrepoletto.gamescollection.model.Game;

public class GameDao extends SQLiteOpenHelper {

    public GameDao(Context context) {
        super(context, "GameNotebook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Games (id INTEGER PRIMARY KEY, title TEXT NOT NULL, release TEXT NOT NULL, producer TEXT NOT NULL, platform TEXT NOT NULL, rate REAL NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Games";
        sqLiteDatabase.execSQL(sql);
    }

    public void insert(Game game) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("title", game.getTitle());
        data.put("release", game.getRelease());
        data.put("producer", game.getProducer());
        data.put("platform", game.getPlataform());
        data.put("rate", game.getRate());

        db.insert("Games", null, data);
    }

    public List<Game> searchGames() {
        String sql = "SELECT * FROM Games";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Game> games = new ArrayList<>();

        while(cursor.moveToNext()){
            Game game = new Game();
            game.setId(cursor.getLong(cursor.getColumnIndex("id")));
            game.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            game.setRelease(cursor.getString(cursor.getColumnIndex("release")));
            game.setProducer(cursor.getString(cursor.getColumnIndex("producer")));
            game.setPlataform(cursor.getString(cursor.getColumnIndex("platform")));
            game.setRate(cursor.getDouble(cursor.getColumnIndex("rate")));

            games.add(game);
        }
        cursor.close();

        return games;
    }

    public void delete(Game game) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {game.getId().toString()};
        db.delete("Games", "id = ?", params);
    }

    public void update(Game game) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("title", game.getTitle());
        data.put("release", game.getRelease());
        data.put("producer", game.getProducer());
        data.put("platform", game.getPlataform());
        data.put("rate", game.getRate());

        String[] params = {game.getId().toString()};

        db.update("Games", data, "id = ?", params);
    }
}
