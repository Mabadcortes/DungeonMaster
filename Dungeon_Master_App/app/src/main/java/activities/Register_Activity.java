package activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectointegrado.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controllers.ApiController;

public class Register_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        EditText usernameUser = findViewById(R.id.user);
        EditText emailUser = findViewById(R.id.email);
        EditText passwdUser = findViewById(R.id.passwd);
        EditText confirmPwd = findViewById(R.id.confirmPasswd);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameUser.getText().toString().trim();
                String email = emailUser.getText().toString().trim();
                String password = passwdUser.getText().toString().trim();
                String confirmPassword = confirmPwd.getText().toString().trim();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    try {
                        if (isEmailValid(email)) {

                            if (password.length() >= 6 && password.equalsIgnoreCase(confirmPassword)) {
                                if (!usernameExist(username)) {
                                    if (!emailExist(email)) {
                                        try {
                                            createUser(username, email, password);
                                            Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                                            startActivity(intent);
                                        } catch (IOException | JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    } else {
                                        Toast.makeText(Register_Activity.this, "ERROR: The email exists", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Register_Activity.this, "ERROR: The username exists", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Register_Activity.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Register_Activity.this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(Register_Activity.this, "Please fill in all fields. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean usernameExist(String username) throws IOException, JSONException {
        ApiController apiController = new ApiController();
        boolean exists = false;
        String user = apiController.getUserByUsername(username);
        if (!user.isEmpty()) {
            exists = true;
        }

        return exists;
    }

    private boolean emailExist(String email) throws IOException, JSONException {
        ApiController apiController = new ApiController();
        boolean exists = false;
        String user = apiController.getUserByEmail(email);
        if (!user.isEmpty()) {
            exists = true;
        }
        return exists;
    }

    private void createUser(String username, String email, String password) throws IOException, JSONException {
        ApiController apiController = new ApiController();
        apiController.crearUsuario(username, email, password);
    }


}
