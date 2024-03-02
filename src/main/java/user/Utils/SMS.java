package user.Utils;

import com.google.gson.Gson;
import okhttp3.*;
import user.Models.LocationInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class SMS {
    private static LocationInfo getLocalisation(){
        LocationInfo locationInfo = null;
        try {
            URL ipifyUrl = new URL("https://api.ipify.org");
            try {
                HttpURLConnection connection = (HttpURLConnection) ipifyUrl.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String ipAddress = response.toString();
                URL url = new URL("http://ip-api.com/json/" + ipAddress);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                inputLine = "";
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Gson gson = new Gson();
                locationInfo = gson.fromJson(response.toString(), LocationInfo.class);
                return locationInfo;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return locationInfo;
    }
    public static void sendSms(int numero){
        String num = String.valueOf(numero);
        LocationInfo locationInfo = getLocalisation();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"messages\":[{\"destinations\":[{\"to\":\"216"+num+"\"}],\"from\":\"ServiceSMS\",\"text\":\"Unauthorized Access Attempt,\\n\\nWe have detected an attempt to access your account : \\nLocation : "+locationInfo.getRegionName()+", "+locationInfo.getCity()+", "+locationInfo.getCountry()+"\\nPlease verify your account and change your password if necessary.\"}]}");
        Request request = new Request.Builder()
                .url("https://3g33gw.api.infobip.com/sms/2/text/advanced")
                .method("POST", body)
                .addHeader("Authorization", "App eb50bb0c94feab3789e49e93cd802b3c-734c3925-93c7-40e4-9a6d-7e32a26a1b1b")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
