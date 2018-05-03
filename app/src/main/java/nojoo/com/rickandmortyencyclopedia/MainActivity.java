package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MediaPlayer schwifty = MediaPlayer.create(MainActivity.this, R.raw.schwifty);
        schwifty.start();

        Button btn = findViewById(R.id.all_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllActivity.class));
            }
        });


        Button btn3 = findViewById(R.id.gender_button);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                startActivity(new Intent(MainActivity.this, GenderActivity.class));
            }
        });

        Button btn4 = findViewById(R.id.status_button);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                startActivity(new Intent(MainActivity.this, StatusActivity.class));
            }
        });

    }
}
