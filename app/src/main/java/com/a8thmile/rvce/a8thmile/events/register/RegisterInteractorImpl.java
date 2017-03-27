package com.a8thmile.rvce.a8thmile.events.register;
import android.util.Log;

import com.a8thmile.rvce.a8thmile.api.EventRegisterApi;
import com.a8thmile.rvce.a8thmile.api.EventWishListGet;
import com.a8thmile.rvce.a8thmile.api.EventWishListAdd;
import com.a8thmile.rvce.a8thmile.api.MyEventsGet;
import com.a8thmile.rvce.a8thmile.api.ServiceGenerator;
import com.a8thmile.rvce.a8thmile.models.EventRegister;
import com.a8thmile.rvce.a8thmile.models.EventRegisterResponse;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vignesh on 24/1/17.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    //Register for an event
    @Override
    public void callRegisterApi(String event_id, String user_id, String token, final onRegisterCalledListener listener) {
        EventRegister eventRegister=new EventRegister(Integer.parseInt(event_id),Integer.parseInt(user_id));
        EventRegisterApi eventRegisterApi= ServiceGenerator.createService(EventRegisterApi.class);
        Call<EventRegisterResponse> call= eventRegisterApi.register(eventRegister,token);
        call.enqueue(new Callback<EventRegisterResponse>() {
                         @Override
                         public void onResponse(Call<EventRegisterResponse> call, Response<EventRegisterResponse> response) {
                             if(response.code()==200)
                             {
                                    listener.onSuccess("Registration Successful !!. See you at the Event");
                             }
                             else if(response.code()==409)
                             {
                                 listener.onFailure("You Have Already Registered to this Event.");
                             }
                             else
                             {
                                 listener.onFailure("Server Error.");
                             }
                         }

                         @Override
                         public void onFailure(Call<EventRegisterResponse> call, Throwable t) {
                                    listener.onFailure("Some Problem Occurred. Please Check your Internet Connection");
                         }
                     }


        );
    }

    //Add a event wish
    @Override
    public void callWishListApi(String event_id, String user_id, String token, final onRegisterCalledListener listener) {

        EventRegister eventRegister=new EventRegister(Integer.parseInt(event_id),Integer.parseInt(user_id));
        EventWishListAdd eventWishListAdd = ServiceGenerator.createService(EventWishListAdd.class);
        Call<EventRegisterResponse> call= eventWishListAdd.register(eventRegister,token);
        call.enqueue(new Callback<EventRegisterResponse>() {
            @Override
            public void onResponse(Call<EventRegisterResponse> call, Response<EventRegisterResponse> response) {
                if(response.code()==200)
                {
                    listener.onSuccess("Event Successfully Added to your Wish List");
                }
                else if(response.code()==409)
                {
                    listener.onFailure("The Event is Already in your Wish List.");
                }
                else {
                    listener.onFailure("Server Error :(. Try again later");
                }
            }

            @Override
            public void onFailure(Call<EventRegisterResponse> call, Throwable t) {
                    listener.onFailure("Problem Occurred. Check with Internet connection");
            }
        });
    }


    //get all the wish list events
    @Override
    public void callWishListGetApi(String user_id, String token, final onWishListGotListener listener) {
       // EventWishListGetRequest eventWishListGetRequest=new EventWishListGetRequest(Integer.parseInt(user_id));
        EventWishListGet eventWishListGet = ServiceGenerator.createService(EventWishListGet.class);
        Call<EventResponse> call= eventWishListGet.getWishList(token,Integer.parseInt(user_id));
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.code()==200)
                {
                    Log.v("test","success "+response.body().getResults().size());
                    listener.onSuccess(response.body());

                }
                else
                {
                    listener.onWishListFailure("Server Error :(. Try Again Later");
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                    listener.onWishListFailure("Some Problem Occurred. Check Your Internet Connection");
            }
        });
    }

    @Override
    public void callMyEventListApi(String user_id, String token, final onMyEventListener listener) {
        MyEventsGet eventWishListGet = ServiceGenerator.createService(MyEventsGet.class);
        Call<MyEventResponse> call= eventWishListGet.getMyEvents(token,Integer.parseInt(user_id));
        call.enqueue(new Callback<MyEventResponse>() {
                         @Override
                         public void onResponse(Call<MyEventResponse> call, Response<MyEventResponse> response) {
                             if(response.code()==200)
                             {
                                 listener.onSuccess(response.body());
                             }
                             else
                             {
                                 listener.onMyEventFailure("Server Error");
                             }
                         }

                         @Override
                         public void onFailure(Call<MyEventResponse> call, Throwable t) {
                                listener.onMyEventFailure("Cant Connect to the Server. Check Your Internet Connection");
                         }
                     }
        );

    }
}
