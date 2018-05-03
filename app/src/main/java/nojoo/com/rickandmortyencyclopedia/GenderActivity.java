package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        Button btn = findViewById(R.id.male_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GenderActivity.this, MaleGender.class));
                MediaPlayer schwifty = MediaPlayer.create(GenderActivity.this, R.raw.button);
                schwifty.start();
            }
        });

        Button btn2 = findViewById(R.id.female_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                startActivity(new Intent(GenderActivity.this, FemaleGender.class));
                MediaPlayer schwifty = MediaPlayer.create(GenderActivity.this, R.raw.button);
                schwifty.start();
            }
        });

        Button btn3 = findViewById(R.id.unknowng_button);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                startActivity(new Intent(GenderActivity.this, UnknownGender.class));
                MediaPlayer schwifty = MediaPlayer.create(GenderActivity.this, R.raw.button);
                schwifty.start();
            }
        });
    }
}
