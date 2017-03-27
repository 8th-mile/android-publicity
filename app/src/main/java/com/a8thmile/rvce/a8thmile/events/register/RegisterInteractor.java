package com.a8thmile.rvce.a8thmile.events.register;

import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;

/**
 * Created by vignesh on 24/1/17.
 */

public interface RegisterInteractor {
    public void callRegisterApi(String event_id,String user_id,String token,onRegisterCalledListener listener);
    public void callWishListApi(String event_id,String user_id,String token,onRegisterCalledListener listener);
    public void callWishListGetApi(String  user_id,String token,onWishListGotListener listener);
    public void callMyEventListApi(String  user_id,String token,onMyEventListener listener);
    public interface onRegisterCalledListener
    {
        public void onSuccess(String message);
        public void onFailure(String message);
    }
    public interface onWishListGotListener
    {
        public void onSuccess(EventResponse eventResponse);
        public void onWishListFailure(String message);
    }
    public interface onMyEventListener
    {
        public void onSuccess(MyEventResponse eventResponse);
        public void onMyEventFailure(String message);
    }
}
