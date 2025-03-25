package activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.proyectointegrado.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;

import Controllers.ApiController;

public class Login_Activity extends AppCompatActivity {

    EditText emailUser;
    EditText passwdUser;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    TextView huella;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        emailUser = findViewById(R.id.email);
        passwdUser = findViewById(R.id.passwd);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView newUser = findViewById(R.id.newUser);
        huella = findViewById(R.id.huella);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prueba();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void login() throws JSONException {
        boolean encontrado = false;
        String emailLogin = emailUser.getText().toString().trim();
        String passwordLogin = passwdUser.getText().toString().trim();
        ApiController apiController = new ApiController();
        JSONArray listaUsuarios = null;
        try {
            listaUsuarios = new JSONArray(apiController.getAllUsers());
        } catch (JSONException e) {
            Toast.makeText(this, "ERROR: The user doesn´t exist", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        if (listaUsuarios != null) {
            for (int i = 0; i < listaUsuarios.length() && !encontrado; i++) {
                JSONObject usuario = listaUsuarios.getJSONObject(i);
                String emailUser = usuario.getString("email");
                String passwordlUser = usuario.getString("password");

                if (emailUser.equalsIgnoreCase(emailLogin)) {
                    if (passwordlUser.equalsIgnoreCase(passwordLogin)) {
                        encontrado = true;
                        Intent intent = new Intent(Login_Activity.this, ChargeActivity.class);
                        intent.putExtra("username", usuario.getString("username"));
                        startActivity(intent);
                    }
                }
            }
            if (!encontrado) {
                Toast.makeText(this, "ERROR: INVALID DATA", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void prueba() {
        // Crear un Executor
        Executor executor = ContextCompat.getMainExecutor(this);

        // Crear el manejador de callbacks para la autenticación
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                String username = "manu";
                Intent intent = new Intent(Login_Activity.this, ChargeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        };

        // Inicializar BiometricPrompt
        biometricPrompt = new BiometricPrompt(this, executor, callback);

        // Crear el prompt de autenticación
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Iniciar la autenticación
        biometricPrompt.authenticate(promptInfo);
    }
}