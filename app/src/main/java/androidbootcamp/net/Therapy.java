// Kenneth Johnson
// 09/26/2022

package androidbootcamp.net;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.media.MediaPlayer;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapy extends AppCompatActivity {
    Button btnPlay;
    MediaPlayer mMeditation;
    int playing;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Button button = (Button) findViewById(R.id.btnTTD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Therapy.this, ThingsToDoVideos.class));
            }
        });


        Button.OnClickListener btnMusic = new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (playing){
                    case 0:
                        mMeditation.start();
                        playing = 1;
                        btnPlay.setText("Meditation Pause");
                        break;
                    case 1:
                        mMeditation.pause();
                        playing = 0;
                        btnPlay.setText("Continue Meditation");
                        break;
                }
            }
        };

        btnPlay = findViewById(R.id.btnMusic);
        btnPlay.setOnClickListener(btnMusic);
        mMeditation = new MediaPlayer();
        mMeditation = MediaPlayer.create(this, R.raw.meditation);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.therapy);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(),Stats.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.therapy:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}