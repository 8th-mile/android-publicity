package com.a8thmile.rvce.a8thmile.ui;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

import java.util.List;

import io.codetail.animation.ViewAnimationUtils;

import static com.a8thmile.rvce.a8thmile.R.id.imageView;

/**
 * Created by vignesh on 19/1/17.
 */

public class LazyAdapter extends ArrayAdapter<RowItem> {
    Context context;

    public LazyAdapter(Context context, int resource, List<RowItem> rowItems) {
        super(context, resource,rowItems);
        this.context=context;
    }
    public class ViewHolder{
        ImageView image;
        TextView time;
        TextView description;
        ImageButton imageButton;
        LinearLayout revealView;
        LinearLayout  layoutButtons;
        Animation alphaAnimation;
        float pixelDensity;
        boolean flag = true;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.event_card, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(imageView);
            holder.time = (TextView)convertView.findViewById(R.id.time);
            holder.description = (TextView)convertView.findViewById(R.id.textView);
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.launchTwitterAnimation);
            holder.revealView = (LinearLayout) convertView.findViewById(R.id.linearView);
            holder.layoutButtons = (LinearLayout) convertView.findViewById(R.id.layoutButtons);
            holder.pixelDensity = context.getResources().getDisplayMetrics().density;
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.image.setImageResource(rowItem.getImageId());
        holder.time.setText(rowItem.getTime());
        holder.description.setText(rowItem.getDescText());



holder.imageButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

        /*
         MARGIN_RIGHT = 16;
         FAB_BUTTON_RADIUS = 28;*/

        int x = holder.image.getRight();
        int y = holder.image.getBottom();
        x -= ((28 * holder.pixelDensity) + (16 * holder.pixelDensity));

        int hypotenuse = (int) Math.hypot(holder.image.getWidth(), holder.image.getHeight());

        if (holder.flag) {

            holder.imageButton.setBackgroundResource(R.drawable.rounded_cancel_button);
            holder.imageButton.setImageResource(R.drawable.ic_event_white_24dp);

            FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                    holder.revealView.getLayoutParams();
            parameters.height = holder.image.getHeight();
            holder.revealView.setLayoutParams(parameters);
            Animator anim = ViewAnimationUtils.createCircularReveal(holder.revealView, x, y, 0, hypotenuse, View.LAYER_TYPE_HARDWARE);
            anim.setDuration(700);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    holder.layoutButtons.setVisibility(View.VISIBLE);

                   //layoutButtons.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            holder.revealView.setVisibility(View.VISIBLE);
            anim.start();

            holder.flag = false;
        } else {

            holder.imageButton.setBackgroundResource(R.drawable.rounded_button);
            holder.imageButton.setImageResource(R.drawable.ic_event_white_24dp);

            Animator anim = ViewAnimationUtils.createCircularReveal(holder.revealView, x, y, hypotenuse, 0);
            anim.setDuration(400);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    holder.revealView.setVisibility(View.GONE);
                    holder.layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            anim.start();
            holder.flag = true;
        }
    }
});

        return convertView;
    }
}
