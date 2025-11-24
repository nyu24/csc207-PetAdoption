package data_access;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

import entities.APIPet;
import okhttp3.*;
import org.json.*;
import use_case.set_parameters.SetParamDataAccessInterface;

public class APIPetDataAccessObject implements SetParamDataAccessInterface {
    //final variables
    private static final String API_KEY = "Jl41gwmuH2mlwcj1NGmeSLPs753IaXX0YuwZjds36iyGvz5bzs";
    private static final String API_SECRET = "PZKwvmzOuVIGI4n0G2HMURlui4oTj02hRfwbCw1L";

    //not final, as the access token can EXPIRE
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

    //TODO: create the use cases for 'coats', 'colours', 'genders'/THE PARAMETERS
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
                    String[] splitCoat = s.split("\\[\"");
                    if (splitCoat.length > 1) {
                        coats.add(splitCoat[1]);
                    }
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

            getTypeBreeds(access_token, type, client, attributes);

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

    private static void getTypeBreeds(String access_token, String type, OkHttpClient client, ArrayList<ArrayList<String>> attributes) {
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
    }

    // Filtering ----------------------------------------------------------------------
    // TODO: yeah, once button is pressed, use this then do the 'flippy swish thing'
    // TODO: parameters for each 'APIPet': image, name, description, (maybe later add what was used to filter)
    // TODO: remember to place a back button which leads back to 'SetParametersView'

    // TODO: might need to adjust params depending on whether or not I add new ones
    // TODO: need to check if we should have multiple pages/flipping through pages for this
    public JSONObject getAPIFilteredPage(String access_token, String type, String breed, String coat,
                                        String colour, String gender){
        OkHttpClient client = new OkHttpClient();

        //setting up query
        //status=adoptable, because it would be redundant otherwise
        //type is MANDATORY
        String query = "type=" + type + "&status=adoptable";

        //setting up optional parameters: aka. when the String is blank
        if(!breed.isEmpty()){
            query += "&breed=" + breed;
        }
        if(!coat.isEmpty()){
            query += "&coat=" + coat;
        }
        if(!colour.isEmpty()){
            query += "&color=" + colour;
        }
        if(!gender.isEmpty()){
            query += "&gender=" + gender;
        }

        System.out.println(query + "----------------------------");

        // Setting up request
        Request request = new Request.Builder()
                .url("https://api.petfinder.com/v2/animals?" + query)
                .header("Authorization", "Bearer " + access_token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            final JSONObject responseBody = new JSONObject(response.body().string());

            //return the Filtered API page (default page 1)
            return responseBody;

        } catch (IOException e) { // TODO: might need to change the exception here
            throw new RuntimeException(e);
        }

    }

    //Constructing ONE APIPet entity (helper for constructMultipleAPIPet)
    private APIPet constructAPIPet(JSONObject petInfo, String type, String breed, String coat,
                                   String colour, String gender){
        APIPet apiPet = new APIPet();

        //setting up things IF available
        String desc = "";
        String name = "";
        String url = "";
        String image = "";

        if(petInfo.has("description")){
            desc = petInfo.get("description").toString();
        }
        apiPet.setDescription(desc);

        if(petInfo.has("name")){
            name = petInfo.get("name").toString();
        }
        apiPet.setName(name);

        if(petInfo.has("url")){
            url = petInfo.get("url").toString();
        }
        apiPet.setUrl(url);

        if(petInfo.get("primary_photo_cropped").toString() != "null"){
            image = petInfo.get("primary_photo_cropped").toString().split("\"")[3];
        }
        apiPet.setImage(image);

        //setting up other APIPet variables
        apiPet.setType(type);
        apiPet.setBreed(breed);
        apiPet.setCoat(coat);
        apiPet.setGender(gender);
        apiPet.setColour(colour);

        return apiPet;
    }

    //Constructing MULTIPLE APIPet entities
    public ArrayList<APIPet> constructMultipleAPIPets(String access_token, String type, String breed, String coat,
                                                      String colour, String gender){
        //initialising variables and stuff
        ArrayList<APIPet> apiPets = new ArrayList<>();
        JSONObject responseBody = getAPIFilteredPage(access_token, type, breed, coat, colour, gender);
        JSONArray pets = responseBody.getJSONArray("animals");

        for(int i = 0; i < pets.length(); i++){
            //get the petInformation
            JSONObject petInfo = pets.getJSONObject(i);

            //construct the corresponding APIPet entity
            APIPet apiPet = constructAPIPet(petInfo, type, breed, coat,
                    colour, gender);

            //save the newly constructed APIPet entity in the ArrayList
            apiPets.add(apiPet);
        }

        //return the constructed apiPets array for the page
        return apiPets;
    }

    @Override
    public ArrayList<APIPet> getApiPetArrayList(String type, String coat, String colour, String breed, String gender) {
        return constructMultipleAPIPets(GenerateAccessToken(), type, breed, coat, colour, gender);
    }


