package com.example.demosqllite2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Detail_item extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Appdatabase db = Room.databaseBuilder(getApplicationContext(),
                Appdatabase.class, "database-name").build();


        Place place = (Place) getIntent().getSerializableExtra("place");
        EditText editText = findViewById(R.id.edittxt);
        Button btnsave = findViewById(R.id.btnluu);
        editText.setText(place.getName());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place.setName(editText.getText().toString());
                MainActivity mainActivity = MainActivity.getMainActivity();
              Appdatabase.getInstance(Detail_item.this).placeDao().update(place);
              mainActivity.hideSoftKeyboard();


                onBackPressed();
                mainActivity.update();
            }
        });

    }
}