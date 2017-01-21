package com.a8thmile.rvce.a8thmile.ui;

import android.graphics.Color;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.login.LoginPresenter;
import com.a8thmile.rvce.a8thmile.login.LoginPresenterImpl;
import com.a8thmile.rvce.a8thmile.login.LoginView;
import com.dd.CircularProgressButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.a8thmile.rvce.a8thmile.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener,View.OnClickListener,GoogleApiClient.ConnectionCallbacks {

private final String CLIENT_ID="498621765547-49g05468oaldcosvg61llfd29jdjrob7.apps.googleusercontent.com";

    private CircularProgressButton signin;
    private LoginPresenter mLoginPresenter;
    private SignInButton signInButton;
    private  GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin=(CircularProgressButton)findViewById(R.id.signin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(CLIENT_ID)
                .build();
        mGoogleApiClient= new GoogleApiClient.Builder(this)
                .enableAutoManage(MainActivity.this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // findViewById(R.id.sign_in_button).setSize(SignInButton.SIZE_STANDARD);

        signin.setOnClickListener(this);
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
    public void startCircularProgressButton() {
        signin.setIndeterminateProgressMode(true);

    }

    @Override
    public void setCircularProgressStatus(int value) {
        signin.setProgress(value);
    }

    @Override
    public void onValidationFailure() {

        Toast toast= Toast.makeText(this,"Phone number not valid",Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }

    @Override
    public void goToOtpActivity() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayFailureToast() {
        Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
Log.v("test","faileed "+connectionResult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin:
                startCircularProgressButton();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;



        }


    }

    public void handleSignInResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            setCircularProgressStatus(100);
            Intent homeIntent=new Intent(MainActivity.this,HomeActivity.class);
            homeIntent.putExtra("userName",result.getSignInAccount().getIdToken());
            homeIntent.putExtra("userEmail",result.getSignInAccount().getEmail());
            Log.v("test","hey man "+result.getSignInAccount().getIdToken());
            Log.v("test","hey man "+mGoogleApiClient.isConnected());
            startActivity(homeIntent);
        }
        else
        {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("test","done1");

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {

        Log.v("test","done");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {




        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            setCircularProgressStatus(50);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v("test","connected in main");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
