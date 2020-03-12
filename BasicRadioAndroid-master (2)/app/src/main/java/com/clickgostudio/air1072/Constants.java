package com.clickgostudio.air1072;

/**
 * Created by BegHead on 01/06/2017.
 */

public class Constants {


  public interface ACTION {
    public static String MAIN_ACTION = "com.marothiatechs.foregroundservice.action.main";
    public static String INIT_ACTION = "com.marothiatechs.foregroundservice.action.init";
    public static String CLOSE_ACTION = "com.marothiatechs.foregroundservice.action.close";
    public static String PLAY_ACTION = "com.marothiatechs.foregroundservice.action.play";
    public static String PAUSE_ACTION = "com.marothiatechs.foregroundservice.action.pause";
    public static String STARTFOREGROUND_ACTION = "com.marothiatechs.foregroundservice.action.startforeground";
    public static String STOPFOREGROUND_ACTION = "com.marothiatechs.foregroundservice.action.stopforeground";
  }

  public interface NOTIFICATION_ID {
    public static int FOREGROUND_SERVICE = 101;
  }
}
