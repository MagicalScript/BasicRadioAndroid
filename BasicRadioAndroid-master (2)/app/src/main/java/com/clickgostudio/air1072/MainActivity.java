package com.clickgostudio.air1072;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.clickgostudio.air1072.fragments.OneFragment;
import com.clickgostudio.air1072.fragments.ThreeFragment;
import com.clickgostudio.air1072.fragments.TwoFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by BegHead on 02/06/2017.
 */

public class MainActivity extends AppCompatActivity{
  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private int[] tabIcons = {
    R.drawable.radiohome,
    R.drawable.contact,
    R.drawable.about
  };
  private AdView mAdView;

  public static InterstitialAd mInterstitialAd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_icon_text_tabs);


    ////////////////////////
    AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbare);
    if (appBarLayout.getLayoutParams() != null) {
      CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
      AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
      appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
        @Override
        public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
          return false;
        }
      });
      layoutParams.setBehavior(appBarLayoutBehaviour);
    }
    //////////////////////////






    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#32cc5a")));

    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);
    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    setupTabIcons();

    mAdView = (AdView) findViewById(R.id.adView);
    //mAdView.setAdSize(AdSize.BANNER);
    //mAdView.setAdUnitId("ca-app-pub-0664570763252260/3326522424");
    AdRequest adRequest = new AdRequest.Builder()
      .build();
    mAdView.loadAd(adRequest);
    showInterstitial(this);
  }



  public static void showInterstitial(final Context context) {
    if(mInterstitialAd == null){
      mInterstitialAd = new InterstitialAd(context);

      // set the ad unit ID
      mInterstitialAd.setAdUnitId(context.getString(R.string.interstitial_full_screen));
    }
    if (mInterstitialAd.isLoaded()) {
      mInterstitialAd.show();
    }
    else{

      AdRequest adRequest = new AdRequest.Builder()
        .build();

      // Load ads into Interstitial Ads
      mInterstitialAd.loadAd(adRequest);

      mInterstitialAd.setAdListener(new AdListener() {
        public void onAdLoaded() {
          showInterstitial(context);
        }
      });
    }
  }
  private void setupTabIcons() {
    tabLayout.getTabAt(0).setIcon(tabIcons[0]);
    tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    tabLayout.getTabAt(2).setIcon(tabIcons[2]);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFrag(new OneFragment(), "Radio Stations");
    adapter.addFrag(new TwoFragment(), "Contact us");
    adapter.addFrag(new ThreeFragment(), "About");
    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.mymenu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  // handle button activities


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.mybutton) {
      // do something here
        Intent shareIntent = new Intent();
      /*
        shareIntent.setType("text/plain");
      //shareIntent.putExtra(Intent.EXTRA_EMAIL  , "emailaddress@emailaddress.com");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I am listening to Islamic Radio Stations (play store URL)");
        startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));

*/


      //Uri imageUri = Uri.parse("android.resource://" + getPackageName()
      //  + "/drawable/" + "icon");
      //Uri imageUri = getImageUri(this,getBitmapFromAsset("icon.png"));
      //Bitmap imgBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.icon);
      //String imgBitmapPath= MediaStore.Images.Media.insertImage(getContentResolver(),imgBitmap,"title",null);
      //Uri imgBitmapUri=Uri.parse(imgBitmapPath);
      //Intent shareIntent = new Intent();
      shareIntent.setAction(Intent.ACTION_SEND);
      shareIntent.putExtra(Intent.EXTRA_TEXT, "I am listening to "+ radioStation.selectedStation.name + " via Radio Islamic https://play.google.com/store/apps/details?id=com.islamicradio.stations");
      //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "I am listening to Radio Islamic (play store URL)");
      //shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
      //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      shareIntent.setType("text/plain");
      startActivity(Intent.createChooser(shareIntent, "send"));
    }
    return super.onOptionsItemSelected(item);

  }
  private Bitmap getBitmapFromAsset(String strName)
  {
    AssetManager assetManager = getAssets();
    InputStream istr = null;
    try {
      istr = assetManager.open(strName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Bitmap bitmap = BitmapFactory.decodeStream(istr);
    return bitmap;
  }
  public Uri getImageUri(Context inContext, Bitmap inImage) {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

    String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
    return Uri.parse(path);
  }

}
