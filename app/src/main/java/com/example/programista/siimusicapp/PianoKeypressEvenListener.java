package com.example.programista.siimusicapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.net.URL;
import java.util.HashMap;

/**
 * Created by Programista on 2015-11-20.
 */
public class PianoKeypressEvenListener implements View.OnTouchListener {

    private long lastDown;
    private long keyPressedDuration ;

    public URL KEY_INPUT_URL;

    Parser Parser = new Parser();

    @Override
    public boolean onTouch( View button, MotionEvent theMotion ) {

        switch ( theMotion.getAction() ) {

            case MotionEvent.ACTION_DOWN:

                lastDown = System.currentTimeMillis();

                break;
            case MotionEvent.ACTION_UP:

                keyPressedDuration = System.currentTimeMillis() - lastDown;
                String key_duration = String.valueOf(keyPressedDuration);

                String id=String.valueOf(button.getId());

                String[] array = {key_duration, id};

                new Send_input().execute(array);

                break;
        }
        return true;
    }

    class Send_input extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {


            HashMap<String, String> parameters = new HashMap<String, String>();

            parameters.put("id", "6");
            parameters.put("line", args[0]);
            parameters.put("time", args[1]);

      //      String json = Parser.makeHttpRequest(KEY_INPUT_URL, "POST", parameters);
      //      Log.d("Zwrotny string", json);
            return null;
        }


    }
}
