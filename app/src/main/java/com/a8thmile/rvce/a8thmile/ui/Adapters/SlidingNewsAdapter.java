package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

import java.util.ArrayList;

/**
 * Created by vignesh on 29/1/17.
 */

public class SlidingNewsAdapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private ArrayList<String> TEXTS;
    private LayoutInflater inflater;
    private Context context;


    public SlidingNewsAdapter(Context context,ArrayList<Integer> IMAGES,ArrayList<String> TEXTS) {
        this.context = context;
        this.IMAGES=IMAGES;
        this.TEXTS=TEXTS;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.sliding_news, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        TextView textView=(TextView)imageLayout.findViewById(R.id.newsText);


        imageView.setImageResource(IMAGES.get(position));
        textView.setText(TEXTS.get(position));
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
