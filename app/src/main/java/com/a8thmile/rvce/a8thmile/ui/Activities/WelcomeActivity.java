package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.a8thmile.rvce.a8thmile.R;


import pl.droidsonroids.gif.GifImageView;
import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.ChangeColor;
import su.levenetc.android.textsurface.animations.Circle;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

public class WelcomeActivity extends Activity {
    SharedPreferences pref;
    Button dontShow,skip;
    private ImageView logo;
    private boolean done;
    public MediaPlayer mPlayer;

    private TextSurface textSurface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/Exo2-Bold.otf");
        done=false;
        mPlayer = MediaPlayer.create(WelcomeActivity.this, R.raw.edit3);
        mPlayer.start();
        //BackgroundSound mBackgroundSound = new BackgroundSound();
        //mBackgroundSound.execute();

        pref= getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            finish();
            done=true;
            mPlayer.stop();
        }
        else{
            animation();
        }

        dontShow=(Button)findViewById(R.id.dontshow);
        dontShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("activity_executed", true);
                ed.commit();
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                done=true;
                mPlayer.stop();
            }
        });
        dontShow.setTypeface(face);
        skip=(Button)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skipIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(skipIntent);
                finish();
                done=true;
                mPlayer.stop();
            }
        });
        skip.setTypeface(face);



        //logo=(ImageView) findViewById(R.id.logo);


        //final GifDrawable gifFromResource = new GifDrawable(res ,R.drawable.logo_orig);

        final GifImageView logo = (GifImageView) findViewById(R.id.logo);

        //Glide.with(getBaseContext()).load(R.drawable.logo_orig).asGif().crossFade().into(logo);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //mPlayer.pause();
                //Glide.with(getBaseContext()).load(R.drawable.logo_orig).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(logo);
                //Intent skipIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                //startActivity(skipIntent);
                //logo.setImageDrawable(gifFromResource);
                //mPlayer.start();
                logo.setImageResource(R.drawable.logo_orig);
                logo.setScaleX((float)1.2);
                logo.setScaleY((float)1.2);
            }
        }, 16200);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Glide.with(getBaseContext()).load(R.drawable.logo_orig).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(logo);
                if(done==false) {
                    Intent skipIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(skipIntent);
                    mPlayer.stop();
                }
                finish();
            }
        }, 23500);

    }


    public void animation(){

        textSurface = (TextSurface) findViewById(R.id.text_surface);


        AssetManager assetManager = getAssets();
        final Typeface robotoBlack = Typeface.createFromAsset(assetManager, "fonts/Exo2-Bold.otf");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(robotoBlack);

        Text textDot = TextBuilder
                .create(".")
                .setSize(128)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.SURFACE_CENTER).build();


        Text textDaai = TextBuilder
                .create("Hey!")
                .setPaint(paint)
                .setSize(64)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.SURFACE_CENTER).build();

        Text textBraAnies = TextBuilder
                .create("8th Mile")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#8E44AD")) //purple
                .setPosition(Align.BOTTOM_OF, textDaai).build();

        Text textFokkenGamBra = TextBuilder
                .create(" is the annual ")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#27AE60")) //green
                .setPosition(Align.RIGHT_OF, textBraAnies).build();
        Text textFokkenGamBra2 = TextBuilder
                .create(" fest of")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#3498DB")) //blue
                .setPosition(Align.RIGHT_OF, textBraAnies).build();

        Text textHaai = TextBuilder
                .create("RVCE")
                .setPaint(paint)
                .setSize(74)
                .setAlpha(0)
                .setColor(Color.parseColor("#16A085"))
                .setPosition(Align.BOTTOM_OF, textFokkenGamBra).build();

        Text textDaaiAnies = TextBuilder
                .create("We Hope")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textHaai).build();

        Text texThyLamInnie = TextBuilder
                .create(" you have fun.")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.RIGHT_OF, textDaaiAnies).build();

        Text textThrowDamn = TextBuilder
                .create("Welcome")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#E74C3C")) //
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, texThyLamInnie).build();

        Text textDevilishGang = TextBuilder
                .create("to")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#E74C3C"))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textThrowDamn).build();

        Text textSignsInTheAir = TextBuilder
                .create("8th Mile!")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.parseColor("#E74C3C"))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textDevilishGang).build();

        textSurface.play(
                new Sequential(
                        Slide.showFrom(Side.TOP, textDot, 400),

                        Delay.duration(300),
                        Alpha.hide(textDot, 200),
                        Alpha.show(textDot, 300),
                        Alpha.hide(textDot, 200),
                        Alpha.show(textDot, 300),
                        Alpha.hide(textDot, 200),
                        Alpha.show(textDot, 300),
                        Alpha.hide(textDot, 300),

                        ShapeReveal.create(textDaai, 1000, SideCut.show(Side.LEFT), false),
                        new Parallel(ShapeReveal.create(textDaai, 800, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(500), ShapeReveal.create(textDaai, 800, SideCut.show(Side.LEFT), false))),
                        new Parallel(new TransSurface(700, textBraAnies, Pivot.CENTER), ShapeReveal.create(textBraAnies, 1600, SideCut.show(Side.LEFT), false)),
                        Delay.duration(700),
                        new Parallel(new TransSurface(1000, textFokkenGamBra, Pivot.CENTER), Slide.showFrom(Side.LEFT, textFokkenGamBra, 1000), ChangeColor.to(textFokkenGamBra, 1000, Color.WHITE)),
                        Delay.duration(500),
                        Alpha.hide(textFokkenGamBra, 100),
                        new Parallel(new TransSurface(1000, textFokkenGamBra2, Pivot.CENTER), Slide.showFrom(Side.LEFT, textFokkenGamBra2, 1000), ChangeColor.to(textFokkenGamBra2, 1000, Color.WHITE)),
                        Delay.duration(700),
                        new Parallel(TransSurface.toCenter(textHaai, 700), Rotate3D.showFromSide(textHaai, 1000, Pivot.TOP)),
                        new Parallel(TransSurface.toCenter(textDaaiAnies, 700), Slide.showFrom(Side.TOP, textDaaiAnies, 700)),
                        new Parallel(TransSurface.toCenter(texThyLamInnie, 1000), Slide.showFrom(Side.LEFT, texThyLamInnie, 700)),
                        Delay.duration(700),

                        new Parallel(
                                new TransSurface(1500, textSignsInTheAir, Pivot.CENTER),
                                new Sequential(
                                        new Sequential(ShapeReveal.create(textThrowDamn, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(textDevilishGang, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(textSignsInTheAir, 500, Circle.show(Side.CENTER, Direction.OUT), false))
                                ),
                                Alpha.hide(textHaai, 1500),
                                Alpha.hide(textFokkenGamBra, 1500),
                                Alpha.hide(textFokkenGamBra2, 1500),
                                Alpha.hide(texThyLamInnie, 1500),
                                Alpha.hide(textDaaiAnies, 1500)
                        ),
                        Delay.duration(200),
                        new Parallel(
                                ShapeReveal.create(textThrowDamn, 1500, SideCut.hide(Side.LEFT), true),
                                new Sequential(Delay.duration(250), ShapeReveal.create(textDevilishGang, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(500), ShapeReveal.create(textSignsInTheAir, 1500, SideCut.hide(Side.LEFT), true))

                        )

                )

        );
    }
    @Override
    public void onPause() {
        super.onPause();
        mPlayer.setVolume(0,0);

    }
    @Override
    public void onResume() {
        super.onResume();
        mPlayer.setVolume(1,1);

    }
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            final MediaPlayer player = MediaPlayer.create(WelcomeActivity.this, R.raw.edit3);
            player.setLooping(false); // Set looping
            player.setVolume(100,100);
            player.start();

            return null;
        }

    }
}