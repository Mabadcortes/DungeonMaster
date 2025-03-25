package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectointegrado.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Controllers.ApiController;
import adapter.SpinnerAdapter;
import data.SpinnerData;

public class CreateCharacter_Activity extends AppCompatActivity {

    AutoCompleteTextView equipment;
    AutoCompleteTextView spell;
    EditText characterName;
    Spinner characterClass;
    Spinner characterRace;
    Spinner characterBackground;
    Spinner characterAlignment;
    Spinner characterSkill;
    Button nextButton;
    TextView skillPointsRemaining;
    TextView strength;
    TextView dexterity;
    TextView constitution;
    TextView intelligence;
    TextView wisdom;
    TextView charisma;
    Button minusStrength;
    Button minusDexterity;
    Button minusConstitution;
    Button minusIntelligence;
    Button minusWisdom;
    Button minusCharisma;
    Button plusStrength;
    Button plusDexterity;
    Button plusConstitution;
    Button plusIntelligence;
    Button plusWisdom;
    Button plusCharisma;
    ImageButton back;
    Button next2;
    RelativeLayout firstLayout;
    RelativeLayout secondLayout;

    private String name;
    private String classCharacter;
    private String race;
    private String background;
    private String alignment;
    private String skill;
    private int characterStrength;
    private int tempStr;
    private int tempDex;
    private int tempWis;
    private int tempCons;
    private int tempInt;
    private int tempChar;
    private int tempSkillPoints;
    private int characterDexterity;
    private int characterConstitution;
    private int characterIntelligence;
    private int characterWisdom;
    private int characterCharisma;
    private String username;
    RelativeLayout thirdLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        username = getIntent().getStringExtra("username");
        characterName = findViewById(R.id.etName);
        characterClass = findViewById(R.id.spinnerClass);
        characterRace = findViewById(R.id.spinnerRace);
        characterBackground = findViewById(R.id.spinnerBackground);
        characterAlignment = findViewById(R.id.spinnerAlignment);
        characterSkill = findViewById(R.id.spinnerSkill);
        nextButton = findViewById(R.id.next);
        skillPointsRemaining = findViewById(R.id.skillPointRemaining);
        strength = findViewById(R.id.strength);
        dexterity = findViewById(R.id.dexterity);
        constitution = findViewById(R.id.constitution);
        intelligence = findViewById(R.id.intelligence);
        wisdom = findViewById(R.id.wisdom);
        charisma = findViewById(R.id.charisma);
        minusStrength = findViewById(R.id.minusStrength);
        minusDexterity = findViewById(R.id.minusDexterity);
        minusConstitution = findViewById(R.id.minusConstitution);
        minusIntelligence = findViewById(R.id.minusIntelligence);
        minusWisdom = findViewById(R.id.minusWisdom);
        minusCharisma = findViewById(R.id.minusCharisma);
        plusStrength = findViewById(R.id.plusStrength);
        plusDexterity = findViewById(R.id.plusDexterity);
        plusConstitution = findViewById(R.id.plusConstitution);
        plusIntelligence = findViewById(R.id.plusIntelligence);
        plusWisdom = findViewById(R.id.plusWisdom);
        plusCharisma = findViewById(R.id.plusCharisma);
        back = findViewById(R.id.back);
        next2 = findViewById(R.id.next2);
        firstLayout = findViewById(R.id.firstLayout);
        secondLayout = findViewById(R.id.secondLayout);
        Button reset = findViewById(R.id.reset);
        equipment = findViewById(R.id.etEquipment);
        thirdLayout = findViewById(R.id.thirdLayout);
        Button create = findViewById(R.id.create);
        spell = findViewById(R.id.etSpell);

        tempStr = 8;
        tempDex = 8;
        tempChar = 8;
        tempInt = 8;
        tempCons = 8;
        tempWis = 8;
        characterStrength = 8;
        characterDexterity = 8;
        characterCharisma = 8;
        characterWisdom = 8;
        characterIntelligence = 8;
        characterConstitution = 8;
        tempSkillPoints = 27;


