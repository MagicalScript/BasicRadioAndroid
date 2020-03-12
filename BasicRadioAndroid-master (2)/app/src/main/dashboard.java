package com.clickgostudio.air1072;

/**
 * Created by BegHead on 30/05/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class dashboard extends Activity{

  /** Called when the activity is first created. */

  private SeekBar volumeSeekbar = null;
  private AudioManager audioManager = null;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setVolumeControlStream(AudioManager.STREAM_MUSIC);
    setContentView(R.layout.dashboard);
    initControls();

    GridView gridView = (GridView)findViewById(R.id.gridview);
    final BooksAdapter booksAdapter = new BooksAdapter(this, radioStation.stations);
    gridView.setAdapter(booksAdapter);
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView parent, View view, int position, long id) {
        radioStation _rs = radioStation.stations[position];
        //_rs.toggleFavorite();
// code to change station to the selected one .........................................................................
        // This tells the GridView to redraw itself
        // in turn calling your BooksAdapter's getView method again for each cell
        booksAdapter.notifyDataSetChanged();
      }
    });
  }

  private void initControls()
  {
    try
    {
      volumeSeekbar = (SeekBar)findViewById(R.id.seekBar);
      audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
      volumeSeekbar.setMax(audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
      volumeSeekbar.setProgress(audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC));


      volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
      {
        @Override
        public void onStopTrackingTouch(SeekBar arg0)
        {
        }

        @Override
        public void onStartTrackingTouch(SeekBar arg0)
        {
        }

        @Override
        public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
        {
          audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
            progress, 0);
        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}


