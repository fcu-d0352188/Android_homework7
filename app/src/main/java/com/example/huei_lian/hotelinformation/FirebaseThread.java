package com.example.huei_lian.hotelinformation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huei-Lian on 2017/6/7.
 */

public class FirebaseThread extends Thread {
    //private static final int LIST_HOTELS = 1;

    private DataSnapshot dataSnapshot;
    private Handler handler = null;
    public FirebaseThread(DataSnapshot dataSnapshot, Handler handler) {
        this.dataSnapshot = dataSnapshot;
        this.handler = handler;

    }
    @Override
    public void run() {
        List<Hotel> hotelList = new ArrayList<>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            DataSnapshot dsName = ds.child("Name");
            DataSnapshot dsAddress = ds.child("Add");
            String name = (String)dsName.getValue();
            String address = (String)dsAddress.getValue();
            DataSnapshot dsImg = ds.child("Picture1");
            String imgUrl = (String) dsImg.getValue();
            Bitmap petImg = getImgBitmap(imgUrl);

            Hotel aHotel = new Hotel();
            aHotel.setName(name);
            aHotel.setAddress(address);
            aHotel.setImgUrl(petImg);
            hotelList.add(aHotel);


            Message msg = new Message();

            msg.what = MainActivity.LIST_HOTELS;
            msg.obj = hotelList;
            this.handler.sendMessage(msg);

            Log.v("Hotel Info", name + ";" + address);
        }
    }

    private Bitmap getImgBitmap(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            Bitmap bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}