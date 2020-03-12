package com.clickgostudio.air1072;

/**
 * Created by BegHead on 31/05/2017.
 */

public class radioStation {

  public static radioStation selectedStation;
  public static radioStation[] stations = {
    new radioStation("http://quraan.us:9852/","Ahmad Al-Ajmy","Quran Recitation by Sheikh Ahmad alAjmi",R.drawable.aabad),
    new radioStation("http://www.quran-radio.org:8002/","my station 2","my station",R.drawable.ajmy),
    new radioStation("http://s10.voscast.com:9276/","my station 3","my station",R.drawable.ghamdi),
    new radioStation("http://quraan.us:9852/","Ahmad Al-Ajmy","Quran Recitation by Sheikh Ahmad alAjmi",R.drawable.aabad),
    new radioStation("http://www.quran-radio.org:8002/","my station 2","my station",R.drawable.ajmy),
    new radioStation("http://s10.voscast.com:9276/","my station 3","my station",R.drawable.ghamdi),
    new radioStation("http://quraan.us:9852/","Ahmad Al-Ajmy","Quran Recitation by Sheikh Ahmad alAjmi",R.drawable.aabad),
    new radioStation("http://www.quran-radio.org:8002/","my station 2","my station",R.drawable.ajmy),
    new radioStation("http://s10.voscast.com:9276/","my station 3","my station",R.drawable.ghamdi),
    new radioStation("http://quraan.us:9852/","Ahmad Al-Ajmy","Quran Recitation by Sheikh Ahmad alAjmi",R.drawable.aabad),
    new radioStation("http://www.quran-radio.org:8002/","my station 2","my station",R.drawable.ajmy),
    new radioStation("http://s10.voscast.com:9276/","my station 3","my station",R.drawable.ghamdi),
    new radioStation("http://quraan.us:9852/","Ahmad Al-Ajmy","Quran Recitation by Sheikh Ahmad alAjmi",R.drawable.aabad),
    new radioStation("http://www.quran-radio.org:8002/","my station 2","my station",R.drawable.ajmy),
    new radioStation("http://s10.voscast.com:9276/","my station 3","my station",R.drawable.ghamdi)
  };





  public radioStation(String _url,String _name,String _detail,Integer _Image){
    //some code here
    url = _url;
    name = _name;
    detail = _detail;
    imageName = _Image;
  }
  public radioStation(String _url,String _name,Integer _Image,String _website){
    //some code here
    url = _url;
    name = _name;
    imageName = _Image;
    website = _website;
  }

  public String url = "";
  public String name = "IslamiqueRadioStation";
  public String detail = "IslamiqueRadioStation";
  public Integer imageName = R.drawable.radio_logo;
  public String website = "www.RadioFM.com";

}
