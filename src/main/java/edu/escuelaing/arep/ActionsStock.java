package edu.escuelaing.arep;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface ActionsStock {

    /**
     * This method get the data to API and parse it to respond petitions
     * @param name name of the Stock to search
     * @param GET_URL address of the API to search
     * @param JSON_KEY json key to identify data an extract it
     * @return String of json with the Stock DATA
     * @throws IOException
     */
    public String getStockByName(String name , String GET_URL,String JSON_KEY) throws IOException;

    /**
     * This method search the data in cache server
     *
     * @param URL_API is the addres from api that get the final data
     * @return JSONObject with the data from cache
     */
    public JSONObject searchInCache(String URL_API);

}