        ArrayList<String> equipmentList = null;
        ArrayList<String> spellList = null;
        try {
            equipmentList = fillObjectList("equipment");
            spellList = fillObjectList("spells");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        ArrayAdapter<String> equipmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equipmentList);
        equipment.setAdapter(equipmentAdapter);
        ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spellList);
        spell.setAdapter(spellAdapter);

        fillCharacterClassSpinner();
        fillRaceClassSpinner();
        fillBackgroundSpinner();
        fillAlignmentSpinner();
        fillSkillSpinner();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = characterName.getText().toString();
                if (!name.isEmpty() && classCharacter != null && race != null && background != null && alignment != null && skill != null) {
                    firstLayout.setVisibility(View.GONE);
                    secondLayout.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(CreateCharacter_Activity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempStr = 8;
                tempDex = 8;
                tempChar = 8;
                tempInt = 8;
                tempCons = 8;
                tempWis = 8;
                characterStrength = 8;
                characterDexterity = 8;
                characterCharisma = 8;
                characterWisdom = 8;
                characterIntelligence = 8;
                characterConstitution = 8;
                tempSkillPoints = 27;

                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
                strength.setText(String.valueOf(tempStr));
                dexterity.setText(String.valueOf(tempDex));
                charisma.setText(String.valueOf(tempChar));
                intelligence.setText(String.valueOf(tempInt));
                constitution.setText(String.valueOf(tempCons));
                wisdom.setText(String.valueOf(tempWis));
                Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                Drawable drawable1 = getResources().getDrawable(R.drawable.button_style);
                minusStrength.setBackground(drawable);
                minusDexterity.setBackground(drawable);
                minusWisdom.setBackground(drawable);
                minusConstitution.setBackground(drawable);
                minusCharisma.setBackground(drawable);
                minusIntelligence.setBackground(drawable);

                plusStrength.setBackground(drawable1);
                plusDexterity.setBackground(drawable1);
                plusWisdom.setBackground(drawable1);
                plusIntelligence.setBackground(drawable1);
                plusConstitution.setBackground(drawable1);
                plusCharisma.setBackground(drawable1);
            }
        });

        minusStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempStr > 8) {
                    tempStr--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }

                if (tempStr == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusStrength.setBackground(drawable);
                }

                if (tempStr < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusStrength.setBackground(drawable);
                }
                characterStrength = tempStr;
                strength.setText(String.valueOf(characterStrength));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempSkillPoints > 0) {
                    if (tempStr < 16) {
                        tempStr++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempStr == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusStrength.setBackground(drawable);
                }

                if (tempStr > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusStrength.setBackground(drawable);
                }
                characterStrength = tempStr;
                strength.setText(String.valueOf(characterStrength));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        minusDexterity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempDex > 8) {
                    tempDex--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }

                if (tempDex == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusDexterity.setBackground(drawable);
                }

                if (tempDex < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusDexterity.setBackground(drawable);
                }
                characterDexterity = tempDex;
                dexterity.setText(String.valueOf(characterDexterity));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusDexterity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempSkillPoints > 0) {
                    if (tempDex < 16) {
                        tempDex++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempDex == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusDexterity.setBackground(drawable);
                }

                if (tempDex > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusDexterity.setBackground(drawable);
                }
                characterDexterity = tempDex;
                dexterity.setText(String.valueOf(characterDexterity));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        minusConstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempCons > 8) {
                    tempCons--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }
                if (tempCons == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusConstitution.setBackground(drawable);
                }

                if (tempCons < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusConstitution.setBackground(drawable);
                }
                characterConstitution = tempCons;
                constitution.setText(String.valueOf(characterConstitution));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusConstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempSkillPoints > 0) {
                    if (tempCons < 16) {
                        tempCons++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempCons == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusConstitution.setBackground(drawable);
                }

                if (tempCons > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusConstitution.setBackground(drawable);
                }
                characterConstitution = tempCons;
                constitution.setText(String.valueOf(characterConstitution));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        minusIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempInt > 8) {
                    tempInt--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }
                if (tempInt == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusIntelligence.setBackground(drawable);
                }

                if (tempInt < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusIntelligence.setBackground(drawable);
                }
                characterIntelligence = tempInt;
                intelligence.setText(String.valueOf(characterIntelligence));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempSkillPoints > 0) {
                    if (tempInt < 16) {
                        tempInt++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempInt == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusIntelligence.setBackground(drawable);
                }

                if (tempInt > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusIntelligence.setBackground(drawable);
                }
                characterIntelligence = tempInt;
                intelligence.setText(String.valueOf(characterIntelligence));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });


        minusWisdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempWis > 8) {
                    tempWis--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }
                if (tempWis == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusWisdom.setBackground(drawable);
                }

                if (tempWis < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusWisdom.setBackground(drawable);
                }
                characterWisdom = tempWis;
                wisdom.setText(String.valueOf(characterWisdom));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusWisdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempSkillPoints > 0) {
                    if (tempWis < 16) {
                        tempWis++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempWis == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusWisdom.setBackground(drawable);
                }

                if (tempWis > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusWisdom.setBackground(drawable);
                }
                characterWisdom = tempWis;
                wisdom.setText(String.valueOf(characterWisdom));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        minusCharisma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempChar > 8) {
                    tempChar--;
                    tempSkillPoints++;
                }
                if (tempSkillPoints > 27) {
                    tempSkillPoints = 27;
                }
                if (tempChar == 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    minusCharisma.setBackground(drawable);
                }

                if (tempChar < 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    plusCharisma.setBackground(drawable);
                }
                characterCharisma = tempChar;
                charisma.setText(String.valueOf(characterCharisma));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        plusCharisma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempSkillPoints > 0) {
                    if (tempChar < 16) {
                        tempChar++;
                        tempSkillPoints--;
                    }
                }
                if (tempSkillPoints < 0) {
                    tempSkillPoints = 0;
                }
                if (tempChar == 16) {
                    Drawable drawable = getResources().getDrawable(R.drawable.inactive_button);
                    plusCharisma.setBackground(drawable);
                }

                if (tempChar > 8) {
                    Drawable drawable = getResources().getDrawable(R.drawable.button_style);
                    minusCharisma.setBackground(drawable);
                }
                characterCharisma = tempChar;
                charisma.setText(String.valueOf(characterCharisma));
                skillPointsRemaining.setText(String.valueOf(tempSkillPoints));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thirdLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!equipment.getText().toString().isEmpty() && !spell.getText().toString().isEmpty()) {
                        createCharacter();
                    } else {
                        Toast.makeText(CreateCharacter_Activity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        characterClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classCharacter = ((SpinnerData) parent.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        characterRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                race = ((SpinnerData) parent.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        characterBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                background = ((SpinnerData) parent.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        characterAlignment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alignment = ((SpinnerData) parent.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        characterSkill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skill = ((SpinnerData) parent.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillCharacterClassSpinner() {
        ArrayList<SpinnerData> data = new ArrayList<>();
        SpinnerAdapter adapter = new SpinnerAdapter(this, data);

        data.add(new SpinnerData(R.drawable.wizard_icon, "Wizard"));
        data.add(new SpinnerData(R.drawable.barbarian_icon, "Barbarian"));
        data.add(new SpinnerData(R.drawable.rogue_icon, "Rogue"));
        data.add(new SpinnerData(R.drawable.ranger_icon, "Ranger"));
        characterClass.setAdapter(adapter);

    }

    private void fillRaceClassSpinner() {
        ArrayList<SpinnerData> data = new ArrayList<>();
        SpinnerAdapter adapter = new SpinnerAdapter(this, data);

        data.add(new SpinnerData(R.drawable.elf_icon, "Elf"));
        data.add(new SpinnerData(R.drawable.human_icon, "Human"));
        data.add(new SpinnerData(R.drawable.tiefling_icon, "Tiefling"));
        data.add(new SpinnerData(R.drawable.dwarf_icon, "Dwarf"));
        characterRace.setAdapter(adapter);
    }

    private void fillBackgroundSpinner() {
        ArrayList<SpinnerData> data = new ArrayList<>();
        SpinnerAdapter adapter = new SpinnerAdapter(this, data);

        data.add(new SpinnerData(R.drawable.criminal_icon, "Criminal"));
        data.add(new SpinnerData(R.drawable.noble_icon, "Noble"));
        data.add(new SpinnerData(R.drawable.soldier_icon, "Soldier"));
        data.add(new SpinnerData(R.drawable.sage_icon, "Sage"));
        data.add(new SpinnerData(R.drawable.folk_hero_icon, "Folk hero"));
        data.add(new SpinnerData(R.drawable.acolyte_icon, "Acolyte"));
        characterBackground.setAdapter(adapter);
    }

    private void fillAlignmentSpinner() {

        ArrayList<SpinnerData> data = new ArrayList<>();
        SpinnerAdapter adapter = new SpinnerAdapter(this, data);

        data.add(new SpinnerData("Lawful good"));
        data.add(new SpinnerData("Neutral good"));
        data.add(new SpinnerData("Chaotic good"));
        data.add(new SpinnerData("Lawful neutral"));
        data.add(new SpinnerData("Neutral"));
        data.add(new SpinnerData("Chaotic Neutral"));
        data.add(new SpinnerData("Lawful evil"));
        data.add(new SpinnerData("Neutral evil"));
        data.add(new SpinnerData("Chaotic evil"));
        characterAlignment.setAdapter(adapter);
    }

    private void fillSkillSpinner() {

        ArrayList<SpinnerData> data = new ArrayList<>();
        SpinnerAdapter adapter = new SpinnerAdapter(this, data);

        data.add(new SpinnerData("Acrobatics"));
        data.add(new SpinnerData("Animal Handling"));
        data.add(new SpinnerData("Arcana"));
        data.add(new SpinnerData("Athletics"));
        data.add(new SpinnerData("Deception"));
        data.add(new SpinnerData("History"));
        data.add(new SpinnerData("Insight"));
        data.add(new SpinnerData("Intimidation"));
        data.add(new SpinnerData("Investigation"));
        characterSkill.setAdapter(adapter);
    }

    private void createCharacter() throws JSONException, IOException {
        ApiController apiController = new ApiController();
        String equipmentIndex = equipment.getText().toString();
        String spellIndex = spell.getText().toString();
        JSONObject equipment = new JSONObject(apiController.getObject("equipment", equipmentIndex));
        JSONObject spell = new JSONObject(apiController.getObject("spells", spellIndex));
        JSONObject usuario = new JSONObject(apiController.getUserByUsername(username));
        int experiencePoints = 0;
        int armorClass = 10;
        int speed = 5;
        int initiative = 12;
        int hitPoints = 20;
        int level = 1;
        apiController.crearCharacter(name, classCharacter, level, race, background, alignment, skill, characterStrength, characterDexterity, characterConstitution, characterIntelligence,
                characterWisdom, characterCharisma, experiencePoints, armorClass, initiative, speed, hitPoints, usuario, equipment, spell);

        Intent intent = new Intent(CreateCharacter_Activity.this, Main_Activity.class);
        startActivity(intent);
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


}