package com.a8thmile.rvce.a8thmile.events.register;
import android.util.Log;

import com.a8thmile.rvce.a8thmile.api.EventRegisterApi;
import com.a8thmile.rvce.a8thmile.api.EventWishGet;
import com.a8thmile.rvce.a8thmile.api.EventWishList;
import com.a8thmile.rvce.a8thmile.api.ServiceGenerator;
import com.a8thmile.rvce.a8thmile.models.EventRegister;
import com.a8thmile.rvce.a8thmile.models.EventRegisterResponse;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.EventWishListGetRequest;
import com.a8thmile.rvce.a8thmile.models.EventWishListGetResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vignesh on 24/1/17.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public void callRegisterApi(String event_id, String user_id, String token, final onRegisterCalledListener listener) {
        Log.v("test","event id "+event_id+" user_id "+user_id+" token "+token);
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

    @Override
    public void callWishListApi(String event_id, String user_id, String token, final onRegisterCalledListener listener) {
        EventRegister eventRegister=new EventRegister(Integer.parseInt(event_id),Integer.parseInt(user_id));
        EventWishList eventWishList= ServiceGenerator.createService(EventWishList.class);
        Call<EventRegisterResponse> call= eventWishList.register(eventRegister,token);
        call.enqueue(new Callback<EventRegisterResponse>() {
            @Override
            public void onResponse(Call<EventRegisterResponse> call, Response<EventRegisterResponse> response) {
                if(response.code()==200)
                {
                    listener.onSuccess("Event Successfully Added to your Wish List");
                }
                else {
                    listener.onFailure("Server Error :(. Try again after sometime");
                }
            }

            @Override
            public void onFailure(Call<EventRegisterResponse> call, Throwable t) {
                    listener.onFailure("Problem Occurred. Check with Internet connection");
            }
        });
    }

    @Override
    public void callWishListGetApi(String user_id, String token, final onWishListGotListener listener) {
       // EventWishListGetRequest eventWishListGetRequest=new EventWishListGetRequest(Integer.parseInt(user_id));
        EventWishGet eventWishGet = ServiceGenerator.createService(EventWishGet.class);
        Call<EventResponse> call=eventWishGet.getWishList(token,Integer.parseInt(user_id));
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.code()==200)
                {
                    listener.onSuccess(response.body());
                }
                else
                {
                    listener.onWishListFailure("Server Error!");
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                    listener.onWishListFailure("Some Problem Occurred. Check Your Internet Connection");
            }
        });
    }
}
