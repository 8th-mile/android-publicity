package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.login.LoginPresenter;
import com.a8thmile.rvce.a8thmile.login.LoginPresenterImpl;
import com.a8thmile.rvce.a8thmile.login.LoginView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.a8thmile.rvce.a8thmile.models.LoginResponse;



import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.a8thmile.rvce.a8thmile.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static uk.co.chrisjenx.calligraphy.R.attr.background;


public class LoginActivity extends AppCompatActivity implements LoginView,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
//498621765547-49g05468oaldcosvg61llfd29jdjrob7.apps.googleusercontent.com
    private final String CLIENT_ID="498621765547-1nkrk6s2fqd5u8ijn4gse59jt19uhutf.apps.googleusercontent.com";//"498621765547-2hk8782i5ntcog2727isd87gd6iuslb5.apps.googleusercontent.com";

    private int RC_SIGN_IN = 100;

    //mvp presenter used for sending requests
    private LoginPresenter mLoginPresenter;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    public ImageView googLogo;
    int flag=0;

    //used store the data needed for sending further requests to the server. These are obtained by sending the login request
    // with the google api token . the server sends another permanent token unique to a user for using as headers
    private String token;
    private String name;
    private String email;

    String currentVersion, latestVersion;
    Dialog dialog;
    private void getCurrentVersion(){

        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo =  pm.getPackageInfo(this.getPackageName(),0);

        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        currentVersion = pInfo.versionName;

        new GetLatestVersion().execute();

    }

    private class GetLatestVersion extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
//It retrieves the latest version by scraping the content of current version from play store at runtime

                org.jsoup.nodes.Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=com.a8thmile.rvce.a8thmile").get();
                latestVersion = doc.getElementsByAttributeValue
                        ("itemprop","softwareVersion").first().text();

            }catch (Exception e){
                e.printStackTrace();

            }

            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            flag=1;

            if(latestVersion!=null) {
                if (!currentVersion.equalsIgnoreCase(latestVersion)){
                    if(!isFinishing()){ //This would help to prevent Error : BinderProxy@45d459c0 is not valid; is your activity running? error
                        showUpdateDialog();
                    }
                }
            }
            else


                //background.start();
            super.onPostExecute(jsonObject);
        }
    }

    private void showUpdateDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Update the App ");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("market://details?id=com.a8thmile.rvce.a8thmile")));
                dialog.dismiss();
            }
        });


        builder.setCancelable(false);
        dialog = builder.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentVersion();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Exo2-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        googLogo = (ImageView) findViewById(R.id.g_logo);
        Glide.with(getBaseContext()).load(R.drawable.g_logo_still).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(googLogo);

        mLoginPresenter = new LoginPresenterImpl(this);


        //initialize the google signin objects
        setGoogleApi();
        googLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1) {

                    Glide.with(getBaseContext()).load(R.drawable.g_anim).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(googLogo);
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    //Intent homeIntent = new Intent(getBaseContext(),HomeActivity.class);
                    //startActivity(homeIntent);

                }
                else
                    Toast.makeText(getBaseContext(),"Checking for a newer version",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void setGoogleApi() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(CLIENT_ID)
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
                .enableAutoManage(LoginActivity.this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mLoginPresenter = new LoginPresenterImpl(this);
    }


    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });


    }




    @Override
    public void goToHomeActivity(LoginResponse mLoginResponse /*String email,String name*/) {
        Intent homeIntent = new Intent(this,HomeActivity.class);
        homeIntent.putExtra("userName",name);
        homeIntent.putExtra("id",mLoginResponse.getId());
        homeIntent.putExtra("email",email);
        homeIntent.putExtra("token",mLoginResponse.getToken());
        startActivity(homeIntent);
        Glide.with(getBaseContext()).load(R.drawable.g_logo_still).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(googLogo);
    }

    @Override
    public void displayFailureToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        Glide.with(getBaseContext()).load(R.drawable.g_logo_still).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(googLogo);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        //connection wont be obtained if google play service is not updated
        Toast.makeText(getBaseContext(),"Update Google Play Services and try again",Toast.LENGTH_LONG).show();
        Glide.with(getBaseContext()).load(R.drawable.g_logo_still).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(googLogo);
    }



    public void handleSignInResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {

            token=result.getSignInAccount().getIdToken();
            email=result.getSignInAccount().getEmail();
            name=result.getSignInAccount().getDisplayName();
            Log.v("test",token);
            //send the token with email to the server and obtain the user token
            mLoginPresenter.tokenLogin(email,token);

            //goToHomeActivity(result.getSignInAccount().getDisplayName(),result.getSignInAccount().getEmail());

        }
        else
        {

            Toast.makeText(this,"Failed to Sign in.Check Your Internet Connection",Toast.LENGTH_LONG).show();
            Glide.with(getBaseContext()).load(R.drawable.g_logo_still).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(googLogo);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        //if the user has signin before and has closed the app, restart him to the home activity
        if(flag==1) {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

            if (opr.isDone()) {
                GoogleSignInResult result = opr.get();
                handleSignInResult(result);
            } else {

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}