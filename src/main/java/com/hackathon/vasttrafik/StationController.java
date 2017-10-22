package com.hackathon.vasttrafik;

import com.google.gson.Gson;
import com.hackathon.vasttrafik.models.Departure;
import com.hackathon.vasttrafik.models.Station;
import com.hackathon.vasttrafik.models.Token;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class StationController {

    private static final String STATION_ID= "9021014006242000";
    private static final String BASE_URL_DEPARTURE = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard";

    @Autowired
    private TokenService tokenService;

    Gson gson = new Gson();

    @RequestMapping(value="/station", method= RequestMethod.GET)
    public List<Departure> getNextDepartures() throws Exception {


        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeString = timeFormat.format(date);
        String dateString = dateFormat.format(date);

        String url = BASE_URL_DEPARTURE + "?id="+ STATION_ID +"&date="+dateString + "&time=" + timeString +"&format=json";

        Token token = tokenService.getToken();
        System.out.println("Bearer " + token.getAccessToken());

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        ResponseHandler<String> handler = new BasicResponseHandler();

        request.addHeader("Accept", "*/*");
        request.addHeader("Authorization", "Bearer " + token.getAccessToken());
        HttpResponse response = client.execute(request);

        String result = handler.handleResponse(response);

        Station station = gson.fromJson(result, Station.class);

        List<Departure> allDepartures = station.getDepartureBoard().getDeparture();

        /*
        List<Departure> nextDepartures = null;

        if (allDepartures.size() >= 10){
            nextDepartures = allDepartures.subList(0, 9);
        }else{
            nextDepartures = allDepartures.subList(0, allDepartures.size() - 1);
        }
        */

        /*
        Collections.sort(allDepartures, new Comparator<Departure>() {
            public int compare(Departure s1, Departure s2) {
                return s1.getRtTime().compareTo(s2.getRtTime());
            }
        });
        */


        List<Departure> wheelchair = allDepartures.stream()
                .filter(d -> d.getAccessibility() != null).collect(Collectors.toList());

        return wheelchair;
    }
}
