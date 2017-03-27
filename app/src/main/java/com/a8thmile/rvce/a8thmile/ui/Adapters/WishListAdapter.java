package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;

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
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;
import com.a8thmile.rvce.a8thmile.ui.Activities.EventActivity;
import com.a8thmile.rvce.a8thmile.ui.ExtraDetails;
import com.a8thmile.rvce.a8thmile.ui.fragments.WishListFragment;

import java.util.HashMap;
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
    ProgressBar spinner;
    private HashMap<Integer,ExtraDetails> extra=new HashMap<Integer, ExtraDetails>()
    {
        {
            put(1,new ExtraDetails("Praveen Kishore","Jai Balaj","9741092282","8861877484",R.drawable.img1));
            put(2,new ExtraDetails("Glen DSouza","Aniruddh S.K","7406974810","8050140803",R.drawable.img2));
            put(3,new ExtraDetails("Srujana S Ramswamy","Vinay Bharadhwaj","9902946010","9663752186",R.drawable.img3));
            put(4,new ExtraDetails("Siddhanth Bajapai","Nishant Srivastava","7676954995","9972381600",R.drawable.img4));
            put(5,new ExtraDetails("Vibhor kala","Anirudh Rao","8867873715","8861572443",R.drawable.img5));
            put(6,new ExtraDetails("TEJASKUMAR C M","JONES ZACHARIAH NOEL N","7353514910","9986714728",R.drawable.img6));
            put(7,new ExtraDetails("Akhil Srinivas","Naveen Dubey","9731357035","9986266906",R.drawable.img7));
            put(8,new ExtraDetails("Prineeth Ramachandra","V T Aniruth","9538687127","9620785437",R.drawable.img8));
            put(9,new ExtraDetails("Vinay S P","Pranav Kulkarni","9620820636","7760891616",R.drawable.img9));
            put(10,new ExtraDetails("B.P Haranandan","M.S Vivek","7406203302","8147401878",R.drawable.img10));
            put(11,new ExtraDetails("HARSHA KULKARNI","ABHISHEK J M","9481333992","9738874630",R.drawable.img11));
            put(68,new ExtraDetails("Vinutha B m","Suhas","7406063074","8123713545",R.drawable.img68));
            put(69,new ExtraDetails("Tanmay Jaiswal","Vindhya N","7259226976","9620267576",R.drawable.img69));
            put(70,new ExtraDetails("Pavan  Rao H B","-","8147391225","",R.drawable.img70));
            put(71,new ExtraDetails("Sharmila Kumble","-","9611834001","",R.drawable.img71));
            put(72,new ExtraDetails("M.S Vamshi","Amrut C","8277778341","9739017594",R.drawable.img72));
            put(73,new ExtraDetails("Basavaraj Matti","Shankar Sriram","8553847010","7022150456",R.drawable.img73));
            put(74,new ExtraDetails("Rajath Bhat","Umakant","9731934729","8088105711",R.drawable.img74));
            put(75,new ExtraDetails("Somashekhar S","Vinay B.Y","7847989017","9060945696",R.drawable.img75));
            put(76,new ExtraDetails("Akanksha G","Sunidhi Singh","7760049678","",R.drawable.img76));
            put(77,new ExtraDetails("Nikhil Paryani","Nishanth Aithal","7847082277","9538305215",R.drawable.img77));
            put(78,new ExtraDetails("Vikas Gautam","Varun Savani","8553643992","8722039076",R.drawable.img78));
            put(79,new ExtraDetails("Ullas Anand","Utkarsh Ghosh","7022150449","8892169713",R.drawable.img79));
            put(80,new ExtraDetails("Kiran Ganiger","Raachit","8971737291","8509233704",R.drawable.img80));
            put(81,new ExtraDetails("Pavan H","-","8050464164","",R.drawable.img81));
            put(82,new ExtraDetails("Anushka ","V Dilip Reddy","8123001816","9632611756",R.drawable.img82));
            put(83,new ExtraDetails("Anantha","Supreet Chandrashekar","8762133684","9739808614",R.drawable.img83));
            put(84,new ExtraDetails("Khadija","Trisha Venkatesh","9742401821","9986693364",R.drawable.img84));
            put(85,new ExtraDetails("Prajwal","Pavan","7411501656","8762114333",R.drawable.img85));
            put(86,new ExtraDetails("kavya","Ramya","9738558462","9008192891",R.drawable.img86));
            put(87,new ExtraDetails("sachin kattimani","shashikanth Ba","8951474921","8884295446",R.drawable.img87));
            put(88,new ExtraDetails("Amrutha ","Archana","9686809301","9481566713",R.drawable.img88));
            put(89,new ExtraDetails("pratima","Aaheli","9480700809","7022140391",R.drawable.img89));
            put(90,new ExtraDetails("simran sifha","sanjana","7353237962","9632522864",R.drawable.img90));
            put(91,new ExtraDetails("Hitaish N","Harshavardhan","9900144268","7411237619",R.drawable.img91));
            put(92,new ExtraDetails("Nishita Hegde","Akanksha Shreya","8095059556","8095817815",R.drawable.img92));
            put(93,new ExtraDetails("Girish patil","-","9844495882","",R.drawable.img93));
            put(94,new ExtraDetails("Shashwath N","Samarth Shendre","9481984277","9008844732",R.drawable.img94));
            put(95,new ExtraDetails("Devyani","Mubaris Khan","9986485300","8139971044",R.drawable.img95));
            put(96,new ExtraDetails("Moushmi J","Srishti Agrawal","9986795159","7899615028",R.drawable.img96));
            put(97,new ExtraDetails("kevin","Abhishek Bose","8880889632","9451910205",R.drawable.img97));
            put(98,new ExtraDetails("Rikku","Niveditha","9739655095","9980142154",R.drawable.img98));
            put(99,new ExtraDetails("Sunaina","Rishab","8861251979","7837600909",R.drawable.img99));
            put(100,new ExtraDetails("Rajath Kaushik","-","8762079988","",R.drawable.img100));
            put(101,new ExtraDetails("Pradeep yadravi","-","8951589789","",R.drawable.img101));
            put(102,new ExtraDetails("Mohan Patil","Praveen MS","","9845510222",R.drawable.img102));
            put(103,new ExtraDetails("Shravit Shetty","Karthik Sulia","9591829155","8762551446",R.drawable.img103));
            put(104,new ExtraDetails("Anoop B","-","9591168937","",R.drawable.img104));
            put(105,new ExtraDetails("Shashank hegde","-","9482480372","",R.drawable.img104));
            put(106,new ExtraDetails(" Akshatha","Nikhil Gowda","9008578899","8904715099",R.drawable.img106));
            put(107,new ExtraDetails("Gajula Chaitra","Pramod","9611916933","8105619142",R.drawable.img107));
            put(108,new ExtraDetails("Durga Pawani"," Varsha V S","9035003030","8867721005",R.drawable.img108));
            put(109,new ExtraDetails(" Deepthi Krishna","Shravya K","9108915216","8792501309",R.drawable.img109));
            put(110,new ExtraDetails("Monica M S"," Shilpa R","8123924979","9611747452",R.drawable.img110));
            put(111,new ExtraDetails("-","-","","",R.drawable.img110));
            put(112,new ExtraDetails(" Moukthika N","Syed Nadeem","9900692277","8904003108",R.drawable.img112));
            put(113,new ExtraDetails("Shruti Bannur"," Dhruthik S","7760095314","9880610844",R.drawable.img113));
            put(114,new ExtraDetails("Yashaswini Bhat","N Ramya","9538501489","9591035553",R.drawable.img114));
            put(115,new ExtraDetails(" Sahana Upadhya"," Monica A","9741772039","9663684490",R.drawable.img115));
            put(116,new ExtraDetails("Anjana Joshi","-","8123179352","",R.drawable.img116));
            put(117,new ExtraDetails("Himanshu Janwadkar","Chandan ","9731391859","9449971632",R.drawable.img117));
            put(118,new ExtraDetails("Karthik SP","Bhargav S","8095800520","9845741055",R.drawable.img118));
            put(119,new ExtraDetails("Dhanush SB","Nisarga","8105105554","9449230225",R.drawable.img119));
            put(120,new ExtraDetails("Ashish padiyar","-","9535767688","",R.drawable.img120));
            put(121,new ExtraDetails("Avinash Gupta","sanskriti khetapal","9738357855","7022151779",R.drawable.img121));
            put(122,new ExtraDetails("Sohani L Gangolli","Monica M S","9845808203","8123924979",R.drawable.img122));
        }
    };


    public WishListAdapter(Context context, int resource, List<EventFields> rowItems, String token,
                           String id, WishListFragment wishListFragment, ProgressBar spinner) {
        super(context, resource,rowItems);
        this.context=context;
        this.token=token;
        this.id=id;
        this.wishListFragment=wishListFragment;
        this.spinner=spinner;
    }

    @Override
    public void registered(String message) {

        spinner.setVisibility(View.GONE);
        wishListFragment.registered(message);
    }

    @Override
    public void RegisterFailed(String message) {

        spinner.setVisibility(View.GONE);
        wishListFragment.RegisterFailed(message);
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {

    }

    @Override
    public void MyEventListGot(MyEventResponse eventResponse) {

    }


    public class ViewHolder{
        ImageView image;
        TextView time;
        TextView description;
        TextView price;
        ImageButton imageButton;
        Button registerButton;
        Button details;

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
            holder.details=(Button)convertView.findViewById(R.id.details);
            holder.revealView = (LinearLayout) convertView.findViewById(R.id.linearView);
            holder.layoutButtons = (LinearLayout) convertView.findViewById(R.id.layoutButtons);
            holder.pixelDensity = context.getResources().getDisplayMetrics().density;
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

            holder.image.setImageResource(extra.get(Integer.parseInt(rowItem.getId())).getImgid());
            if(rowItem.getDate()==null)
              holder.time.setText("-");
            else
              holder.time.setText(rowItem.getDate());
            holder.description.setText(rowItem.getName());
            holder.price.setText(rowItem.getPrice());

            holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spinner.setVisibility(View.VISIBLE);
                //registerPresenter.registerRequest(rowItem.getId(),id,token);
                Intent registerIntent=new Intent(Intent.ACTION_VIEW);
                registerIntent.setData(Uri.parse("https://www.goeventz.com/event/8th-mile-r-v-college-of-engineering/40985"));
                context.startActivity(registerIntent);
            }
        });
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent=new Intent(context, EventActivity.class);
                detailsIntent.putExtra("name",rowItem.getName());
                detailsIntent.putExtra("price",rowItem.getPrice());
                detailsIntent.putExtra("c1",extra.get(Integer.parseInt(rowItem.getId())).getCoord1());
                detailsIntent.putExtra("c2",extra.get(Integer.parseInt(rowItem.getId())).getCoord2());
                detailsIntent.putExtra("cph1",extra.get(Integer.parseInt(rowItem.getId())).getCphone1());
                detailsIntent.putExtra("cph2",extra.get(Integer.parseInt(rowItem.getId())).getCphone2());
                detailsIntent.putExtra("date",rowItem.getDate());
                detailsIntent.putExtra("about",rowItem.getAbout());
                detailsIntent.putExtra("rules",rowItem.getRules());
                detailsIntent.putExtra("first",rowItem.getFirst_prize());
                detailsIntent.putExtra("second",rowItem.getSecond_prize());

                context.startActivity(detailsIntent);
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
