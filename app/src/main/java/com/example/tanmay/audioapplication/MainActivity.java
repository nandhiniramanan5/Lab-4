package com.example.tanmay.audioapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.pm.PackageManager;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startRecord, stopRecord, startPlay, stopPlay;
    String path = null;
    MediaRecorder mediaRecorder;
    public static final int PermissionCode = 1;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startRecord = (Button) findViewById(R.id.record);
        stopRecord = (Button) findViewById(R.id.stopRecord);
        startPlay = (Button) findViewById(R.id.play);
        stopPlay = (Button) findViewById(R.id.stop);

    }

    public void MediaRecorderReady(){
        // Check if mediaRecorder is Ready. Check before recording and check once it is stopped.
    }

    private boolean permission() {

        // Check for Permission here

    }

    private void request() {
        // Request for Permission here


    }
    public void recordClick(View view){
        if(permission()) {
            // Get a Random integer and set path for the audio file
            Random rand = new Random();
            int random = rand.nextInt(100) + 1;

            String ext_path = "AudioRecording" + random + ".3gp";
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + ext_path;

            // Check if Media Recorder Ready
            MediaRecorderReady();


            // Prepare and start Audio Recording


            // Disable startRecord and Enable stopRecording button.

            Toast.makeText(MainActivity.this, "Recording Start",
                    Toast.LENGTH_LONG).show();
        } else {
            request();
        }
    }

    public void stopRecordClick(View view){
        // Stop Recording

        // Enable Recording button. Disable Stop Recording. Enable Play and Disable Stop.

        Toast.makeText(MainActivity.this, "Recording Complete",
                Toast.LENGTH_LONG).show();
    }

    public void startPlay(View view){
        // Take care of the buttons.


        // initialize mediaplayer and set the path.


        Toast.makeText(MainActivity.this, "Record Playing",
                Toast.LENGTH_LONG).show();
    }

    public void stopPlay(View view){
        // Take care of the buttons.


        // Stop the audio

    }

}
