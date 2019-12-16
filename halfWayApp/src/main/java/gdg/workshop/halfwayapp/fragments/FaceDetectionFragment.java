package gdg.workshop.halfwayapp.fragments;

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

import gdg.workshop.mysmartapp.R;
import gdg.workshop.halfwayapp.graphics.FaceGraphic;
import gdg.workshop.halfwayapp.graphics.GraphicOverlay;

/**
 * the FaceDetectionFragment is a real time face detection and classification fragment.
 * it draw rectangle around the detected face and classify whether the person is smiling,
 * closing one eye or not
 */
public class FaceDetectionFragment extends VisionFragment<List<FirebaseVisionFace>> {

    public static final String TAG  = "TextRecognitionFragment";
    private CameraView mCameraView;
    private GraphicOverlay mGraphicOverlay;
    private ImageView mEmojiImageView; // an image view that express the face expression


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

        /**
         * switching between the front and the back camera
         **/
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

        // TODO: define the options for the Firebase face detector to include: contours detection and face classification


        // TODO: get instance od the Firebase Vision face detector


        //TODO: start the face detection task and handle both success and failure results
        return null;

    }


    /**
     * this methode get the detected face then draw a rectangle over it
     * and get the expression in the picture
     *
     * @param faces a list of {@link FirebaseVisionFace} that should contain 1 or 0 faces
     */
    private void processFace(List<FirebaseVisionFace> faces){


        // TODO: clean the drawing surface

        // TODO: get the detected face

            //TODO: draw a frame over the detected face

            //TODO: set the expression emoji



    }



}
