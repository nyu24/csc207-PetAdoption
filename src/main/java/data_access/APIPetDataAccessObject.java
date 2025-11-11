package data_access;

import entities.APIPet;
import java.io.IOException;
import okhttp3.*;
import org.json.JSONObject;

public class APIPetDataAccessObject { //implements SetParamDataAccessInterface
    //final variables
    private static final String API_KEY = "Jl41gwmuH2mlwcj1NGmeSLPs753IaXX0YuwZjds36iyGvz5bzs";
    private static final String API_SECRET = "PZKwvmzOuVIGI4n0G2HMURlui4oTj02hRfwbCw1L";
    // private static final String API_URL ;
    private static String API_ACCESS_TOKEN;


    //calls api to generate an access token
    public static void GenerateAccessToken()
    {
        // Make an API call to get the user object.
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", API_KEY)
                .add("client_secret", API_SECRET)
                .build();

        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/oauth2/token")
                .post(formBody)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            final JSONObject responseBody = new JSONObject(response.body().string());
            API_ACCESS_TOKEN = responseBody.getString("access_token");

        } catch (IOException e) { //TODO: might need to change the error/exception here
            throw new RuntimeException(e);
        }
    }

    //making an API call with the generated access token
    public APIPet get(String api_key, String api_secret, String access_token){
        
    }

    //testing to see if the token generator works TODO: to delete after debugging
    public static void main(String[] args) {
        GenerateAccessToken();
    }
}
