package com.a8thmile.rvce.a8thmile.events.register;

import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;

/**
 * Created by vignesh on 24/1/17.
 */

public interface RegisterView {
    public void registered(String message);
    public void RegisterFailed(String message);
    public void wishListGot(EventResponse eventResponse);
    public void MyEventListGot(MyEventResponse eventResponse);
}
