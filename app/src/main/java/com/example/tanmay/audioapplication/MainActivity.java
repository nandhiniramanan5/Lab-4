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

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

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
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(path);
    }

    private boolean permission() {
        // Check for Permission here
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void request() {
        // Request for Permission here
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, 1);

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
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Disable startRecord and Enable stopRecording button.
            startRecord.setEnabled(false);
            stopRecord.setEnabled(true);

            Toast.makeText(MainActivity.this, "Recording Start",
                    Toast.LENGTH_LONG).show();
        } else {
            request();
        }
    }

    public void stopRecordClick(View view){
        // Stop Recording
        mediaRecorder.stop();

        // Enable Recording button. Disable Stop Recording. Enable Play and Disable Stop.
        stopRecord.setEnabled(false);
        startPlay.setEnabled(true);
        startRecord.setEnabled(true);
        stopPlay.setEnabled(false);

        Toast.makeText(MainActivity.this, "Recording Complete",
                Toast.LENGTH_LONG).show();
    }

    public void startPlay(View view){
        // Take care of the buttons.
        stopRecord.setEnabled(false);
        startRecord.setEnabled(false);
        stopPlay.setEnabled(true);

        // initialize mediaplayer and set the path.
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        Toast.makeText(MainActivity.this, "Record Playing",
                Toast.LENGTH_LONG).show();
    }

    public void stopPlay(View view){
        // Take care of the buttons.
        stopRecord.setEnabled(false);
        startPlay.setEnabled(true);
        stopPlay.setEnabled(false);
        startRecord.setEnabled(true);

        // Stop the audio
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            MediaRecorderReady();
        }

    }

}
