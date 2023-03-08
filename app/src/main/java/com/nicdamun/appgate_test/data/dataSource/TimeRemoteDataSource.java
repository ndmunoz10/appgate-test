package com.nicdamun.appgate_test.data.dataSource;

import com.nicdamun.appgate_test.BuildConfig;
import com.nicdamun.appgate_test.data.dto.TimeDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

public class TimeRemoteDataSource {

     public CompletableFuture<TimeDto> getCurrentTime(
         final double latitude,
         final double longitude
     ) {
         return CompletableFuture.supplyAsync(() -> {
             HttpURLConnection connection = null;
             TimeDto timeDtoResponse = null;
             try {
                 final URL url = new URL(
                     BuildConfig.BASE_URL +
                         "coordinate?latitude=" + latitude +
                         "&longitude=" + longitude
                 );
                 String line;
                 connection = (HttpURLConnection) url.openConnection();
                 connection.setRequestMethod("GET");
                 final BufferedReader rd  = new BufferedReader(
                     new InputStreamReader(connection.getInputStream())
                 );
                 while ((line = rd.readLine()) != null) {
                     timeDtoResponse = parseResponse(line);
                 }
                 rd.close();
             } catch (IOException | JSONException | ParseException e) {
                 e.printStackTrace();
             } finally {
                 if (connection != null) {
                     connection.disconnect();
                 }
             }
             return timeDtoResponse;
         });
     }

     private TimeDto parseResponse(final String line) throws ParseException, JSONException {
         final JSONObject json = new JSONObject(line);
         return new TimeDto(
             json.optString("currentLocalTime")
         );
     }
}
