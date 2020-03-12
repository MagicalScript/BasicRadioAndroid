package com.clickgostudio.air1072;

/**
 * Created by BegHead on 31/05/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {

  private final Context mContext;
  private final radioStation[] RS;

  // 1
  public BooksAdapter(Context context, radioStation[] _RS) {
    this.mContext = context;
    this.RS = _RS;
  }

  // 2
  @Override
  public int getCount() {
    return RS.length;
  }

  // 3
  @Override
  public long getItemId(int position) {
    return 0;
  }

  // 4
  @Override
  public Object getItem(int position) {
    return null;
  }

  // 5
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // 1
    final radioStation _rs = RS[position];

    // 2
    if (convertView == null) {
      final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
      convertView = layoutInflater.inflate(R.layout.radiostation, null);
    }

    // 3
    final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_favorite);
    final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
    final TextView detailTextView = (TextView)convertView.findViewById(R.id.textView2);
    final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

    // 4
    imageView.setImageResource(_rs.imageName);
    nameTextView.setText(_rs.name);
    detailTextView.setText(_rs.detail);

    return convertView;
  }

}
