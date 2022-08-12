package edu.escuelaing.arep;

import static spark.Spark.*;
public class SparkWebApp {
    public static void main() {
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
        //get("/data", ((request, response) -> response.type("application/json");
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
