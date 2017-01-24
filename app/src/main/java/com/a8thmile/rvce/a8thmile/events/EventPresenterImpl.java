package com.a8thmile.rvce.a8thmile.events;

import com.a8thmile.rvce.a8thmile.models.EventResponse;

/**
 * Created by vignesh on 23/1/17.
 */

public class EventPresenterImpl implements EventPresenter,EventInteractor.onEventRequestFinishedListener {
    EventInteractorImpl eventInteractor;
    EventView eventView;

    public EventPresenterImpl(EventView eventView){
        this.eventView=eventView;
        eventInteractor=new EventInteractorImpl();

    }

    @Override
    public void eventRequest(String token) {
        eventInteractor.callEventApi(token,this);
    }



    @Override
    public void onSucess(EventResponse eventResponse) {
        eventView.loadData(eventResponse);

    }

    @Override
    public void onFailure(String message) {
        eventView.showFailureMessage(message);
    }
}
