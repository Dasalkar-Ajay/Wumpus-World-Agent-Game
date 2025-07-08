package com.aigame.Login_SignUp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class ApiRandomPhoto {

    public String getResponseDAta() throws Exception {
        String url = "https://api.unsplash.com/photos/random?client_id=SgT8yXm0Iy9EOZzp6lmb_HoChK_UOmfbZ-pte2Xyw58";
        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        StringBuffer response = new StringBuffer();
        int responseCode = httpClient.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject urlJsonObject = jsonObject.getJSONObject("urls");
                return urlJsonObject.getString("full");
            } catch (Exception exception) {
                throw new RuntimeException("Error in you Api Please Check These Api or Try Later please");
            }
        } else {

            throw new RuntimeException("Error occures bro" + responseCode);
        }
    }
}
