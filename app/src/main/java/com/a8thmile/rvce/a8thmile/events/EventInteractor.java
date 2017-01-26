package com.a8thmile.rvce.a8thmile.events;

import com.a8thmile.rvce.a8thmile.models.EventResponse;

/**
 * Created by vignesh on 23/1/17.
 */

public interface EventInteractor {
    public interface onEventRequestFinishedListener{
        public void onSucess(EventResponse eventResponse);
        public void onFailure(String message);
    }

    public void callEventApi(String token,onEventRequestFinishedListener listener);


}
