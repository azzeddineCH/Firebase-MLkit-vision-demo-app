package gdg.workshop.mysmartapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.List;
import java.util.concurrent.ExecutionException;

import gdg.workshop.mysmartapp.R;
import gdg.workshop.mysmartapp.graphics.GraphicOverlay;
import gdg.workshop.mysmartapp.graphics.TextGraphic;

/**
 * the TextRecognitionFragment is a real time text recognition fragment.
 * it draw rectangle around each detected element and display the text on a text area below
 */
public class TextRecognitionFragment extends Fragment implements FrameProcessor {

    public static final String TAG = "TextRecognitionFragment";

    private CameraView mCameraView; // a view that display the camera view used fot scanning
    private GraphicOverlay mGraphicOverlay; // a view that overlay the camera view, used to draw rectangles
    private TextView mTextContainer; // a text view used as a text area to print the detected text

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text_recognition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCameraView = view.findViewById(R.id.camera_view);

        /**
         * setting up the camera lifecycle to match the one of the current fragment
         */

        mCameraView.setLifecycleOwner(this);

        /**
         * a video is a series of frames, for each frame captured by the camera
         * you need to run the Firebase MLKit Text recognition model for it.
         * this fragment already implement {@link FrameProcessor} interface.
         */
        mCameraView.addFrameProcessor(this);

        mGraphicOverlay = view.findViewById(R.id.graphic_overlay);
        mTextContainer = view.findViewById(R.id.text_container);

    }

    /**
     * this method return a Firebase MLKit image from the captured frame
     * @param frame a single image captured by the camera
     * @return {@link FirebaseVisionImage } Firebase MLKit Image object
     */
    private FirebaseVisionImage getImageFromFrame(Frame frame) {


         //TODO: get the frame data as an array of byte


        //TODO: fix the image rotation


        //TODO: create Firebase Image meta data: format, rotation, height, width


        //TODO: return a FirebaseVisionImage using the byteArray and the metadata

        return null;
    }


    /**
     * this method will process each frame captured by the camera and
     * recognize the text contained in
     *
     * @param frame a single image captured by the camera
     *
     */
    @Override
    @WorkerThread
    public void process(@NonNull Frame frame) {


        try {

            Tasks.await(analyze(getImageFromFrame(frame)));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private Task<FirebaseVisionText> analyze(FirebaseVisionImage image) {

        //TODO: get instance od the Firebase Vision text recognizer

        //TODO: start the text recognition task and handle both success and failure results

        return null;

    }


    /**
     * this method iterate over the recognition result and display the recognized text
     * on both the camera preview and the text area
     *
     * @param firebaseVisionText the result of the recognition process
     */
    private void processText(FirebaseVisionText firebaseVisionText){
        StringBuilder text = new StringBuilder();

        // TODO: clean the drawing surface

        // TODO: get the list of blocks

            // TODO: for each block get the list of lines

                // TODO: print the line on the text area

                // TODO: for each line get the list of element

                // TODO: draw a rectangle around the text element

    }





}
