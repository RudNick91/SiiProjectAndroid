package com.example.programista.siimusicapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.net.URL;
import java.util.HashMap;


public class MainPiano extends Activity implements View.OnTouchListener {

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_9;
    private Button button_10;
    private Button button_11;
    private Button button_12;
    private Button button_13;

    private Button button_play;
    private Button button_stop;
    private Button button_exit;

    private long lastDown;
    private long keyPressedDuration ;

    public String songId;

    Parser Parser = new Parser();


    //koniecznosc do obslugi multitouch
    private MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_piano);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

        toolbar.setLogo(android.R.drawable.ic_media_play);

        button_1 = (Button) findViewById(R.id.button_kl1);
        button_2 = (Button) findViewById(R.id.button_kl2);
        button_3 = (Button) findViewById(R.id.button_kl3);
        button_4 = (Button) findViewById(R.id.button_kl4);
        button_5 = (Button) findViewById(R.id.button_kl5);
        button_6 = (Button) findViewById(R.id.button_kl6);
        button_7 = (Button) findViewById(R.id.button_kl7);
        button_9 = (Button) findViewById(R.id.button_kl9);
        button_10 = (Button) findViewById(R.id.button_kl10);
        button_11 = (Button) findViewById(R.id.button_kl11);
        button_12 = (Button) findViewById(R.id.button_kl12);
        button_13 = (Button) findViewById(R.id.button_kl13);

        button_play = (Button) findViewById(R.id.button_rec);
        button_stop = (Button) findViewById(R.id.button_stop);
        button_exit = (Button) findViewById(R.id.button_end);



      /*  button_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastDown = System.currentTimeMillis();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    keyPressedDuration = System.currentTimeMillis() - lastDown;

                    String key_duration = String.valueOf(keyPressedDuration);

                  //  new Send_input().execute(key_duration);

                }
                return true;
            }
        });*/

        button_1.setOnTouchListener(this);
        button_2.setOnTouchListener(this);
        button_3.setOnTouchListener(this);
        button_4.setOnTouchListener(this);
        button_5.setOnTouchListener(this);
        button_6.setOnTouchListener(this);
        button_7.setOnTouchListener(this);
        button_9.setOnTouchListener(this);
        button_10.setOnTouchListener(this);
        button_11.setOnTouchListener(this);
        button_12.setOnTouchListener(this);
        button_13.setOnTouchListener(this);
        button_play.setOnTouchListener(this);
        button_stop.setOnTouchListener(this);
        button_exit.setOnTouchListener(this);




    }


//nadpisany ontouchlistener wykrywania i obsluga przytrzyman

    @Override
    public boolean onTouch( View button, MotionEvent theMotion ) {


        switch ( theMotion.getAction() ) {

            case MotionEvent.ACTION_DOWN:

                lastDown = System.currentTimeMillis();

                button.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black_press));


                PlayTone(button.getId());

                break;
            case MotionEvent.ACTION_UP:

                KillTone(button.getId());

             //   toneGen1.stopTone();
          //      mp1.stop();
              //  mp1.pause();
          //     mp1.release();

                keyPressedDuration = System.currentTimeMillis() - lastDown;
                String key_duration = String.valueOf(keyPressedDuration);

                String id=keyValueExtract(button.getId());

                String[] array = {key_duration, id};

                if(songId != null && !songId.isEmpty()) {

                    new Send_input().execute(array);

                }



                break;
        }
        return true;
    }


//zakonczenie odtwarzania nuty

    public void KillTone (int id){

        switch(id){
            case R.id.button_kl1:
                //  toneGen1.startTone(ToneGenerator.TONE_DTMF_1);

                mp1.stop();
                mp1.release();

                break;
            case R.id.button_kl2:
                //   toneGen1.startTone(ToneGenerator.TONE_DTMF_3);

                mp2.stop();
                mp2.release();

                break;
            case R.id.button_kl3:
                //     toneGen1.startTone(ToneGenerator.TONE_DTMF_5);
                mp3.stop();
                mp3.release();
                break;
            case R.id.button_kl4:
                //      toneGen1.startTone(ToneGenerator.TONE_DTMF_7);
                mp4.stop();
                mp4.release();
                break;
            case R.id.button_kl5:
                //    toneGen1.startTone(ToneGenerator.TONE_DTMF_8);
                mp5.stop();
                mp5.release();
                break;
            case R.id.button_kl6:
                //    toneGen1.startTone(ToneGenerator.TONE_DTMF_A);
                mp6.stop();
                mp6.release();
                break;
            case R.id.button_kl7:
                //     toneGen1.startTone(ToneGenerator.TONE_DTMF_C);
                mp7.stop();
                mp7.release();
                break;

// czarne klawisze

            case R.id.button_kl9:
                //     toneGen1.startTone(ToneGenerator.TONE_DTMF_2);
                mp8.stop();
                mp8.release();
                break;
            case R.id.button_kl10:
                //    toneGen1.startTone(ToneGenerator.TONE_DTMF_4);
                mp9.stop();
                mp9.release();
                break;
            case R.id.button_kl11:
                //     toneGen1.startTone(ToneGenerator.TONE_DTMF_6);
                mp10.stop();
                mp10.release();
                break;
            case R.id.button_kl12:
                //      toneGen1.startTone(ToneGenerator.TONE_DTMF_9);
                mp11.stop();
                mp11.release();
                break;
            case R.id.button_kl13:
                //       toneGen1.startTone(ToneGenerator.TONE_DTMF_B);
                mp12.stop();
                mp12.release();
                break;
        }

    }


