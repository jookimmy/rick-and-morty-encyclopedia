package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Button btn = findViewById(R.id.alive_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatusActivity.this, AliveStatus.class));
            }
        });

        Button btn2 = findViewById(R.id.dead_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                startActivity(new Intent(StatusActivity.this, DeadStatus.class));
            }
        });

        Button btn3 = findViewById(R.id.notKnown_button);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                startActivity(new Intent(StatusActivity.this, UnknownStatus.class));
            }
        });
    }

}
