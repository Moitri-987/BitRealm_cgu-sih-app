package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VitalSignsResults extends AppCompatActivity {
    private Button button;
    private String user, Date;
    public String result;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    public int VBP1, VBP2, VRR, VHR, VO2,VBPL1;
    public String rr;
    String url="https://sih-cgu-app.herokuapp.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_results);

        Date = df.format(today);
        TextView VSRR = this.findViewById(R.id.RRV);
        TextView VSBPS = this.findViewById(R.id.BP2V);
        TextView VSHR = this.findViewById(R.id.HRV);
        TextView VSO2 = this.findViewById(R.id.O2V);
        TextView VBPL = this.findViewById(R.id.BP2V);
        ImageButton All = this.findViewById(R.id.SendAll);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VRR = bundle.getInt("breath");
            VHR = bundle.getInt("bpm");
            VBP1 = bundle.getInt("SP");
            VBP2 = bundle.getInt("DP");
            VO2 = bundle.getInt("O2R");
            VBPL1 = bundle.getInt("SP");
            user = bundle.getString("Usr");

            VSRR.setText(String.valueOf(VRR));
            VSHR.setText(String.valueOf(VHR));
            VSBPS.setText(VBP1 + " / " + VBP2);
            VSO2.setText(String.valueOf(VO2));
            VBPL.setText(String.valueOf(VBPL1));
        }

        All.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
            i.putExtra(Intent.EXTRA_TEXT, user + "'s new measuerment " + "\n" + " at " + Date + " are :" + "\n" + "Heart Rate = " + VHR + "\n" + "Blood Pressure = " + VBP1 + " / " + VBP2 + "\n" + "Respiration Rate = " + VRR + "\n" + "Oxygen Saturation = " + VO2);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(VitalSignsResults.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///   //  /// // /// ///////////////////////////////////////
                // hit the API -> Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("predict");
                                    if(data=="0")
                                        result="Not_Fine";
                                    else{
                                        result="Fine";
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(VitalSignsResults.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    public Map getParams(){
                        Map params = new HashMap();
                        params.put("HeartRate",VSHR.getText().toString());
                        params.put("RespirationRate",VSRR.getText().toString());
                        params.put("BloodPressure",VBPL.getText().toString());
                        params.put("SpO2",VSO2.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(VitalSignsResults.this);
                queue.add(stringRequest);
                ////////////////////
                openml_result();

            }
        });

    }

    private void openml_result() {
        Intent intent=new Intent(VitalSignsResults.this, ml_result.class);
        intent.putExtra("Result",result);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(VitalSignsResults.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
    }
}
