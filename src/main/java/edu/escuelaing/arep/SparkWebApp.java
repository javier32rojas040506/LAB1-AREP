package edu.escuelaing.arep;

import static spark.Spark.*;

/**
 * This is a class that attends all of http petitions with spark web
 * @author: Francisco Javier Rojas M
 * @version: 18/08/2022/A
 */
public class SparkWebApp {

    //URLS from APIs
    private static final String API_URL_1 = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=name&interval=5min&apikey=QJ03PA2SC2JFM7EE";
    private static final String API_URL_2 = "https://api.polygon.io/v1/open-close/name/2020-10-14?adjusted=true&apiKey=7uoEXwg2fBxmL3JeB5CaYtJCv32JSJLs";

    /**
     * This is the main class of the server that defines the endpoints to be attended
     * @param args
     */
    public static void main(String[] args) {
        //Getting the port to deploy the services
        port(getPort());
        //Defining the statics files (front)
        staticFiles.location("/public");
        //endpont example in spark web
        get("/hello", (request, response) -> {
            String name = request.queryParams("name");
            return "Hello Heroku :) " + name;
        });
        //endponit to get data via GET
        get("/data", (request, response) -> {
            response.type("application/json");
            String name = request.queryParams("name");
            return ActionsStock.getStockByName(name, API_URL_1, "Time Series (5min)");
        });
        //endponit to get data via POST
        post("/databypost", "application/json",
                (request, response) -> {
                    String name = request.queryParams("name");
                    return ActionsStock.getStockByName(name, API_URL_2, null);
        });

    }

    /**
     * Method that get the port to expose the services
     * @return int that represent the port of the server
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
