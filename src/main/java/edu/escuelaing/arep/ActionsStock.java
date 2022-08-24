package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * This is a class or sevice that have all the logic to get data from APIs or cache Storage
 * @author: Francisco Javier Rojas M
 * @version: 18/08/2022/A
 */
public class ActionsStock {
    //User agent to get data via rest
    private static final String USER_AGENT = "Mozilla/5.0";
    //Cache to improve the performance of the server
    private static Map<String, JSONObject> cacheData = new HashMap<String, JSONObject>();

    /**
     * This method get the data to API and parse it to respond petitions
         * @param name name of the Stock to search
         * @param GET_URL address of the API to search
         * @param JSON_KEY json key to identify data an extract it
     * @return String of json with the Stock DATA
     * @throws IOException
     */
    public static String getStockByName(String name , String GET_URL,String JSON_KEY) throws IOException {
        String URL_API= GET_URL.replace("name", name);
        JSONObject jsonObj = searchInCache(URL_API);
        if(jsonObj != null){
            System.out.println("El valor de cache que se encontro es:");
            System.out.println(jsonObj.toString());
            return jsonObj.toString();
        }
        URL obj = new URL(URL_API);
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
            jsonObj = new JSONObject(responseString.toString());
            if(JSON_KEY != null) {
                 jsonObj = jsonObj.getJSONObject(JSON_KEY);
            }
            cacheData.put(URL_API, jsonObj);
            System.out.println("Respueta del Servidor en JSON:");
            System.out.println(jsonObj.toString());
            return jsonObj.toString();

        } else {
            System.out.println("GET request not worked");
        }
        return("BAD NAME");
    }

    /**
     * This method search the data in cache server
     * @param URL_API is the addres from api that get the final data
     * @return JSONObject with the data from cache
     */
    public static JSONObject searchInCache(String URL_API){
        System.out.println("==============Inicio de Busqueda =========");
        System.out.println("key de cache es:");
        System.out.println(URL_API);
        JSONObject jsonObj = cacheData.get(URL_API);
         return jsonObj;
    }

}

