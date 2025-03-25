package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.proyectointegrado.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import Controllers.ApiController;
import adapter.EquipmentListAdapter;
import adapter.SpellListAdapter;
import data.EquipmentListData;
import data.SpellListData;

public class CharacterView extends AppCompatActivity {

    private Long characterId;
    private JSONObject character;
    private ArrayList<EquipmentListData> equipmentListData;
    private ArrayList<SpellListData> spellListData;
    private JSONArray equipmentList;
    private JSONArray spellList;
    private TextView characterName;
    private TextView characterLevel;
    private TextView characterClass;
    private TextView characterRace;
    private TextView characterBackground;
    private TextView characterAlignment;
    private TextView characterExperiencePoints;
    private TextView characterStr;
    private TextView characterDex;
    private TextView characterCons;
    private TextView characterInt;
    private TextView characterWis;
    private TextView characterChar;
    private TextView hitPoints;
    private TextView armorClass;
    private TextView initiative;
    private ListView listEquipment;
    private ListView listSpell;
    private ApiController apiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_view);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button nextButton = findViewById(R.id.nextButton);
        Button backButton = findViewById(R.id.backButton);
        RelativeLayout firstLayout = findViewById(R.id.firstLayout);
        RelativeLayout secondLayout = findViewById(R.id.secondLayout);
        characterId = getIntent().getLongExtra("id", 0);
        characterName = findViewById(R.id.characterName);
        characterLevel = findViewById(R.id.characterLevel);
        characterClass = findViewById(R.id.characterClass);
        characterRace = findViewById(R.id.characterRace);
        characterBackground = findViewById(R.id.characterBackground);
        characterAlignment = findViewById(R.id.characterAlignment);
        characterExperiencePoints = findViewById(R.id.characterExperience);
        characterStr = findViewById(R.id.characterStr);
        characterDex = findViewById(R.id.characterDex);
        characterCons = findViewById(R.id.characterCons);
        characterInt = findViewById(R.id.characterInt);
        characterWis = findViewById(R.id.characterWis);
        characterChar = findViewById(R.id.characterChar);
        hitPoints = findViewById(R.id.hitPoints);
        armorClass = findViewById(R.id.armorClass);
        initiative = findViewById(R.id.initiative);
        listEquipment = findViewById(R.id.listEquipment);
        listSpell = findViewById(R.id.listSpell);
        equipmentListData = new ArrayList<>();
        spellListData = new ArrayList<>();
        apiController = new ApiController();
        try {
            character = new JSONObject(apiController.getCharacterById(characterId));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            getCharacter();
            fillEquipmentList();
            fillSpellList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        EquipmentListAdapter equipmentListAdapter = new EquipmentListAdapter(this, equipmentListData);
        listEquipment.setAdapter(equipmentListAdapter);
        SpellListAdapter spellListAdapter = new SpellListAdapter(this, spellListData);
        listSpell.setAdapter(spellListAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLayout.setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondLayout.setVisibility(View.GONE);
                firstLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getCharacter() throws IOException, JSONException {

        characterName.setText(character.getString("characterName"));
        characterLevel.setText(String.valueOf(character.getInt("characterLevel")));
        characterClass.setText(character.getString("characterClass"));
        characterRace.setText(character.getString("characterRace"));
        characterBackground.setText(character.getString("characterBackground"));
        characterAlignment.setText(character.getString("characterAlignment"));
        characterExperiencePoints.setText(String.valueOf(character.getInt("experiencePoints")));
        characterExperiencePoints.append(" EXP");
        characterStr.setText(String.valueOf(character.getInt("strength")));
        characterDex.setText(String.valueOf(character.getInt("dexterity")));
        characterCons.setText(String.valueOf(character.getInt("constitution")));
        characterInt.setText(String.valueOf(character.getInt("intelligence")));
        characterWis.setText(String.valueOf(character.getInt("wisdom")));
        characterChar.setText(String.valueOf(character.getInt("charisma")));
        hitPoints.setText(String.valueOf(character.getInt("hitPoints")));
        armorClass.setText(String.valueOf(character.getInt("armorClass")));
        initiative.setText(String.valueOf(character.getInt("initiative")));
    }

    private void fillEquipmentList() throws JSONException {
        equipmentList = character.getJSONArray("equipmentList");

        if (equipmentList != null) {
            for (int i = 0; i < equipmentList.length(); i++) {
                EquipmentListData data = new EquipmentListData();
                JSONObject equipment = equipmentList.getJSONObject(i);

                Long id = equipment.getLong("id");
                String name = equipment.getString("name");
                String equipmentCategoryName = "";
                String gearCategoryName = "";
                if (equipment.has("equipmentCategoryName")) {
                    equipmentCategoryName = equipment.getString("equipmentCategoryName");
                }
                if (equipment.has("gearCategoryName")) {
                    gearCategoryName = equipment.getString("gearCategoryName");
                }
                int quantity = equipment.getInt("quantity");
                int weight = equipment.getInt("weight");
                String unit = equipment.getString("costUnit");

                data.setId(id);
                data.setName(name);
                data.setUnit(unit);
                data.setWeight(weight);
                data.setQuantity(quantity);
                data.setGearCategoryName(gearCategoryName);
                data.setEquipmentCategoryName(equipmentCategoryName);

                this.equipmentListData.add(data);
            }
        }
    }

    private void fillSpellList() throws JSONException {
        spellList = character.getJSONArray("spellList");

        if (spellList != null) {
            for (int i = 0; i < spellList.length(); i++) {
                SpellListData data = new SpellListData();
                JSONObject spell = spellList.getJSONObject(i);

                String name = spell.getString("name");
                String range = "";
                String castingType = "";
                String schoolName = "";
                String description = "";
                if (spell.has("range")) {
                    range = spell.getString("range");
                }
                if (spell.has("castingTime")) {
                    castingType = spell.getString("castingTime");
                }
                if (spell.has("schoolName")) {
                    schoolName = spell.getString("schoolName");
                }
                if (spell.has("description")) {
                    description = spell.getString("description");
                }

                data.setName(name);
                data.setRange(range);
                data.setCastingType(castingType);
                data.setSchool(schoolName);
                data.setDescription(description);

                this.spellListData.add(data);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.character_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();

        if (opcion == R.id.editCharacter) {
            Intent intent = new Intent(CharacterView.this, EditCharacter.class);
            intent.putExtra("id", characterId);
            startActivity(intent);
        }
        if (opcion == R.id.deleteCharacter) {
            showPopUp();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete the character?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApiController apiController = new ApiController();
                Intent intent = new Intent(CharacterView.this, Main_Activity.class);
                try {
                    apiController.deleteCharacter(characterId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            equipmentListData = new ArrayList<>();
            spellListData = new ArrayList<>();
            character = new JSONObject(apiController.getCharacterById(characterId));
            getCharacter();
            fillSpellList();
            fillEquipmentList();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}