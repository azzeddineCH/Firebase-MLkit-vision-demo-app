package gdg.workshop.halfwayapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import gdg.workshop.halfwayapp.fragments.FaceDetectionFragment;
import gdg.workshop.halfwayapp.fragments.TextRecognitionFragment;
import gdg.workshop.halfwayapp.fragments.VisionFragment;
import gdg.workshop.mysmartapp.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private VisionFragment fragment = new FaceDetectionFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();





    }


}
