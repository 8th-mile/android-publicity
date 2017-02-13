package com.a8thmile.rvce.a8thmile.events.register;

import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.EventWishListGetResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;

/**
 * Created by vignesh on 24/1/17.
 */

public class RegisterPresenterImpl implements RegisterPresenter,RegisterInteractor.onMyEventListener,RegisterInteractor.onRegisterCalledListener,RegisterInteractor.onWishListGotListener{
    RegisterView registerView;
    RegisterInteractorImpl registerInteractor;
    public RegisterPresenterImpl(RegisterView registerView)
    {
        this.registerView=registerView;
        registerInteractor=new RegisterInteractorImpl();
    }
    @Override
    public void registerRequest(String event_id, String user_id,String token) {
        registerInteractor.callRegisterApi(event_id,user_id,token,this);
    }

    @Override
    public void wishlistRequest(String event_id, String user_id,String token) {
        registerInteractor.callWishListApi(event_id,user_id,token,this);
    }

    @Override
    public void wishListGet(String user_id, String token) {
        registerInteractor.callWishListGetApi(user_id,token,this);
    }

    @Override
    public void myEventsListGet(String user_id, String token) {
        registerInteractor.callMyEventListApi(user_id,token,this);
    }

    @Override
    public void onSuccess(String message) {
        registerView.registered(message);
    }

    @Override
    public void onFailure(String message) {
        registerView.RegisterFailed(message);
    }

    @Override
    public void onSuccess(EventResponse eventResponse) {
        registerView.wishListGot(eventResponse);
    }

    @Override
    public void onSuccess(MyEventResponse eventResponse) {
        registerView.MyEventListGot(eventResponse);
    }

    @Override
    public void onMyEventFailure(String message) {
        registerView.RegisterFailed(message);

    }

    @Override
    public void onWishListFailure(String message) {
            registerView.RegisterFailed(message);
    }
}
