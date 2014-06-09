package com.example.JavaDN;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity implements Runnable {

    TextView log;

    AndroidClassLoader classLoader;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        classLoader = new AndroidClassLoader(getApplicationContext());
        log = (TextView) findViewById(R.id.log);

        new Thread(this).start();
    }

    @Override
    public void run() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                log.setText("Loading lib...");
            }
        });

        classLoader.add("https://dl.dropboxusercontent.com/u/67269258/lib_class.jar");

        try {
            classLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Class<?> clsMath = classLoader.loadClass("com.example.JavaDN.Math");
                try {
                    IMath math = (IMath) clsMath.newInstance();

                    log.setText("Sum 5 + 5 = " + math.sum(5, 5) + " Sub: 5 - 4 = " + math.diff(5, 4));

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
