package com.clickgostudio.air1072;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class RadioMediaPlayerService extends Service {
	//Variables
  private MyCustomObjectListener listener;
	public static boolean isPlaying = false;
	private MediaPlayer radioPlayer; //The media player instance
	private static int classID = 579; // just a number
	public static String START_PLAY = "START_PLAY";

	//Settings
	static Settings settings;


	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	/**
	 * Starts the streaming service
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

    if (intent.getBooleanExtra(START_PLAY, false)) {
      play();
      final Handler h = new Handler();
      h.postDelayed(new Runnable()
      {
        private long time = 0;

        @Override
        public void run()
        {
          // do stuff then

          MainActivity.showInterstitial(getBaseContext());

          // can call h again after work!
          time += 120000;
          Log.d("TimerExample", "Going for... " + time);
          if(isPlaying)
          h.postDelayed(this, 120000);
        }
      }, 120000); // 1 second delay (takes millis)
    }else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
      Log.i("RadioService", "Clicked Play");
      play();
    } else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)) {
      Log.i("RadioService", "Clicked Pause");
      Pause();
    } else if (intent.getAction().equals(Constants.ACTION.CLOSE_ACTION)) {
      Log.i("RadioService", "Clicked Pause");
      stop();
    }

		return Service.START_STICKY;
	}

static public void intsSetting(){
  if (settings == null)
  settings = new Settings();
}

	/**
	 * Starts radio URL stream
	 */
	@SuppressLint("NewApi")
	private void play() {
    intsSetting();
		//Check connectivity status
		if (isOnline()) {
			//Check if player already streaming
			if (!isPlaying) {
				isPlaying = true;

				//Return to the current activity
				Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Constants.ACTION.PLAY_ACTION);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
						Intent.FLAG_ACTIVITY_SINGLE_TOP);

				PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
          settings.getRadioImage());

        Intent playIntent = new Intent(this, RadioMediaPlayerService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
          playIntent, 0);

        Intent nextIntent = new Intent(this, RadioMediaPlayerService.class);
        nextIntent.setAction(Constants.ACTION.PAUSE_ACTION);
        PendingIntent ppauseIntent = PendingIntent.getService(this, 0,
          nextIntent, 0);

        Intent closeIntent = new Intent(this, RadioMediaPlayerService.class);
        closeIntent.setAction(Constants.ACTION.CLOSE_ACTION);
        PendingIntent pcloseintent = PendingIntent.getService(this, 0,
          closeIntent, 0);
				//Build and show notification for radio playing
				Notification notification = new Notification.Builder(getApplicationContext())
				.setContentTitle(settings.getRadioName())
				.setContentText(settings.getMainNotificationMessage())
				.setSmallIcon(R.drawable.radio_logo)
        .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
				.setContentIntent(pi)
          .addAction(android.R.drawable.ic_media_play, "Play",
            pplayIntent)
          .addAction(android.R.drawable.ic_media_pause, "Pause",
            ppauseIntent)
          .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Close",
            pcloseintent)
				.build();

				//Get stream URL
				radioPlayer = new MediaPlayer();
				try {
					radioPlayer.setDataSource(settings.getRadioStreamURL()); //Place URL here
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (settings.getAllowConsole()){
					//Buffering Info
					radioPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
						public void onBufferingUpdate(MediaPlayer mp, int percent) {
							Log.i("Buffering", "" + percent);
						}
					});
				}

				radioPlayer.prepareAsync();
				radioPlayer.setOnPreparedListener(new OnPreparedListener() {

					public void onPrepared(MediaPlayer mp) {
						radioPlayer.start(); //Start radio stream
					}
				});

				startForeground(classID, notification);

				//Display toast notification
				Toast.makeText(getApplicationContext(), settings.getPlayNotificationMessage(),
						Toast.LENGTH_LONG).show();


        this.listener.playerAction(isPlaying);
			}
		}
		else {
			//Display no connectivity warning
			Toast.makeText(getApplicationContext(), "No internet connection",
					Toast.LENGTH_LONG).show();
		}


	}


	/**
	 * Stops the stream if activity destroyed
	 */
	@Override
	public void onDestroy() {
		stop();
	}


	/**
	 * Stops audio from the active service
	 */
	private void stop() {
		if (isPlaying) {
			isPlaying = false;
			if (radioPlayer != null) {
				radioPlayer.release();
				radioPlayer = null;
			}
      this.listener.playerAction(isPlaying);
			stopForeground(true);
		}

		Toast.makeText(getApplicationContext(), "Stream stopped",
				Toast.LENGTH_LONG).show();
	}
  private void Pause() {
    if (isPlaying) {
      isPlaying = false;
      if (radioPlayer != null) {
        radioPlayer.release();
        radioPlayer = null;
      }
      this.listener.playerAction(isPlaying);
      //stopForeground(true);
    }

    Toast.makeText(getApplicationContext(), "Stream stopped",
      Toast.LENGTH_LONG).show();
  }


	/**
	 * Checks if there is a data or internet connection before starting the stream.
	 * Displays Toast warning if there is no connection
	 * @return online status boolean
	 */
	public boolean isOnline() {
		ConnectivityManager cm =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

  public void setListener(MyCustomObjectListener mlistner){
    listener = mlistner;
  }
}
