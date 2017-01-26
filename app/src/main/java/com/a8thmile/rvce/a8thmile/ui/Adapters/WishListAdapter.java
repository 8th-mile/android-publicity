package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;
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
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenter;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenterImpl;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.ui.fragments.WishListFragment;

import java.util.List;

import io.codetail.animation.ViewAnimationUtils;

import static com.a8thmile.rvce.a8thmile.R.id.imageView;

/**
 * Created by vignesh on 19/1/17.
 */

public class WishListAdapter extends ArrayAdapter<EventFields>  implements RegisterView{
    Context context;
    private String token;
    private String id;
    WishListFragment wishListFragment;

    public WishListAdapter(Context context, int resource, List<EventFields> rowItems, String token, String id,WishListFragment wishListFragment) {
        super(context, resource,rowItems);
        this.context=context;
        this.token=token;
        this.id=id;
        this.wishListFragment=wishListFragment;
    }

    @Override
    public void registered(String message) {
        wishListFragment.registered(message);
    }

    @Override
    public void RegisterFailed(String message) {
wishListFragment.RegisterFailed(message);
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {

    }


    public class ViewHolder{
        ImageView image;
        TextView time;
        TextView description;
        TextView price;
        ImageButton imageButton;
        Button registerButton;
        Button loc;

        LinearLayout revealView;
        LinearLayout  layoutButtons;
        Animation alphaAnimation;
        float pixelDensity;
        boolean flag = true;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        final EventFields rowItem = getItem(position);
       final RegisterPresenter registerPresenter=  new RegisterPresenterImpl(this);



        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.wishlist_card, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(imageView);
            holder.time = (TextView)convertView.findViewById(R.id.time);
            holder.price=(TextView)convertView.findViewById(R.id.price);
            holder.description = (TextView)convertView.findViewById(R.id.textView);
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.launchTwitterAnimation);
            holder.registerButton=(Button)convertView.findViewById(R.id.register);
            holder.loc=(Button)convertView.findViewById(R.id.loc);
            holder.revealView = (LinearLayout) convertView.findViewById(R.id.linearView);
            holder.layoutButtons = (LinearLayout) convertView.findViewById(R.id.layoutButtons);
            holder.pixelDensity = context.getResources().getDisplayMetrics().density;
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();
            holder.image.setImageResource(R.drawable.event4);
            holder.time.setText(rowItem.getDate());
            holder.description.setText(rowItem.getName());
            holder.price.setText(Integer.toString(rowItem.getPrice()));

            holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("test","price "+rowItem.getPrice());
                registerPresenter.registerRequest(rowItem.getId(),id,token);
            }
        });

        //since the same adapter is used for events listing and wishlist listing and we do not need wishlist button
        //again in the wishlist events , we check if it null

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
        } else {

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
