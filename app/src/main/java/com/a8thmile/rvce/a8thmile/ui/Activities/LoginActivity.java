package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.login.LoginPresenter;
import com.a8thmile.rvce.a8thmile.login.LoginPresenterImpl;
import com.a8thmile.rvce.a8thmile.login.LoginView;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;
import com.dd.CircularProgressButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.a8thmile.rvce.a8thmile.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;


public class LoginActivity extends AppCompatActivity implements LoginView,View.OnClickListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private final String CLIENT_ID="498621765547-49g05468oaldcosvg61llfd29jdjrob7.apps.googleusercontent.com";
    private int RC_SIGN_IN = 100;

    private CircularProgressButton signin;

    //mvp presenter used for sending requests
    private LoginPresenter mLoginPresenter;

    //For Google Api Requests
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;


    //used store the data needed for sending further requests to the server. These are obtained by sending the login request
    // with the google api token . the server sends another permanent token unique to a user for using as headers
    private String token;
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginPresenter = new LoginPresenterImpl(this);
        signin=(CircularProgressButton)findViewById(R.id.signin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //initialize the google signin objects
        setGoogleApi();

        signin.setOnClickListener(this);

    }

    public void setGoogleApi() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(CLIENT_ID)
                .build();
        mGoogleApiClient= new GoogleApiClient.Builder(getBaseContext())
                .enableAutoManage(LoginActivity.this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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
    public void goToHomeActivity(LoginResponse mLoginResponse /*String email,String name*/) {
        Intent homeIntent = new Intent(this,HomeActivity.class);
        homeIntent.putExtra("userName",name);
        homeIntent.putExtra("id",mLoginResponse.getId());
        homeIntent.putExtra("email",email);
        homeIntent.putExtra("token",mLoginResponse.getToken());
        startActivity(homeIntent);
    }

    @Override
    public void displayFailureToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        //connection wont be obtained if google play service is not updated
        Toast.makeText(getBaseContext(),"Update Google Play Services and try again",Toast.LENGTH_LONG).show();
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
            setCircularProgressStatus(50);
            token=result.getSignInAccount().getIdToken();
            email=result.getSignInAccount().getEmail();
            name=result.getSignInAccount().getDisplayName();

            //send the token with email to the server and obtain the user token
            mLoginPresenter.tokenLogin(email,token);

            //goToHomeActivity(result.getSignInAccount().getDisplayName(),result.getSignInAccount().getEmail());

        }
        else
        {
            setCircularProgressStatus(0);
           Toast.makeText(this,"Failed to Sign in.Check Your Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if the user has signin before and has closed the app, restart him to the home activity
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (opr.isDone()) {
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

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}