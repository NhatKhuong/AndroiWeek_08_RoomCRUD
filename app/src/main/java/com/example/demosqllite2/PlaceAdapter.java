package com.example.demosqllite2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private List<Place> list;

    public PlaceAdapter(Context context, int idLayout, List<Place> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }

        Appdatabase db = Room.databaseBuilder(context.getApplicationContext(),
                Appdatabase.class, "database-name").build();

        PlaceDao placeDao = db.placeDao();

        Place place = list.get(i);
        System.out.println(place);
        System.out.println("-->"+list.size());

        TextView txt = view.findViewById(R.id.txtinput);
        Button btnAdd = view.findViewById(R.id.btnadd);
        ImageButton btnU = view.findViewById(R.id.btnU);
        ImageButton btnD = view.findViewById(R.id.btnD);
        TextView name = view.findViewById(R.id.name);

        name.setText(place.getName());

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Place place1 = new Place();
//                place1.setName(txt.getText().toString());
//                MainActivity mainActivity = MainActivity.getMainActivity();
//                mainActivity.getdb().addPlace(place1);
//                notifyDataSetChanged();
//            }
//        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.getMainActivity();
                Appdatabase.getInstance(context).placeDao().delete(place);
                mainActivity.update();
            }
        });

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Detail_item.class);
                intent.putExtra("place",place);
                context.startActivity(intent);
            }
        });
        return  view;


    }
}
