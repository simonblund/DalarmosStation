package com.example.simonblomsterlund.dalstation;

import android.content.Context;
import android.widget.Toast;

import com.example.simonblomsterlund.dalstation.model.Incident;
import com.example.simonblomsterlund.dalstation.service.createIncident;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simonblomsterlund on 29/06/2017.
 */

public class incidentMakerUtil{
    String[] messageArr;
    String message = "";
    String address = "";
    String type = "";
    String area = "";
    String details = "";
    String time = "";
    Incident incident;

    public void parametrise(String messag, Context context) {

        messageArr = messag.split("\\s*,\\s*");
        message = messag;
        type = messageArr[0];
        address = messageArr[3];
        area = messageArr[6];
        details = messageArr[2] + " " + messageArr[3];
        time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        Incident incident = new Incident(
                message,
                address,
                type,
                area,
                details,
                time
        );


        Toast.makeText(context, "incident created" + new SimpleDateFormat("MM.dd.HH.mm").format(new Date()), Toast.LENGTH_SHORT).show();
        sendNetworkRequest(incident, context);
    }
    private void sendNetworkRequest(Incident incident, Context context){
        final Context innerContext = context;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://dalarmos.io/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        createIncident newIncident = retrofit.create(createIncident.class);
        Call<Incident> call = newIncident.insertIncidentInfo(incident);

        call.enqueue(new Callback<Incident>() {
            @Override
            public void onResponse(Call<Incident> call, Response<Incident> response) {

            }

            @Override
            public void onFailure(Call<Incident> call, Throwable t) {
                Toast.makeText(innerContext, "Incident failed" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
