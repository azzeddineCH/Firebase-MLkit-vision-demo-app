package gdg.workshop.finalapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.otaliastudios.cameraview.CameraView;

import java.util.List;

import gdg.workshop.finalapp.graphics.GraphicOverlay;
import gdg.workshop.finalapp.graphics.TextGraphic;
import gdg.workshop.mysmartapp.R;

public class TextRecognitionFragment extends VisionFragment{

    public static final String TAG  = "TextRecognitionFragment";
    private CameraView mCameraView;
    private GraphicOverlay mGraphicOverlay;
    private TextView mTextContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text_recognition,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCameraView = view.findViewById(R.id.camera_view);
        mCameraView.setLifecycleOwner(this);
        mCameraView.addFrameProcessor(this);

        mGraphicOverlay = view.findViewById(R.id.graphic_overlay);
        mTextContainer = view.findViewById(R.id.text_container);

    }




    @Override
    public Task<FirebaseVisionText> analyze(FirebaseVisionImage image) {
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

       return detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                               processText(firebaseVisionText);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"ERROR: "+ e.getMessage());
                                    }
                                });

    }

    private void processText(FirebaseVisionText firebaseVisionText){
        StringBuilder text = new StringBuilder();

        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
        if(blocks.size() == 0)
            mTextContainer.setText("No Text Found");
        else {

            mGraphicOverlay.clear();


            for (FirebaseVisionText.TextBlock block:blocks){
                text.append(block.getText())
                    .append("\n");

                for(FirebaseVisionText.Line line :block.getLines()){
                    for(FirebaseVisionText.Element element:line.getElements()){
                        mGraphicOverlay.add(new TextGraphic(mGraphicOverlay,element));
                    }
                }
            }

            mTextContainer.setText(text.toString());
        }







    }
}
