package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectointegrado.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Controllers.ApiController;

public class EditCharacter extends AppCompatActivity {

    private JSONObject character;
    private JSONObject spell;
    private JSONObject equipment;
    private Long characterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);

        characterId = getIntent().getLongExtra("id", 0);
        EditText characterLevel = findViewById(R.id.characterLevel);
        EditText characterExp = findViewById(R.id.characterExp);
        AutoCompleteTextView etEquipment = findViewById(R.id.etEquipment);
        AutoCompleteTextView etSpell = findViewById(R.id.etSpell);
        Button update = findViewById(R.id.update);
        ArrayList<String> equipmentList = null;
        ArrayList<String> spellList = null;


        try {
            getCharacter();
            equipmentList = fillObjectList("equipment");
            spellList = fillObjectList("spells");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ArrayAdapter<String> equipmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equipmentList);
        etEquipment.setAdapter(equipmentAdapter);
        ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spellList);
        etSpell.setAdapter(spellAdapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean modify = false;
                String levelString = (characterLevel.getText().toString());
                String expString = (characterExp.getText().toString());
                String equipmentIndex = etEquipment.getText().toString();
                String spellIndex = etSpell.getText().toString();

                if (!levelString.isEmpty() || !expString.isEmpty() || !equipmentIndex.isEmpty() || !spellIndex.isEmpty()) {
                    Intent intent = new Intent(EditCharacter.this, Main_Activity.class);
                    ApiController apiController = new ApiController();

                    try {
                        if (!levelString.isEmpty()) {
                            int level = Integer.parseInt(levelString);
                            character.put("characterLevel", level);
                            modify = true;
                        }

                        if (!expString.isEmpty()) {
                            int exp = Integer.parseInt(expString);
                            character.put("experiencePoints", exp);
                            modify = true;
                        }

                        if (!equipmentIndex.isEmpty()) {
                            equipment = new JSONObject(apiController.getObject("equipment", equipmentIndex));
                            apiController.createEquipment(equipment);
                            takeLastEquipment();
                            JSONArray newEquipmentList = character.getJSONArray("equipmentList");
                            newEquipmentList.put(equipment);
                            character.put("equipmentList", newEquipmentList);
                            modify = true;
                        }
                        if (!spellIndex.isEmpty()) {
                            spell = new JSONObject(apiController.getObject("spells", spellIndex));
                            apiController.createSpell(spell);
                            takeLastSpell();
                            JSONArray newSpellList = character.getJSONArray("spellList");
                            newSpellList.put(spell);
                            character.put("spellList", newSpellList);
                            modify = true;
                        }

                        if (modify) {
                            apiController.updateCharacter(characterId, character);
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    startActivity(intent);
                } else {
                    Toast.makeText(EditCharacter.this, "Please fill at least one field", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private ArrayList<String> fillObjectList(String url) throws IOException, JSONException {
        ApiController apiController = new ApiController();
        JSONObject object = new JSONObject(apiController.getAllEquipmentSpellOrMonster(url));
        JSONArray objectList = object.getJSONArray("results");
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < objectList.length(); i++) {
            JSONObject itemObject = objectList.getJSONObject(i);
            list.add(itemObject.getString("index"));
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    private void getCharacter() throws IOException, JSONException {
        ApiController apiController = new ApiController();
        character = new JSONObject(apiController.getCharacterById(characterId));

    }

    private void takeLastSpell() throws IOException, JSONException {
        ApiController apiController = new ApiController();
        JSONArray list = new JSONArray(apiController.getAllSpells());

        JSONObject spellObject = list.getJSONObject(list.length() - 1);
        Long spellId = spellObject.getLong("id");
        spell.put("id", spellId);
    }

    private void takeLastEquipment() throws IOException, JSONException {
        ApiController apiController = new ApiController();
        JSONArray list = new JSONArray(apiController.getAllEquipment());

        JSONObject equipmentObject = list.getJSONObject(list.length() - 1);
        Long equipmentId = equipmentObject.getLong("id");
        equipment.put("id", equipmentId);
    }
}