    //testing to see if it works TODO: to delete after debugging
    public static void main(String[] args) {
        APIPetDataAccessObject apiPetDataAccessObject = new APIPetDataAccessObject(); //declaration of yeah
        API_ACCESS_TOKEN = apiPetDataAccessObject.GenerateAccessToken(); //api access token generation


        System.out.println("Generated access_token: " + apiPetDataAccessObject.GenerateAccessToken());

        System.out.println("GetTypeAttributes return values: " + apiPetDataAccessObject.getTypeAttributes(API_ACCESS_TOKEN, "Dog"));

        System.out.println("AN example of filtering: " + apiPetDataAccessObject.getAPIFilteredPage(API_ACCESS_TOKEN, "Dog",
                "Akita", "short", "Black", "Male")); //.getJSONArray("animals")

        ArrayList<APIPet> apiPets = apiPetDataAccessObject.constructMultipleAPIPets(API_ACCESS_TOKEN, "Dog",
                "Akita", "short", "Black", "Male");

        System.out.println("Example of an array of APIPets (it's names): ");

        for (APIPet apiPet : apiPets) {
            System.out.println(apiPet.getName() + ": " + apiPet.getDescription() + ": " + apiPet.getUrl());
        }

        //for just female cats
        ArrayList<APIPet> dogs = apiPetDataAccessObject.constructMultipleAPIPets(API_ACCESS_TOKEN, "Cat",
                "", "", "", "Female");
        System.out.println("----------------");
        for (APIPet apiPet : dogs) {
            System.out.println(apiPet.getName() + ": " + apiPet.getDescription() + ": " + apiPet.getUrl());
            System.out.println("image link: " + apiPet.getImage());
        }

        //TODO: format of the JSON stuff to delete later
        /**
         * {"gender":"Male",
         * "distance":null,
         * "_links":{
         *      "organization":{
         *          "href":"/v2/organizations/ks25"
         *          },
         *      "self":{
         *          "href":"/v2/animals/79197396"
         *          },
         *      "type":{
         *          "href":"/v2/types/dog"
         *          }
         *      },
         * "status_changed_at":"2025-11-06T01:51:26+0000",
         * "description":"This is Jewell, our friend with City of Raytown Animal Services! She&#039;s an adorable Akita mix, around five months old,...",
         * "organization_animal_id":null,
         * "videos":[],
         * "type":"Dog",
         * "photos":[{
         *      "small":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=100",
         *      "large":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=600",
         *      "medium":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=300",
         *      "full":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111"
         *      },
         *      {
         *      "small":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/fe81dcd4-08ab-4127-892b-1f146f9a1029.jpg?versionId=ZbBQtDe0c4v0XSW_sGK6hZix39PgkFUz&bust=1762393111&width=100",
         *      "large":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/fe81dcd4-08ab-4127-892b-1f146f9a1029.jpg?versionId=ZbBQtDe0c4v0XSW_sGK6hZix39PgkFUz&bust=1762393111&width=600",
         *      "medium":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/fe81dcd4-08ab-4127-892b-1f146f9a1029.jpg?versionId=ZbBQtDe0c4v0XSW_sGK6hZix39PgkFUz&bust=1762393111&width=300",
         *      "full":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/fe81dcd4-08ab-4127-892b-1f146f9a1029.jpg?versionId=ZbBQtDe0c4v0XSW_sGK6hZix39PgkFUz&bust=1762393111"
         *      }],
         * "colors":{
         *      "secondary":"Brown / Chocolate",
         *      "tertiary":"Black",
         *      "primary":"Golden"
         *      },
         * "breeds":{
         *      "secondary":null,
         *      "mixed":true,
         *      "primary":"Akita",
         *      "unknown":false
         *      },
         * "contact":{
         *      "address":{
         *          "country":"US",
         *          "address2":null,
         *          "city":"Mission",
         *          "address1":"Address will vary",
         *          "postcode":"66202",
         *          "state":"KS"
         *          },
         *       "phone":"(913) 671-7387",
         *       "email":"kelso463@gmail.com"
         *       },
         * "id":79197396,
         * "published_at":"2025-11-06T01:51:25+0000",
         * "primary_photo_cropped":{
         *      "small":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=300",
         *      "large":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=600",
         *      "medium":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111&width=450",
         *      "full":"https://dbw3zep4prcju.cloudfront.net/animal/7e7e7a68-a509-4a54-a4ef-ef87d9bcf799/image/42122a91-bf14-4d9d-ba93-bc7e141e38aa.jpg?versionId=gfRRLugGkkXqh_CEJ5H8meN35JX5H5Vz&bust=1762393111"
         *      },
         * "url":"https://www.petfinder.com/dog/jewell-79197396/ks/mission/the-pet-connection-inc-ks25/?referrer_id=39cfd756-b13a-47aa-94b1-2615f3f4d7b7&utm_source=api&utm_medium=partnership&utm_content=39cfd756-b13a-47aa-94b1-2615f3f4d7b7",
         * "tags":["Affectionate","Couch","Curious","Gentle","Friendly","Loves"],
         * "coat":"Short",
         * "environment":{
         *      "cats":null,
         *      "children":true,
         *      "dogs":true
         *      },
         * "size":"Medium",
         * "species":"Dog",
         * "organization_id":"KS25",
         * "name":"Jewell",
         * "attributes":{
         *      "shots_current":true,
         *      "special_needs":false,
         *      "declawed":null,
         *      "spayed_neutered":true,
         *      "house_trained":false
         *      },
         * "age":"Young",
         * "status":"adoptable"},
         */

    }
}
