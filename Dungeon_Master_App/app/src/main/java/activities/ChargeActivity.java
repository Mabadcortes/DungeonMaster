package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.proyectointegrado.R;

public class ChargeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        String username = getIntent().getStringExtra("username");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ChargeActivity.this, Main_Activity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        }, 1500);
    }
}