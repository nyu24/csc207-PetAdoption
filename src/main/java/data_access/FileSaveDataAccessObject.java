package data_access;

import entities.ApiPet;
import entities.Pet;
import entities.SaveFile;
import org.jetbrains.annotations.NotNull;
import use_case.load_game.LoadGameDataAccessInterface;
import use_case.save_game.SaveGameDataAccessInterface;

import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileSaveDataAccessObject implements SaveGameDataAccessInterface, LoadGameDataAccessInterface {
    private final File jsonFile;
    private SaveFile saveFile;
    private static final int MAX_STAT_VAL = 100;

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

            JSONArray jsonArray = new JSONArray(saveString);

            JSONObject outerObj = jsonArray.getJSONObject(0);
            int timeLeft = outerObj.getInt("timeLeft");
            int currScore = outerObj.getInt("currScore");

            JSONObject apiPetInfo = outerObj.getJSONObject("apiPetInfo");
            ApiPet apiPet = getApiPet(apiPetInfo);

            JSONObject petInfo = outerObj.getJSONObject("petInformation");
            Pet currPet = getCurrPet(MAX_STAT_VAL, petInfo);
            currPet.setApiPet(apiPet);

            this.saveFile = new SaveFile(timeLeft, currScore, currPet, apiPet);

        }

    }

    @NotNull
    private static Pet getCurrPet(int maxStatValue, JSONObject petInfo) {
        Pet currPet = new Pet(maxStatValue, maxStatValue, maxStatValue, maxStatValue);
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
        ApiPet apiPet = new ApiPet();
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

    public void save() {
        ApiPet apiPet = this.saveFile.getApiPet();
        JSONObject apiPetJson = getApiPetJson(apiPet);
        Pet currPet = this.saveFile.getCurrPet();
        JSONObject petInfoJson = getPetJsonObject(currPet);

        JSONObject outerObj = new JSONObject();
        outerObj.put("timeLeft", saveFile.getTimeLeft());
        outerObj.put("currScore", saveFile.getCurrScore());
        outerObj.put("apiPetInfo", apiPetJson);
        outerObj.put("petInformation", petInfoJson);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(outerObj);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
            writer.write(jsonArray.toString(4));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @NotNull
    private JSONObject getPetJsonObject(Pet currPet) {
        JSONObject petInfoJson = new JSONObject();
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
        JSONObject apiPetJson = new JSONObject();
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
        if (input == null) {
            return "null";
        }
        return input;
    }

    public void save(SaveFile saveFile) {
        this.saveFile = saveFile;
        this.save();
    }

    public SaveFile load() {
        return this.saveFile;
    }

    @Override
    public boolean saveDataExists() {
        return jsonFile.length() != 0;
    }
}
