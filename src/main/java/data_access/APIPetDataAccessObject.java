package data_access;

import java.io.IOException;
import java.util.*;
import okhttp3.*;
import org.json.*;

public class APIPetDataAccessObject { //implements SetParamDataAccessInterface
    //final variables
    private static final String API_KEY = "Jl41gwmuH2mlwcj1NGmeSLPs753IaXX0YuwZjds36iyGvz5bzs";
    private static final String API_SECRET = "PZKwvmzOuVIGI4n0G2HMURlui4oTj02hRfwbCw1L";
    private static String API_ACCESS_TOKEN;

    /**
     * This new API_ACCESS_TOKEN MUST be accessible from other files within this project
     * Specifically for the use cases
     * @return a new unique access_token given the API_KEY and API_SECRET
     */
    public String GenerateAccessToken()
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
            //changes the API_ACCESS_TOKEN to the generated result
            final JSONObject responseBody = new JSONObject(response.body().string());
            //returns it as well
            return responseBody.getString("access_token");

        } catch (IOException e) { //TODO: might need to change the error/exception here
            throw new RuntimeException(e);
        }
    }

    //making an API call for SET_PARAMETERS with the generated access token --------------------------------

    //TODO: make the 'select animal type' USE CASE
    /**
     * To use in the 'select animal type' drop down USE CASE
     * @param access_token
     * @return a list of all animal types within the API
     */
    public ArrayList<String> getTypes(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/types")
                .header("Authorization", "Bearer " + access_token)
                .build();

        //obtaining all possible animal types and placing returning it
        ArrayList<String> types = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            final JSONObject responseBody = new JSONObject(response.body().string());

            //goes through the API and adds any animal name into the 'types' ArrayList
            for(int i = 0; i < responseBody.getJSONArray("types").length(); i++){
                types.add(responseBody.getJSONArray("types").getJSONObject(i).getString("name"));
            }
            return types;

        } catch (IOException e) { // TODO: might need to change the exception here
            throw new RuntimeException(e);
        }
    }

    //TODO: create the use cases for 'coats', 'colours', 'genders'
    /**
     * To use in the proper USE CASES
     * @param access_token
     * @param type
     * @return a list of all 'breeds', 'coats', 'colours', and 'genders' for the given type IN THIS ORDER
     */
    public ArrayList<ArrayList<String>> getTypeAttributes(String access_token, String type){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/types/" + type)
                .header("Authorization", "Bearer " + access_token)
                .build();

        //ArrayLists variables of necessary attributes
        //mother arraylist
        ArrayList<ArrayList<String>> attributes = new ArrayList<>();
        //coats
        ArrayList<String> coats = new ArrayList<>();
        //colours
        ArrayList<String> colours = new ArrayList<>();
        //gender
        ArrayList<String> genders = new ArrayList<>();

        //setting up all the ArrayLists
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            final JSONObject responseBody = new JSONObject(response.body().string());

            //coats, extracting the object and converting it into an arraylist of strings
            String[] objCoats = responseBody.getJSONObject("type").get("coats").toString().split("\",\"");
            for (String s : objCoats) {
                if (s.contains("[")) {
                    coats.add(s.split("\\[\"")[1]);
                } else if (s.contains("]")) {
                    coats.add(s.split("\"]")[0]);
                } else {
                    coats.add(s);
                }
            }
            //colours, same methodology as above for coats
            String[] objColours = responseBody.getJSONObject("type").get("colors").toString().split("\",\"");
            for (String s : objColours) {
                if (s.contains("[")) {
                    colours.add(s.split("\\[\"")[1]);
                } else if (s.contains("]")) {
                    colours.add(s.split("\"]")[0]);
                } else {
                    colours.add(s);
                }
            }

            //gender, same methodology above as coats
            String[] objGenders = responseBody.getJSONObject("type").get("genders").toString().split("\",\"");
            for (String s : objGenders) {
                if (s.contains("[")) {
                    genders.add(s.split("\\[\"")[1]);
                } else if (s.contains("]")) {
                    genders.add(s.split("\"]")[0]);
                } else {
                    genders.add(s);
                }
            }

            //making seperate request for breeds (as it takes a different URL)
            Request requestBreed = new Request.Builder()
                    .url("https://api.petfinder.com/v2/types/" + type + "/breeds")
                    .header("Authorization", "Bearer " + access_token)
                    .build();

            //initialize breeds ArrayList
            ArrayList<String> breeds = new ArrayList<>();

            try (Response responseBreeds = client.newCall(requestBreed).execute()) {
                if (!responseBreeds.isSuccessful()) throw new IOException("Unexpected code " + responseBreeds);
                final JSONObject responseBodyBreeds = new JSONObject(responseBreeds.body().string());

                //extracting all possible breeds for the animal type
                for(int i = 0; i< responseBodyBreeds.getJSONArray("breeds").length(); i++){
                    breeds.add(responseBodyBreeds.getJSONArray("breeds").getJSONObject(i).getString("name"));
                }

                //adding the list of breeds into the attributes arraylist
                attributes.add(breeds);

            } catch (IOException e) { // TODO: might need to change the exception here
                throw new RuntimeException(e);
            }

            //adding the rest into an arraylist
            attributes.add(coats);
            attributes.add(colours);
            attributes.add(genders);

            //returning an arraylist of all the arraylists
            return attributes;

        } catch (IOException e) { // TODO: might need to change the exception here
            throw new RuntimeException(e);
        }
    }

    // make API calls for SELECT_ANIMAL with the selected animal attributes
    // TODO: yeah

    //testing to see if the token generator works TODO: to delete after debugging
    public static void main(String[] args) {
        APIPetDataAccessObject apiPetDataAccessObject = new APIPetDataAccessObject(); //declaration of yeah
        API_ACCESS_TOKEN = apiPetDataAccessObject.GenerateAccessToken(); //api access token generation

        System.out.println(apiPetDataAccessObject.getTypeAttributes(API_ACCESS_TOKEN, "Dog"));
    }
}