//rozpoczecie odtwarzania nuty

    public void PlayTone (int id){

        switch(id){
            case R.id.button_kl1:
              //  toneGen1.startTone(ToneGenerator.TONE_DTMF_1);
                mp1 = MediaPlayer.create(this, R.raw.g);
                mp1.start();
                break;
            case R.id.button_kl2:
             //   toneGen1.startTone(ToneGenerator.TONE_DTMF_3);
                mp2 = MediaPlayer.create(this, R.raw.bb);
                mp2.start();
                break;
            case R.id.button_kl3:
           //     toneGen1.startTone(ToneGenerator.TONE_DTMF_5);
                mp3 = MediaPlayer.create(this, R.raw.cc);
                mp3.start();
                break;
            case R.id.button_kl4:
          //      toneGen1.startTone(ToneGenerator.TONE_DTMF_7);
                mp4 = MediaPlayer.create(this, R.raw.d);
                mp4.start();
                break;
            case R.id.button_kl5:
            //    toneGen1.startTone(ToneGenerator.TONE_DTMF_8);
                mp5 = MediaPlayer.create(this, R.raw.eb);
                mp5.start();
                break;
            case R.id.button_kl6:
            //    toneGen1.startTone(ToneGenerator.TONE_DTMF_A);
                mp6 = MediaPlayer.create(this, R.raw.ff);
                mp6.start();
                break;
            case R.id.button_kl7:
           //     toneGen1.startTone(ToneGenerator.TONE_DTMF_C);
                mp7 = MediaPlayer.create(this, R.raw.gg);
                mp7.start();
                break;

// czarne klawisze

            case R.id.button_kl9:
           //     toneGen1.startTone(ToneGenerator.TONE_DTMF_2);
                mp8 = MediaPlayer.create(this, R.raw.a);
                mp8.start();
                break;
            case R.id.button_kl10:
            //    toneGen1.startTone(ToneGenerator.TONE_DTMF_4);
                mp9 = MediaPlayer.create(this, R.raw.b);
                mp9.start();
                break;
            case R.id.button_kl11:
           //     toneGen1.startTone(ToneGenerator.TONE_DTMF_6);
                mp10 = MediaPlayer.create(this, R.raw.c);
                mp10.start();
                break;
            case R.id.button_kl12:
          //      toneGen1.startTone(ToneGenerator.TONE_DTMF_9);
                mp11 = MediaPlayer.create(this, R.raw.e);
                mp11.start();
                break;
            case R.id.button_kl13:
         //       toneGen1.startTone(ToneGenerator.TONE_DTMF_B);
                mp12 = MediaPlayer.create(this, R.raw.f);
                mp12.start();
                break;
        }

    }



//wczytywanie wartosci przyciskow

    public String keyValueExtract (int id){
        String keyValue = null;

        switch(id){
            case R.id.button_kl1:
                keyValue = "1";

                button_1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl2:
                keyValue = "3";

                button_2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl3:
                keyValue = "5";

                button_3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl4:
                keyValue = "7";

                button_4.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl5:
                keyValue = "8";

                button_5.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl6:
                keyValue = "10";

                button_6.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl7:
                keyValue = "12";

                button_7.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_kl9:
                keyValue = "2";

                button_9.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black));

                break;
            case R.id.button_kl10:
                keyValue = "4";

                button_10.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black));

                break;
            case R.id.button_kl11:
                keyValue = "6";

                button_11.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black));

                break;
            case R.id.button_kl12:
                keyValue = "9";

                button_12.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black));

                break;
            case R.id.button_kl13:
                keyValue = "11";

                button_13.setBackground(ContextCompat.getDrawable(this, R.drawable.button_black));

                break;
            case R.id.button_rec:
                new New_record().execute();
                button_play.setVisibility(View.GONE);

                button_play.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_stop:
                songId="";
                button_play.setVisibility(View.VISIBLE);

                button_stop.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                break;
            case R.id.button_end:

                button_exit.setBackground(ContextCompat.getDrawable(this, R.drawable.button_white));

                finish();
              /*  Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
                break;
        }


        return keyValue;
    }

    //setter id nuty


    public void setId (String Id){
        songId = Id;
    }

    //async taski

    class Send_input extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {



            String url = "http://192.168.0.106:8090/sendSound";

            HashMap<String, String> parameters = new HashMap<String, String>();

            parameters.put("id", songId);
            parameters.put("line", args[1]);
            parameters.put("time", args[0]);


            String json = Parser.makeHttpRequest(url, "POST", parameters);
            Log.d("Zwrotny string", json);
            return null;
        }



    }

    class New_record extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            String url = "http://192.168.0.106:8090/startRecord";

            HashMap<String, String> parameters = new HashMap<String, String>();

            String json = Parser.makeHttpRequest(url, "POST", parameters);
            setId(json);

            Log.d("Zwrotny string", json);
            return null;
        }
    }


}
