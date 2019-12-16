package gdg.workshop.finalapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.Facing;

import java.util.List;

import gdg.workshop.finalapp.graphics.FaceGraphic;
import gdg.workshop.finalapp.graphics.GraphicOverlay;
import gdg.workshop.mysmartapp.R;

public class FaceDetectionFragment extends VisionFragment<List<FirebaseVisionFace>> {

    public static final String TAG  = "TextRecognitionFragment";
    private CameraView mCameraView;
    private GraphicOverlay mGraphicOverlay;
    private ImageView mEmojiImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_face_detection,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCameraView = view.findViewById(R.id.camera_view);
        mCameraView.setLifecycleOwner(this);
        mCameraView.addFrameProcessor(this);

        mGraphicOverlay = view.findViewById(R.id.graphic_overlay);
        mEmojiImageView = view.findViewById(R.id.emoji_imageview);
        view.findViewById(R.id.flip_camera_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mCameraView.getFacing() == Facing.BACK)
                    mCameraView.setFacing(Facing.FRONT);
                else
                    mCameraView.setFacing(Facing.BACK);
            }
        });

    }





    public Task<List<FirebaseVisionFace>> analyze(FirebaseVisionImage image) {

        FirebaseVisionFaceDetectorOptions realTimeOpts = new FirebaseVisionFaceDetectorOptions.Builder()
                        .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
                         .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();

        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(realTimeOpts);

        return detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                        processFace(firebaseVisionFaces);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



    }

    private void processFace(List<FirebaseVisionFace> faces){

        mGraphicOverlay.clear();
        Glide.with(this)
                .load(R.drawable.ic_sad)
                .into(mEmojiImageView);

        if(faces.size()> 0){


            FirebaseVisionFace face = faces.get(0);

            FaceGraphic faceGraphic = new FaceGraphic(mGraphicOverlay);
            mGraphicOverlay.add(faceGraphic);
            faceGraphic.updateFace(face);



            if(face.getRightEyeOpenProbability() > 0.75f && face.getLeftEyeOpenProbability() < 0.1f
                        || face.getRightEyeOpenProbability() < 0.1f && face.getLeftEyeOpenProbability()  > 0.75f ) {

                Glide.with(this)
                        .load(R.drawable.ic_twink)
                        .into(mEmojiImageView);

                return;

            }

            if(face.getSmilingProbability() > 0.75f) {

                Glide.with(this)
                        .load(R.drawable.ic_smile)
                        .into(mEmojiImageView);

            }

        }




    }



}
