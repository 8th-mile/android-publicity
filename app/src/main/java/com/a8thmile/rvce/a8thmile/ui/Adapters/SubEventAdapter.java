package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.animation.Animator;
import android.content.Context;

import android.content.Intent;


import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenter;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenterImpl;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.ui.Activities.EventActivity;
import com.a8thmile.rvce.a8thmile.ui.Activities.SubEventActivity;

import java.util.List;

import io.codetail.animation.ViewAnimationUtils;

import static com.a8thmile.rvce.a8thmile.R.id.imageView;

/**
 * Created by vignesh on 19/1/17.
 */

public class SubEventAdapter extends ArrayAdapter<EventFields> {
    Context context;
    private String token;
    private String id;
    private ProgressBar spinner;

    public SubEventAdapter(Context context, int resource, List<EventFields> rowItems, String token, String id, ProgressBar spinner) {
        super(context, resource,rowItems);
        this.context=context;
        this.token=token;
        this.id=id;
        this.spinner=spinner;
    }



    public class ViewHolder{
        ImageView image;
        TextView time;
        TextView description;
        TextView price;
        ImageButton imageButton;
        Button registerButton;
        Button wishlist;
        LinearLayout revealView;
        LinearLayout  layoutButtons;
        Animation alphaAnimation;
        Button details;
        float pixelDensity;
        boolean flag = true;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        final EventFields rowItem = getItem(position);
       final RegisterPresenter registerPresenter=  new RegisterPresenterImpl((SubEventActivity)context);
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "fonts/Exo2-Bold.otf");

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.event_card, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(imageView);
            holder.time = (TextView)convertView.findViewById(R.id.time);
            holder.price=(TextView)convertView.findViewById(R.id.price);
            holder.description = (TextView)convertView.findViewById(R.id.textView);
            holder.description.setTypeface(face);
            holder.price.setTypeface(face);
            holder.time.setTypeface(face);
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.launchTwitterAnimation);
            holder.registerButton=(Button)convertView.findViewById(R.id.register);
            holder.details=(Button)convertView.findViewById(R.id.loc);
            holder.wishlist=(Button)convertView.findViewById(R.id.wishlist);
            holder.details.setTypeface(face);
            holder.registerButton.setTypeface(face);
            holder.wishlist.setTypeface(face);
            holder.revealView = (LinearLayout) convertView.findViewById(R.id.linearView);
            holder.layoutButtons = (LinearLayout) convertView.findViewById(R.id.layoutButtons);
            holder.pixelDensity = context.getResources().getDisplayMetrics().density;
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.image.setImageResource(rowItem.getImgid());
        if(rowItem.getDate()==null)
            rowItem.setDate("Timings : Not Yet Confirmed");
        holder.time.setText(rowItem.getDate());
        if(rowItem.getPrice()==null)
            holder.price.setText("-");
        else
        holder.price.setText(rowItem.getPrice());

        holder.description.setText(rowItem.getName());
        //holder.price.setText(Integer.toString(rowItem.get()));

        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Not sure of this feature as of now so uncomment if needed
            // registerPresenter.registerRequest(rowItem.getId(),id,token);
                Intent registerIntent=new Intent(Intent.ACTION_VIEW);
                registerIntent.setData(Uri.parse("https://www.goeventz.com/event/8th-mile-r-v-college-of-engineering/40985"));
                context.startActivity(registerIntent);
            }
        });



            holder.wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  registerPresenter.wishlistRequest(rowItem.getId(), id, token);
                    spinner.setVisibility(View.VISIBLE);
                }
            });

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent=new Intent(context, EventActivity.class);
                detailsIntent.putExtra("name",rowItem.getName());
                detailsIntent.putExtra("price",rowItem.getPrice());
                detailsIntent.putExtra("first",rowItem.getFirst_prize());
                detailsIntent.putExtra("second",rowItem.getSecond_prize());
                detailsIntent.putExtra("about",rowItem.getAbout());
                detailsIntent.putExtra("rules",rowItem.getRules());
                detailsIntent.putExtra("date",rowItem.getDate());
                detailsIntent.putExtra("c1",rowItem.getCoord1());
                detailsIntent.putExtra("c2",rowItem.getCoord2());
                detailsIntent.putExtra("cph1",rowItem.getCphone1());
                detailsIntent.putExtra("cph2",rowItem.getCphone2());


                context.startActivity(detailsIntent);
            }
        });

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
            holder.imageButton.setImageResource(R.drawable.ic_clear_black_24dp);

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
        }
        else {

            holder.imageButton.setBackgroundResource(R.drawable.rounded_button);
            holder.imageButton.setImageResource(R.drawable.ic_add_white_24dp);

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
