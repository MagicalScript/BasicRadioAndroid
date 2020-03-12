package com.clickgostudio.air1072.fragments;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.clickgostudio.air1072.BooksAdapter;
import com.clickgostudio.air1072.MyCustomObjectListener;
import com.clickgostudio.air1072.R;
import com.clickgostudio.air1072.RadioMediaPlayerService;
import com.clickgostudio.air1072.radioStation;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class OneFragment extends Fragment implements MyCustomObjectListener{


  /** Called when the activity is first created. */

  private SeekBar volumeSeekbar = null;
  private Integer Volume;
  private boolean isMute;
  private AudioManager audioManager = null;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

   View rootView;
  private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      rootView = inflater.inflate(R.layout.fragment_one, container, false);

      getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
      initControls();

      GridView gridView = (GridView)rootView.findViewById(R.id.gridview);
      final BooksAdapter booksAdapter = new BooksAdapter(getActivity(), radioStation.stations);
      gridView.setAdapter(booksAdapter);
      final ImageView mButton = (ImageView)rootView.findViewById(R.id.button3);
      gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
          radioStation _rs = radioStation.stations[position];
          radioStation.selectedStation = _rs;

          ((ImageView)rootView.findViewById(R.id.imageView3)).setImageResource(radioStation.selectedStation.imageName);
          ((TextView)rootView.findViewById(R.id.textView5)).setText(_rs.name);
          ((TextView)rootView.findViewById(R.id.textView6)).setText(_rs.detail);

          if(RadioMediaPlayerService.isPlaying){
            //stopping radio and restart
            Intent intent = new Intent(getActivity().getApplicationContext(),
              RadioMediaPlayerService.class);
            getActivity().stopService(intent);
            mButton.setImageResource(R.drawable.play);
            intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
            getActivity().startService(intent);
          }
          else{
            mButton.setImageResource(R.drawable.pause);
            Intent intent = new Intent(getActivity().getApplicationContext(),
              RadioMediaPlayerService.class);
            intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
            getActivity().startService(intent);
          }

          //_rs.toggleFavorite();
// code to change station to the selected one .........................................................................

          // This tells the GridView to redraw itself
          // in turn calling your BooksAdapter's getView method again for each cell
          booksAdapter.notifyDataSetChanged();
        }
      });
      //Play button

      mButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {


          if(!RadioMediaPlayerService.isPlaying){
            RadioMediaPlayerService.intsSetting();

            mButton.setImageResource(R.drawable.pause);
            Intent intent = new Intent(getActivity().getApplicationContext(),
              RadioMediaPlayerService.class);
            intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
            getActivity().startService(intent);

            //TextView tx = ((TextView)rootView.findViewById(R.id.textView5));
            ((ImageView)rootView.findViewById(R.id.imageView3)).setImageResource(radioStation.selectedStation.imageName);
            ((TextView)rootView.findViewById(R.id.textView5)).setText(radioStation.selectedStation.name);
            ((TextView)rootView.findViewById(R.id.textView6)).setText(radioStation.selectedStation.detail);
          }else{
            ((ImageView)rootView.findViewById(R.id.imageView3)).setImageResource(radioStation.selectedStation.imageName);
            ((TextView)rootView.findViewById(R.id.textView5)).setText(radioStation.selectedStation.name);
            ((TextView)rootView.findViewById(R.id.textView6)).setText(radioStation.selectedStation.detail);

            mButton.setImageResource(R.drawable.play);
            Intent intent = new Intent(getActivity().getApplicationContext(),
              RadioMediaPlayerService.class);
            getActivity().stopService(intent);
          }

        }
      });

      return rootView;
    }


  private void initControls()
  {
    try
    {
      volumeSeekbar = (SeekBar)rootView.findViewById(R.id.seekBar);
      audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
      volumeSeekbar.setMax(audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
      volumeSeekbar.setProgress(audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC));
      Volume = volumeSeekbar.getProgress();
      if(Volume > 0)
        isMute = false;

      final Button imageBtn = (Button)rootView.findViewById(R.id.imageButton);
      imageBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          if(!isMute) {
            Volume = volumeSeekbar.getProgress();
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
              0, 0);
            imageBtn.setBackgroundResource(R.drawable.speakerlucked);
            volumeSeekbar.setProgress(audioManager
              .getStreamVolume(AudioManager.STREAM_MUSIC));
            isMute = true;
          }else{
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
              Volume, 0);
            imageBtn.setBackgroundResource(R.drawable.speaker);
            volumeSeekbar.setProgress(audioManager
              .getStreamVolume(AudioManager.STREAM_MUSIC));
            isMute = false;
          }
        }});

      volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
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
          if(progress <= 0){
            isMute = true;
          imageBtn.setBackgroundResource(R.drawable.speakerlucked);}
          else{
            isMute = false;
          imageBtn.setBackgroundResource(R.drawable.speaker);}

        }
      });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void onResume() {
    final ImageView mButton = (ImageView)rootView.findViewById(R.id.button3);
    if(RadioMediaPlayerService.isPlaying){
      mButton.setImageResource(R.drawable.pause);
    }
    else{
      mButton.setImageResource(R.drawable.play);
    }

    super.onResume();
  }

  @Override
  public void playerAction(Boolean b) {
    final ImageView mButton = (ImageView)rootView.findViewById(R.id.button3);
    if(b){
      mButton.setImageResource(R.drawable.pause);
    }
    else{
      mButton.setImageResource(R.drawable.play);
    }

  }
}
