package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import entities.APIPet;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.select_animal.SelectAnimalDataAccessInterface;
import use_case.set_parameters.SetParamDataAccessInterface;
import okhttp3.*;

/**
 * This data access object contains all necessary API data access methods/manipulations.
 */
public class APIPetDataAccessObject implements SelectAnimalDataAccessInterface, SetParamDataAccessInterface {
    //final variables
    private static final String API_KEY = "Jl41gwmuH2mlwcj1NGmeSLPs753IaXX0YuwZjds36iyGvz5bzs";
    private static final String API_SECRET = "PZKwvmzOuVIGI4n0G2HMURlui4oTj02hRfwbCw1L";

    /**
     * This new API_ACCESS_TOKEN MUST be accessible from other files within this project.
     * Specifically for the use cases
     * @return a new unique access_token given the API_KEY and API_SECRET
     */
    public String GenerateAccessToken() {
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
            if (!response.isSuccessful()) { throw new IOException("Unexpected code " + response); }
            //changes the API_ACCESS_TOKEN to the generated result
            assert response.body() != null;
            final JSONObject responseBody = new JSONObject(response.body().string());
            //returns it as well
            return responseBody.getString("access_token");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //making an API call for SET_PARAMETERS with the generated access token --------------------------------
    /**
     * To use in the 'select animal type' drop down USE CASE.
     * @return a list of all animal types within the API
     */
    @Override
    public ArrayList<String> getTypes() {
        String access_token = GenerateAccessToken();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/types")
                .header("Authorization", "Bearer " + access_token)
                .build();

        //obtaining all possible animal types and placing returning it
        ArrayList<String> types = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) { throw new IOException("Unexpected code " + response); }
            assert response.body() != null;
            final JSONObject responseBody = new JSONObject(response.body().string());

            //goes through the API and adds any animal name into the 'types' ArrayList
            for (int i = 0; i < responseBody.getJSONArray("types").length(); i++) {
                String newType = responseBody.getJSONArray("types").getJSONObject(i).getString("name");
                if (!newType.contains("&")) {
                    types.add(newType);
                }
            }
            return types;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To use in the proper USE CASES.
     * @param type, the type of animal to get attributes for
     * @return a list of all 'breeds', 'coats', 'colours', and 'genders' for the given type IN THIS ORDER
     */
    @Override
    public ArrayList<ArrayList<String>> getTypeAttributesList(String type) {
        String accessToken =  GenerateAccessToken();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/types/" + type)
                .header("Authorization", "Bearer " + accessToken)
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
            if (!response.isSuccessful()) { throw new IOException("Unexpected code " + response); }
            assert response.body() != null;
            final JSONObject responseBody = new JSONObject(response.body().string());

            //coats, extracting the object and converting it into an arraylist of strings
            String[] objCoats = responseBody.getJSONObject("type").get("coats").toString().split("\",\"");
            for (String s : objCoats) {
                if (s.contains("[")) {
                    String[] splitCoat = s.split("\\[\"");
                    if (splitCoat.length > 1) {
                        coats.add(splitCoat[1]);
                    }
                } else if (s.contains("]")) {
                    coats.add(s.split("\"]")[0]);
                } else if (!s.contains("&")) {
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
                } else if (!s.contains("&")) {
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

            getTypeBreeds(accessToken, type, client, attributes);

            //adding the rest into an arraylist
            attributes.add(coats);
            attributes.add(colours);
            attributes.add(genders);

            //returning an arraylist of all the arraylists
            return attributes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getTypeBreeds(String accessToken, String type, OkHttpClient client, ArrayList<ArrayList<String>> attributes) {
        //making separate request for breeds (as it takes a different URL)
        Request requestBreed = new Request.Builder()
                .url("https://api.petfinder.com/v2/types/" + type + "/breeds")
                .header("Authorization", "Bearer " + accessToken)
                .build();

        //initialize breeds ArrayList
        ArrayList<String> breeds = new ArrayList<>();

        try (Response responseBreeds = client.newCall(requestBreed).execute()) {
            if (!responseBreeds.isSuccessful()) { throw new IOException("Unexpected code " + responseBreeds); }
            assert responseBreeds.body() != null;
            final JSONObject responseBodyBreeds = new JSONObject(responseBreeds.body().string());

            //extracting all possible breeds for the animal type
            for (int i = 0; i < responseBodyBreeds.getJSONArray("breeds").length(); i++) {
                String newBreed = responseBodyBreeds.getJSONArray("breeds").getJSONObject(i).getString("name");
                if (!newBreed.contains("&")) {
                    breeds.add(newBreed);
                }
            }

            //adding the list of breeds into the attributes arraylist
            attributes.add(breeds);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Filtering ----------------------------------------------------------------------
    private JSONObject getAPIFilteredPage(String accessToken, String type, String breed, String coat,
                                        String colour, String gender) {
        OkHttpClient client = new OkHttpClient();

        //setting up query
        //status=adoptable, because it would be redundant otherwise
        //type is MANDATORY
        String query = "type=" + type + "&status=adoptable";

        //setting up optional parameters: aka. when the String is blank
        if (!breed.isEmpty()) {
            query += "&breed=" + breed;
        }
        if (!coat.isEmpty()) {
            query += "&coat=" + coat;
        }
        if (!colour.isEmpty()) {
            query += "&color=" + colour;
        }
        if (!gender.isEmpty()) {
            query += "&gender=" + gender;
        }

        // Setting up request
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/animals?" + query)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) { throw new IOException("Unexpected code " + response); }

            //return the Filtered API page (default page 1)
            assert response.body() != null;
            return new JSONObject(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Constructing ONE APIPet entity (helper for constructMultipleAPIPet)
    private APIPet constructAPIPet(JSONObject petInfo) {
        APIPet apiPet = new APIPet();

        //setting up things IF available
        String desc = "";
        String name = "";
        String url = "";
        String image = "";
        String primaryColor = "";
        String primaryBreed = "";
        String coatAPI = "";

        if (petInfo.has("description")) {
            desc = petInfo.get("description").toString();
        }
        apiPet.setDescription(desc);

        if (petInfo.has("name")) {
            name = petInfo.get("name").toString();
        }
        apiPet.setName(name);

        if (petInfo.has("url") && petInfo.get("url") != null) {
            url = petInfo.get("url").toString();
        }
        apiPet.setUrl(url);

        if (!Objects.equals(petInfo.get("primary_photo_cropped").toString(), "null")) {
            final int desiredImageLocation = 3;
            image = petInfo.get("primary_photo_cropped").toString().split("\"")[desiredImageLocation];
        }
        apiPet.setImage(image);

        //setting up other APIPet variables from THIS petInfo (for cases where parameters are empty)
        apiPet.setType(petInfo.get("type").toString());
        apiPet.setGender(petInfo.get("gender").toString());

        if (petInfo.has("coat")) {
            coatAPI = petInfo.get("coat").toString();
        }
        apiPet.setCoat(coatAPI);

        final String primary = "primary";

        if (petInfo.has("colors") && petInfo.getJSONObject("colors").has(primary)
            && petInfo.getJSONObject("colors").get(primary) != null) {
            primaryColor = petInfo.getJSONObject("colors").get("primary").toString();
        }
        apiPet.setColour(primaryColor);

        if (petInfo.has("breeds") && petInfo.getJSONObject("breeds").has(primary)
            && petInfo.getJSONObject("breeds").get(primary) != null) {
            primaryBreed = petInfo.getJSONObject("breeds").get(primary).toString();
        }
        apiPet.setBreed(primaryBreed);

        return apiPet;
    }

    // Constructing MULTIPLE APIPet entities
    private ArrayList<APIPet> constructMultipleApiPets(String accessToken, String type, String breed, String coat,
                                                       String colour, String gender) {
        // initialising variables and stuff
        final ArrayList<APIPet> apiPets = new ArrayList<>();
        final JSONObject responseBody = getAPIFilteredPage(accessToken, type, breed, coat, colour, gender);
        final JSONArray pets = responseBody.getJSONArray("animals");

        for (int i = 0; i < pets.length(); i++) {
            // get the petInformation
            final JSONObject petInfo = pets.getJSONObject(i);

            // construct the corresponding APIPet entity
            final APIPet apiPet = constructAPIPet(petInfo);

            // save the newly constructed APIPet entity in the ArrayList
            apiPets.add(apiPet);
        }

        // return the constructed apiPets array for the page
        return apiPets;
    }

    @Override
    public ArrayList<APIPet> getApiPetArrayList(String type, String coat, String colour, String breed, String gender) {
        return constructMultipleApiPets(GenerateAccessToken(), type, breed, coat, colour, gender);
    }
}
