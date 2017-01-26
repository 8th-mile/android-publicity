package com.a8thmile.rvce.a8thmile.events.register;

/**
 * Created by vignesh on 24/1/17.
 */

public interface RegisterPresenter{
public void registerRequest(String event_id,String user_id,String token);
    public void wishlistRequest(String event_id,String user_id,String token);

    public void wishListGet(String user_id,String token);

}
