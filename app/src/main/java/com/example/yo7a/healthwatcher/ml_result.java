package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ml_result extends AppCompatActivity {
    public String VBP1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_result);
        //////////////////////////////////////////////////
        TextView Resulttext =this.findViewById(R.id.Result);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VBP1 = bundle.getString("Result");
            Resulttext.setText(String.valueOf(VBP1));
        }
    }
}