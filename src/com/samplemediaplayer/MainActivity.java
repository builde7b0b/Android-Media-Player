package com.samplemediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dummy);
				mediaPlayer = MediaPlayer.create(MainActivity.this, path);
				mediaPlayer.start();
			}
		});
        
        //handling stop button
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
					mediaPlayer.release();
				}
			}
		});
    }
    
    //handling destory of this activity to release media player
    @Override
    protected void onDestroy() {
    	if(mediaPlayer!=null && mediaPlayer.isPlaying()) {
    		mediaPlayer.stop();
    		mediaPlayer.release();
    		mediaPlayer = null;
    	}
    	super.onDestroy();
    }
}
