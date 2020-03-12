/**
 * Main activity that is launched upon opening the app. Listens to the contact buttons on the home screen, and play + stop buttons
 * @author davidbain
 */

package com.clickgostudio.air1072;

import java.io.InputStream;

import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity1 extends Activity {

	private Button stopButton = null;
	private Button playButton = null;

	//Settings
	Settings settings = new Settings();
	private final String ADURL = settings.getAdBannerURL();
	private final String EMAILADD = settings.getEmailAddress();


	/**
	 * Done upon opening the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Allow hardware audio buttons to control volume
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		clickListeners(); //Start click listeners
		adBanner(); //Start ad banner loading and display

	}


	/**
	 * Sets up menu options
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}


	/**
	 * Listen for menu option selection and carry out event
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.watch_webcam: {
			launchWebcam();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}


	/**
	 * Listens for contact button clicks
	 */
	private void clickListeners(){
		//Play button
		playButton = (Button)findViewById(R.id.PlayButton);
		playButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						RadioMediaPlayerService.class);
				intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
				startService(intent);

			}
		});

		//Stop button
		stopButton = (Button)findViewById(R.id.StopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Get new MediaPlayerService activity
				Intent intent = new Intent(getApplicationContext(),
						RadioMediaPlayerService.class);
				stopService(intent);
			}
		});

		//Email Button click list
		final View EmailPress = (Button)this.findViewById(R.id.emailBtn);
		EmailPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType("message/rfc822");
				emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{EMAILADD});
				//i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
				//i.putExtra(Intent.EXTRA_TEXT   , "body of email");
				try {
					startActivity(Intent.createChooser(emailIntent, "Send email..."));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(MainActivity1.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}

			}
		});

		//Phone Button
		final View PhonePress = (Button)this.findViewById(R.id.phoneBtn);
		PhonePress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				String phoneNum = settings.getPhoneNumber();
				Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNum));
				startActivity (phoneIntent);

			}
		});

		//Website Button
		final View WWWPress = (Button)this.findViewById(R.id.websiteBtn);
		WWWPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getWebsiteURL())); //URL
				startActivity (browserIntent);

			}
		});

		//SMS Button
		final View TxtPress = (Button)this.findViewById(R.id.txtBtn);
		TxtPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +settings.getSmsNumber())); smsIntent.putExtra("sms_body", "AIR ");
				startActivity(smsIntent);

			}
		});
	}


	/**
	 * Get and display advertising banner. Also determine screen size and show / hide ad accordingly
	 */
	private void adBanner(){

		//Hide ad banner if screen size too small
		if ((getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK) ==
				Configuration.SCREENLAYOUT_SIZE_SMALL) {

			LinearLayout adAreaLl = (LinearLayout)findViewById(R.id.adArea);
			adAreaLl.setVisibility(View.GONE);
		}

		//Show on all other screen sizes
		else{

			new DownloadImageTask((ImageView) findViewById(R.id.display_banner))
			.execute(ADURL);

		}
	}


	/**
	 * Launches webcam from external URL
	 */
	public void launchWebcam(){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getRadioWebcamURL()));
		startActivity (browserIntent);
	}


	/**
	 * Lauches send Tweet function
	 */
	public void launchTweet(){
		//TODO
	}


	/**
	 * Launches Play store to rate app
	 */
	public void lauchRating(){
		//TODO
	}


	/**
	 * Load image from external source Asyncrons
	 */
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView adImage;

		public DownloadImageTask(ImageView bmImage) {
			this.adImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap adBmFa = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				adBmFa = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return adBmFa;
		}

		protected void onPostExecute(Bitmap result) {
			adImage.setImageBitmap(result);
		}
	}

  public void clickm(View v){
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}


