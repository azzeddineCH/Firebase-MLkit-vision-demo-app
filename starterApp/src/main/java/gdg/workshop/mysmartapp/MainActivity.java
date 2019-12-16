package gdg.workshop.mysmartapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import gdg.workshop.mysmartapp.fragments.TextRecognitionFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /**
     * Instantiate the text recognition fragment
     */
    private TextRecognitionFragment fragment = new TextRecognitionFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Start the fragment transaction to display the fragment
         */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();





    }


}
