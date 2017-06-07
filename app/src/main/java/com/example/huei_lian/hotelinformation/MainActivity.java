package com.example.huei_lian.hotelinformation;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HotelArrayAdapter adapter = null;

    public static final int LIST_HOTELS = 1;


    private void refreshHotelList(List<Hotel> hotels) {
        adapter.clear();
        adapter.addAll(hotels);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LIST_HOTELS: {
                    List<Hotel> hotels = (List<Hotel>)msg.obj;
                    refreshHotelList(hotels);
                    break;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvHotels = (ListView)findViewById(R.id.listview_hotel);


        adapter = new HotelArrayAdapter(this, new ArrayList<Hotel>());
        lvHotels.setAdapter(adapter);

        getPetsFromFirebase();

    }

    private void getPetsFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new FirebaseThread(dataSnapshot,handler).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.v("Hotel Info", databaseError.getMessage());
            }
        });
    }

}