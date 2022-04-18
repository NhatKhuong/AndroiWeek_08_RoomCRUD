package com.example.demosqllite2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Place> list;
    PlaceAdapter adt;

    private TextView txt;

    private static Appdatabase db;
    private static  PlaceDao placeDao;
    private static  MainActivity mainActivity;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
//        db = new DatabaseHandle(this);
        listView = findViewById(R.id.lvPlace);
        list = new ArrayList<>();
        list = Appdatabase.getInstance(this).placeDao().getAll();
        System.out.println(list);
        adt = new PlaceAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adt);


        btnAdd = findViewById(R.id.btnadd);
        txt = findViewById(R.id.txtinput);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              add();
              txt.setText("");
              update();
              hideSoftKeyboard();

            }
        });
    }

    public void update() {
        list=  Appdatabase.getInstance(this).placeDao().getAll();
        adt = new PlaceAdapter(this, R.layout.list_item,list);
        listView.setAdapter(adt);

    }

    public void add(){
        Place place1 = new Place(txt.getText().toString());
        Appdatabase.getInstance(this).placeDao().insert(place1);

    }

    public void hideSoftKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }



    public static MainActivity getMainActivity(){
        return  mainActivity;
    }


}