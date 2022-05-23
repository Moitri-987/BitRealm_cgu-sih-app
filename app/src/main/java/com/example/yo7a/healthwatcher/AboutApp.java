package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutApp extends AppCompatActivity {

    private String user;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = bundle.getString("Usr");
        }

        logout = findViewById(R.id.button2);

        logout.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), First.class);
            i.putExtra("Usr", user);
            startActivity(i);
            finish();
        });

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(AboutApp.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
