package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectointegrado.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controllers.ApiController;
import adapter.ListViewAdapter;
import data.ListViewData;

public class Main_Activity extends AppCompatActivity {

    private List<ListViewData> listaCharacter;
    private String username;
    private JSONArray characterList;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Button btnSignOut = findViewById(R.id.btnLogOut);
        listaCharacter = new ArrayList<>();
        username = getIntent().getStringExtra("username");
        list = findViewById(R.id.characterList);
        try {
            fillCharacterList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        ListViewAdapter adapter = new ListViewAdapter(this, listaCharacter);
        list.setAdapter(adapter);
        list.refreshDrawableState();


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewData character = (ListViewData) parent.getAdapter().getItem(position);
                Long charId = character.getId();
                Intent intent = new Intent(Main_Activity.this, CharacterView.class);
                intent.putExtra("id", charId);
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        logoutMenu(Main_Activity.this);
    }

    private void logoutMenu(Main_Activity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Main_Activity.this, Login_Activity.class);
                Handler handler = new Handler();
                Toast.makeText(mainActivity, "Closing session...", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void fillCharacterList() throws IOException, JSONException {
        ApiController apiController = new ApiController();

        JSONObject usuario = new JSONObject(apiController.getUserByUsername(username));
        characterList = usuario.getJSONArray("characterList");

        if (characterList != null) {
            for (int i = 0; i < characterList.length(); i++) {
                ListViewData datos = new ListViewData();
                JSONObject user = characterList.getJSONObject(i);
                String name = user.getString("characterName");
                String characterClass = user.getString("characterClass");
                int level = user.getInt("characterLevel");
                String race = user.getString("characterRace");
                Long id = user.getLong("id");

                datos.setId(id);
                datos.setName(name);
                datos.setCharacterClass(characterClass);
                datos.setLevel(level);
                datos.setRace(race);

                this.listaCharacter.add(datos);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();

        if (opcion == R.id.addCharacter) {
            Intent intent = new Intent(Main_Activity.this, CreateCharacter_Activity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        if (opcion == R.id.logout) {
            Intent intent = new Intent(Main_Activity.this, Login_Activity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            listaCharacter = new ArrayList<>();
            fillCharacterList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}