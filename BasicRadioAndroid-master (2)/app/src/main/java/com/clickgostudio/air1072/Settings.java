/**
 * Settings for app including URLS and other features.
 */

package com.clickgostudio.air1072;

public class Settings {
	/********ALL EDITABLE SETTINGS ARE HERE *****/
  //image radio station
  private Integer radioImage = R.drawable.radio_logo;

  //Name of radio station
  private String radioName = "AIR 107.2";

  //URL of the radio stream
  private String radioStreamURL = "http://stream.aironair.co.uk:8002";

  //URL of webcam (or YouTube link maybe)
  private String radioWebcamURL = "http://ustream.com/";

  //URL for the advertising / message banner. For no banner leave blank, i.e: ""
  private String adBannerURL = "http://www.aironair.co.uk/wp-content/uploads/2013/09/App-Banner.png";

  //Contact button email address
  private String emailAddress = "zemraniyassine2@gmail.com";

  //Contact button phone number
  private String phoneNumber = "01305836040";

  //Contact button website URL
  private String websiteURL = "http://aironair.co.uk";

  //Contact button SMS number
  private int smsNumber = 66777;

  //Message to be shown in notification center whilst playing
  private String mainNotificationMessage = "You're listening to " + radioName;

  //TOAST notification when play button is pressed
  private String playNotificationMessage = "Starting " + radioName;

  //Play store URL (not known until published
  private String playStoreURL = "http://play.google.com/";

  //Enable console output for streaming info (Buffering etc) - Disable = false
  private boolean allowConsole = true;

	/********END OF EDITABLE SETTINGS**********/


  public Integer getRadioImage() {
    return radioImage;
  }

  /********DO NOT EDIT BELOW THIS LINE*******/


	public String getRadioName(){
		return radioName;
	}

	public String getRadioStreamURL(){
		return radioStreamURL;
	}

	public String getRadioWebcamURL(){
		return radioWebcamURL;
	}

	public String getAdBannerURL(){
		return adBannerURL;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public String getPhoneNumber(){
		String appendPhoneNumber = "tel:"+phoneNumber;
		return appendPhoneNumber;
	}

	public String getWebsiteURL(){
		return websiteURL;
	}

	public int getSmsNumber(){
		return smsNumber;
	}

	public String getMainNotificationMessage(){
		return mainNotificationMessage;
	}

	public String getPlayNotificationMessage(){
		return playNotificationMessage;
	}

	public String getPlayStoreURL(){
		return playStoreURL;
	}

	public boolean getAllowConsole(){
		return allowConsole;
	}

  public Settings (){
    if(radioStation.selectedStation == null)
      radioStation.selectedStation = radioStation.stations[0];

    /********ALL EDITABLE SETTINGS ARE HERE *****/
    //image radio station
    radioImage = radioStation.selectedStation.imageName;

    //Name of radio station
    radioName = radioStation.selectedStation.name;

    //URL of the radio stream"http://www.quran-radio.org:8002/
    radioStreamURL = radioStation.selectedStation.url;

    //URL of webcam (or YouTube link maybe)
    radioWebcamURL = "http://ustream.com/";

    //URL for the advertising / message banner. For no banner leave blank, i.e: ""
    adBannerURL = "http://www.aironair.co.uk/wp-content/uploads/2013/09/App-Banner.png";

    //Contact button email address
    emailAddress = "zemraniyassine2@gmail.com";

    //Contact button phone number
    phoneNumber = "01305836040";

    //Contact button website URL
    websiteURL = radioStation.selectedStation.website;

    //Contact button SMS number
    smsNumber = 66777;

    //Message to be shown in notification center whilst playing
    mainNotificationMessage = "You're listening to AIR";

    //TOAST notification when play button is pressed
    playNotificationMessage = "Starting " + radioStation.selectedStation.name;

    //Play store URL (not known until published
    playStoreURL = "http://play.google.com/";

    //Enable console output for streaming info (Buffering etc) - Disable = false
    allowConsole = true;

    /********END OF EDITABLE SETTINGS**********/
  }
}



