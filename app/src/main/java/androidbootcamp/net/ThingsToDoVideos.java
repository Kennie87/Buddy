package androidbootcamp.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class ThingsToDoVideos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do_videos);

        VideoView videoView1 = findViewById(R.id.vv1);
        VideoView videoView2 = findViewById(R.id.vv2);
        VideoView videoView3 = findViewById(R.id.vv3);

        videoView1.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.vid1);
        videoView2.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.vid2);
        videoView3.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.vid3);

        MediaController mediaController1 = new MediaController(this);
        mediaController1.setAnchorView(videoView1);
        MediaController mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(videoView2);
        MediaController mediaController3 = new MediaController(this);
        mediaController3.setAnchorView(videoView3);

        videoView1.setMediaController(mediaController1);
        videoView2.setMediaController(mediaController2);
        videoView3.setMediaController(mediaController3);
    }
}