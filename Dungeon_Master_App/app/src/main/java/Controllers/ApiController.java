package Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiController {

    private URL url;
    private String urlApi;
    private static String ip;
    private JSONObject equipmentObject;
    private JSONObject spellObject;
    private HttpURLConnection httpURLConnection;

    static {
        ip = "192.168.160.178";
    }

    public ApiController() {
        equipmentObject = new JSONObject();
        spellObject = new JSONObject();
    }

    public void crearUsuario(String username, String email, String password) throws IOException, JSONException {

        JSONObject usuario = new JSONObject();
        usuario.put("username", username);
        usuario.put("email", email);
        usuario.put("password", password);
        urlApi = "http://" + ip + ":8080/api/user";
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);


        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
        writer.write(usuario.toString());
        writer.flush();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();

    }

    public void crearCharacter(String name, String characterClass, int level, String race, String background, String alignment, String skill, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma
            , int experiencePoints, int armorClass, int initiative, int speed, int hitPoints, JSONObject username, JSONObject equipment, JSONObject spell) throws JSONException, IOException {

        createEquipment(equipment);
        takeLastEquipment(getAllEquipment());

        createSpell(spell);
        takeLastSpell(getAllSpells());

        JSONArray equipmentList = new JSONArray();
        equipmentList.put(equipmentObject);
        JSONArray spellList = new JSONArray();
        spellList.put(spellObject);
        JSONObject character = new JSONObject();

        character.put("characterName", name);
        character.put("characterLevel", level);
        character.put("characterClass", characterClass);
        character.put("characterRace", race);
        character.put("characterBackground", background);
        character.put("characterAlignment", alignment);
        character.put("skill", skill);
        character.put("strength", strength);
        character.put("dexterity", dexterity);
        character.put("constitution", constitution);
        character.put("intelligence", intelligence);
        character.put("charisma", charisma);
        character.put("wisdom", wisdom);
        character.put("experiencePoints", experiencePoints);
        character.put("armorClass", armorClass);
        character.put("initiative", initiative);
        character.put("speed", speed);
        character.put("hitPoints", hitPoints);
        character.putOpt("playerName", username);
        character.put("equipmentList", equipmentList);
        character.put("spellList", spellList);

        urlApi = "http://" + ip + ":8080/api/character";
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);


        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
        writer.write(character.toString());
        writer.flush();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();

        equipmentObject = null;
        spellObject = null;

    }


    public String getAllUsers() throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/users";
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public String getAllEquipment() throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/equipments";
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    private void takeLastEquipment(String equipmentList) throws JSONException {
        JSONArray list = new JSONArray(equipmentList);

        JSONObject equipment = list.getJSONObject(list.length() - 1);
        Long equipmentId = equipment.getLong("id");
        equipmentObject.put("id", equipmentId);
    }

    public String getCharacterById(Long id) throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/character/" + id;
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public String getUserByUsername(String username) throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/user/username/" + username;
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public String getUserByEmail(String email) throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/user/email/" + email;
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public String getAllEquipmentSpellOrMonster(String item) throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "https://www.dnd5eapi.co/api/" + item;
        this.url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public String getObject(String item, String index) throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "https://www.dnd5eapi.co/api/" + item + "/" + index;
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    public void createEquipment(JSONObject equipment) throws IOException, JSONException {

        equipmentObject.put("index", equipment.getString("index"));
        equipmentObject.put("name", equipment.getString("name"));

        if (equipment.has("equipment_category")) {
            JSONObject equipmentCategory = equipment.getJSONObject("equipment_category");
            equipmentObject.put("equipmentCategoryName", equipmentCategory.getString("name"));
            equipmentObject.put("equipmentCategoryIndex", equipmentCategory.getString("index"));
        }

        if (equipment.has("gear_category")) {
            JSONObject gearCategory = equipment.getJSONObject("gear_category");
            equipmentObject.put("gearCategoryIndex", gearCategory.getString("index"));
            equipmentObject.put("gearCategoryName", gearCategory.getString("name"));

        }

        if (equipment.has("cost")) {
            JSONObject cost = equipment.getJSONObject("cost");
            equipmentObject.put("quantity", cost.getInt("quantity"));
            equipmentObject.put("costUnit", cost.getString("unit"));
        }

        if (equipment.has("weight")) {
            equipmentObject.put("weight", equipment.getInt("weight"));
        }

        urlApi = "http://" + ip + ":8080/api/equipment";
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);


        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
        writer.write(equipmentObject.toString());
        writer.flush();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();

    }

    public void deleteCharacter(Long id) throws IOException {
        urlApi = "http://" + ip + ":8080/api/character/" + id;
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("DELETE");
        int respuesta = httpURLConnection.getResponseCode();
    }

    public void updateCharacter(Long id, JSONObject character) throws IOException {
        urlApi = "http://" + ip + ":8080/api/character/" + id;
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
        writer.write(character.toString());
        writer.flush();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();
    }

    public void createSpell(JSONObject spell) throws JSONException, IOException {

        spellObject.put("index", spell.getString("index"));
        spellObject.put("name", spell.getString("name"));

        if (spell.has("desc")) {
            JSONArray description = spell.getJSONArray("desc");
            String shortDesc = description.get(0).toString();
            int index = shortDesc.indexOf('.');
            String descripcionCorta;

            if (index == -1) {
                descripcionCorta = shortDesc;
            } else {
                descripcionCorta = shortDesc.substring(0, index + 1);
            }
            spellObject.put("description", descripcionCorta);
        }

        if (spell.has("range")) {
            spellObject.put("range", spell.getString("range"));
        }

        if (spell.has("material")) {
            spellObject.put("material", spell.getString("material"));
        }

        if (spell.has("ritual")) {
            spellObject.put("ritual", spell.getBoolean("ritual"));
        }

        if (spell.has("duration")) {
            spellObject.put("duration", spell.getString("duration"));
        }

        if (spell.has("concentration")) {
            spellObject.put("concentration", spell.getString("concentration"));
        }

        if (spell.has("casting_time")) {
            spellObject.put("castingTime", spell.getString("casting_time"));
        }

        if (spell.has("level")) {
            spellObject.put("level", spell.getInt("level"));
        }

        if (spell.has("attack_type")) {
            spellObject.put("attackType", spell.getString("attack_type"));
        }

        if (spell.has("damage")) {
            JSONObject damageObject = spell.getJSONObject("damage");
            JSONObject damageTypeObject = damageObject.getJSONObject("damage_type");
            spellObject.put("damageTypeIndex", damageTypeObject.getString("index"));
            spellObject.put("damageTypeName", damageTypeObject.getString("name"));
        }

        if (spell.has("school")) {
            JSONObject school = spell.getJSONObject("school");
            spellObject.put("schoolIndex", school.getString("index"));
            spellObject.put("schoolName", school.getString("name"));
        }

        urlApi = "http://" + ip + ":8080/api/spell";
        this.httpURLConnection = (HttpURLConnection) new URL(urlApi).openConnection();
        this.httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);


        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
        writer.write(spellObject.toString());
        writer.flush();
        int responseCode = httpURLConnection.getResponseCode();
        httpURLConnection.disconnect();
    }

    public String getAllSpells() throws IOException {
        StringBuilder json = new StringBuilder();
        String urlApi = "http://" + ip + ":8080/api/spells";
        url = new URL(urlApi);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.connect();
        int respuesta = this.httpURLConnection.getResponseCode();

        if (respuesta == 200) {
            System.out.println("Conexión correcta");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()))) {
                String entrada;
                while ((entrada = in.readLine()) != null) {
                    json.append(entrada);
                }
            }

        } else {
            System.out.println("Error " + respuesta);
        }
        return json.toString();
    }

    private void takeLastSpell(String spellList) throws JSONException {
        JSONArray list = new JSONArray(spellList);

        JSONObject spell = list.getJSONObject(list.length() - 1);
        Long spellId = spell.getLong("id");
        spellObject.put("id", spellId);
    }
}
    
