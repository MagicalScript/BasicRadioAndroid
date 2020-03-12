package com.clickgostudio.air1072.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clickgostudio.air1072.MainActivity1;
import com.clickgostudio.air1072.R;
import com.clickgostudio.air1072.Settings;

//import com.clickgostudio.air1072.R;


public class TwoFragment extends Fragment{
  Settings settings = new Settings();
  private final String EMAILADD = settings.getEmailAddress();
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
      Button btn = (Button)v.findViewById(R.id.button4);
      final EditText et = (EditText)v.findViewById(R.id.editText6);
      final EditText et5 = (EditText)v.findViewById(R.id.editText5);
      final EditText et7 = (EditText)v.findViewById(R.id.editText7);

      btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
          emailIntent.setType("message/rfc822");
          emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL  , new String[]{EMAILADD});
          emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (et).getText());
          emailIntent.putExtra(android.content.Intent.EXTRA_TEXT   , (et5).getText()+".\n"+(et7).getText());
          try {
            startActivity(android.content.Intent.createChooser(emailIntent, "Send email..."));
          } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
          }
        }
      });
      return v;
    }

}
