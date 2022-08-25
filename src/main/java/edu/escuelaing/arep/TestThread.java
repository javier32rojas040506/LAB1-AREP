package edu.escuelaing.arep;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class TestThread extends Thread{
    private static final String USER_AGENT = "Mozilla/5.0";
    public void run() {
        URL obj = null;
        try {
            obj = new URL("http://localhost:4567/data?name=BBVA");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                StringBuilder responseString = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    responseString.append(inputLine);
                }
                in.close();
                System.out.println(responseString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
