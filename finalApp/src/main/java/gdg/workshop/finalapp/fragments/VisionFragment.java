package gdg.workshop.finalapp.fragments;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.concurrent.ExecutionException;

public abstract class VisionFragment<T> extends Fragment implements FrameProcessor {


    public FirebaseVisionImage getImageFromFrame(Frame frame) {
        byte[] byteArray = frame.getData();
        int rotation = FirebaseVisionImageMetadata.ROTATION_0;

        switch (frame.getRotation()) {
            case 0:
                rotation = FirebaseVisionImageMetadata.ROTATION_0;
                break;
            case 90:
                rotation = FirebaseVisionImageMetadata.ROTATION_90;
                break;
            case 180:
                rotation = FirebaseVisionImageMetadata.ROTATION_180;
                break;
            case 270:
                rotation = FirebaseVisionImageMetadata.ROTATION_270;
                break;
        }

        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setRotation(rotation)
                .setHeight(frame.getSize().getHeight())
                .setWidth(frame.getSize().getWidth())
                .build();

        return FirebaseVisionImage.fromByteArray(byteArray, metadata);
    }

    public abstract Task<T> analyze(FirebaseVisionImage image);

    @Override
    @WorkerThread
    public void process(@NonNull Frame frame) {

        Log.d("TAG", "process: "+frame);
        try {

            Tasks.await(analyze(getImageFromFrame(frame)));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    }
