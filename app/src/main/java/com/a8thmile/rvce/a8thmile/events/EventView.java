package com.a8thmile.rvce.a8thmile.events;

import com.a8thmile.rvce.a8thmile.models.EventResponse;

/**
 * Created by vignesh on 23/1/17.
 */

public interface EventView {
   public void showFailureMessage(String message);
    public void loadData(EventResponse eventResponse);

}
