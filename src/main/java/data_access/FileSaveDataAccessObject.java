package data_access;

import java.io.*;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import entities.ApiPet;
import entities.Pet;
import entities.SaveFile;
import use_case.load_game.LoadGameDataAccessInterface;
import use_case.save_game.SaveGameDataAccessInterface;

/**
 * DAO for the save game data implemented using File to persit data.
 */
public class FileSaveDataAccessObject implements SaveGameDataAccessInterface, LoadGameDataAccessInterface {
    private final File jsonFile;
    private SaveFile saveFile;

    /**
     * Construct this DAO for saving to and reading from a local file.
     * @param path the path of the file to save to
     * @throws RuntimeException if there is an IOException when accessing the file
     */
    public FileSaveDataAccessObject(String path) {

        jsonFile = new File(path);

        if (jsonFile.length() != 0) {
            String saveString = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    saveString += row;
                }
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            final JSONArray jsonArray = new JSONArray(saveString);

            final JSONObject outerObj = jsonArray.getJSONObject(0);
            final int timeLeft = outerObj.getInt("timeLeft");
            final int currScore = outerObj.getInt("currScore");

            final JSONObject apiPetInfo = outerObj.getJSONObject("apiPetInfo");
            final ApiPet apiPet = getApiPet(apiPetInfo);

            final JSONObject petInfo = outerObj.getJSONObject("petInformation");
            final Pet currPet = getCurrPet(100, petInfo);
            currPet.setApiPet(apiPet);

            this.saveFile = new SaveFile(timeLeft, currScore, currPet, apiPet);

        }

    }

    @NotNull
    private static Pet getCurrPet(int maxStatValue, JSONObject petInfo) {
        final Pet currPet = new Pet(maxStatValue, maxStatValue, maxStatValue, maxStatValue);
        currPet.setName(petInfo.getString("name"));
        currPet.setPetSpritePath(petInfo.getString("petSpritePath"));
        currPet.setHunger(petInfo.getInt("hunger"));
        currPet.setThirst(petInfo.getInt("thirst"));
        currPet.setCleanliness(petInfo.getInt("cleanliness"));
        currPet.setHappiness(petInfo.getInt("happiness"));
        return currPet;
    }

    @NotNull
    private static ApiPet getApiPet(JSONObject apiPetInfo) {
        final ApiPet apiPet = new ApiPet();
        apiPet.setName(apiPetInfo.getString("name"));
        apiPet.setImage(apiPetInfo.getString("image"));
        apiPet.setUrl(apiPetInfo.getString("url"));
        apiPet.setType(apiPetInfo.getString("type"));
        apiPet.setBreed(apiPetInfo.getString("breed"));
        apiPet.setGender(apiPetInfo.getString("gender"));
        apiPet.setDescription(apiPetInfo.getString("description"));
        apiPet.setCoat(apiPetInfo.getString("coat"));
        apiPet.setColour(apiPetInfo.getString("colour"));
        return apiPet;
    }

    /**
     * Save the save file in DAO to file.
     * @throws RuntimeException if there is an IOException when accessing the file
     */

    public void save() {
        final int indentFactor = 4;
        final ApiPet apiPet = this.saveFile.getApiPet();
        final JSONObject apiPetJson = getApiPetJson(apiPet);
        final Pet currPet = this.saveFile.getCurrPet();
        final JSONObject petInfoJson = getPetJsonObject(currPet);

        final JSONObject outerObj = new JSONObject();
        outerObj.put("timeLeft", saveFile.getTimeLeft());
        outerObj.put("currScore", saveFile.getCurrScore());
        outerObj.put("apiPetInfo", apiPetJson);
        outerObj.put("petInformation", petInfoJson);

        final JSONArray jsonArray = new JSONArray();
        jsonArray.put(outerObj);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
            writer.write(jsonArray.toString(indentFactor));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Save the save file in DAO and then saves it in the file.
     * @param save savefile from the game to be saved.
     */

    public void save(SaveFile save) {
        this.saveFile = save;
        this.save();
    }

    @NotNull
    private JSONObject getPetJsonObject(Pet currPet) {
        final JSONObject petInfoJson = new JSONObject();
        petInfoJson.put("name", convertNull(currPet.getName()));
        petInfoJson.put("petSpritePath", convertNull(currPet.getPetSpritePath()));
        petInfoJson.put("hunger", currPet.getHunger());
        petInfoJson.put("thirst", currPet.getThirst());
        petInfoJson.put("cleanliness", currPet.getCleanliness());
        petInfoJson.put("happiness", currPet.getHappiness());
        return petInfoJson;
    }

    @NotNull
    private JSONObject getApiPetJson(ApiPet apiPet) {
        final JSONObject apiPetJson = new JSONObject();
        apiPetJson.put("name", convertNull(apiPet.getName()));
        apiPetJson.put("image", convertNull(apiPet.getImage()));
        apiPetJson.put("url", convertNull(apiPet.getUrl()));
        apiPetJson.put("type", convertNull(apiPet.getType()));
        apiPetJson.put("breed", convertNull(apiPet.getBreed()));
        apiPetJson.put("gender", convertNull(apiPet.getGender()));
        apiPetJson.put("description", convertNull(apiPet.getDescription()));
        apiPetJson.put("coat", convertNull(apiPet.getCoat()));
        apiPetJson.put("colour", convertNull(apiPet.getColour()));
        return apiPetJson;
    }

    private String convertNull(String input) {
        String output = input;
        if (input == null) {
            output = "null";
        }
        return output;
    }

    /**
     * Load the save file in DAO.
     * @return a save file loaded from the DAO.
     */
    public SaveFile load() {
        return this.saveFile;
    }

    @Override
    public boolean saveDataExists() {
        return jsonFile.length() != 0;
    }
}
