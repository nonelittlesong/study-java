package com.faceattributes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.faceattributes.monitor.CPUMonitor;
import com.faceattributes.monitor.IJavaCallback;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private JavaCameraView cameraView;
    private Mat rgbaMat;

    private static final int REQUEST_PERMISSION = 3;

    private NativeFunction uglyFace = new NativeFunction();

    private static final String TAG = "MainActivity";

    // UI操作的Handler
    private final Handler mUIHandler = new Handler(Looper.getMainLooper());
    private final Thread mUIThread = mUIHandler.getLooper().getThread();

    TextView total;
    TextView female;
    TextView male;
    TextView child;
    TextView teen;
    TextView young;
    TextView midle;
    TextView old;

    // log
    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = findViewById(R.id.tv_total);
        female = findViewById(R.id.tv_female);
        male = findViewById(R.id.tv_male);
        child = findViewById(R.id.tv_child);
        teen = findViewById(R.id.tv_teen);
        young = findViewById(R.id.tv_young);
        midle = findViewById(R.id.tv_middle);
        old = findViewById(R.id.tv_old);
        cameraView = findViewById(R.id.cameraView);

        // 设置人脸计数的回调
        uglyFace.setCallback(mCountCallback);

        // 权限
        checkPermission();


    }

    //===================================== 权限 ===========================================
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allGranted = true;
        switch(requestCode) {
            case REQUEST_PERMISSION:
                for (int grandResult : grantResults) {
                    if (grandResult != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false;
                        Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (allGranted) {
                    initFace();
                    // log
                    logger = LoggerFactory.getLogger(MainActivity.class);
                }
                break;
        }
    }

    /**
     * 模型初始化
     */
    private void initFace() {
        cameraView.setCvCameraViewListener(this);

        // 模型初始化
        try {
            copyBigDataToSD("det1.bin");
            copyBigDataToSD("det2.bin");
            copyBigDataToSD("det3.bin");
            copyBigDataToSD("det1.param");
            copyBigDataToSD("det2.param");
            copyBigDataToSD("det3.param");
            copyBigDataToSD("mobilenet.bin");
            copyBigDataToSD("mobilenet.param");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File sdDir = Environment.getExternalStorageDirectory();
        String sdPath = sdDir.toString() + "/mtcnn/";
        uglyFace.ModelInit(sdPath);
    }

    private void checkPermission() {
        List<String> mPermissionList = new ArrayList<>();
        List<String> mRequestPermissions = new ArrayList<>();
        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission);
            }
        }

        if (!mPermissionList.isEmpty()) {
            for (String requestPermission : mPermissionList) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, requestPermission)) {
                    Toast.makeText(this, requestPermission + " not granted", Toast.LENGTH_SHORT).show();
                } else {
                    mRequestPermissions.add(requestPermission);
                }
            }
            ActivityCompat.requestPermissions(this, mRequestPermissions.toArray(new String[mPermissionList.size()]), REQUEST_PERMISSION);
        } else {
            initFace();
            // log
            logger = LoggerFactory.getLogger(MainActivity.class);
        }
    }
    //========================================================================================


    private IJavaCallback mCountCallback = new IJavaCallback() {
        @Override
        public void displayCount(final int[] count) {

            Log.d("count", "#displayCount: "+Thread.currentThread().getName());
            Log.d("count", "count size: "+count.length);
            for (int i = 0; i < count.length; i++) {
                Log.d("count", "count at "+i+" is "+count[i]);
            }
            logger.debug("someone gone");
            logger.error("test error");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    total.setText("total:"+count[0]);
                    female.setText(" female:"+count[1]);
                    male.setText(" male:"+count[2]);
                    child.setText(" child:"+count[3]);
                    teen.setText(" teenager:"+count[4]);
                    young.setText(" young:"+count[5]);
                    midle.setText(" middle:"+count[6]);
                    old.setText(" old:"+count[7]);
                }
            }, 0);
        }
    };

    public final void runOnUiThread(final Runnable task, final long duration) {
        if (task == null) return;
        mUIHandler.removeCallbacks(task);
        if ((duration > 0) || Thread.currentThread() != mUIThread) {
            mUIHandler.postDelayed(task, duration);
        } else {
            try {
                task.run();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void removeFromUiThread(final Runnable task) {
        if (task == null) return;
        mUIHandler.removeCallbacks(task);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        rgbaMat = new Mat(width, height, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        rgbaMat.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        rgbaMat = inputFrame.rgba();
        long addr = rgbaMat.getNativeObjAddr();
        uglyFace.makeFace(addr);
        return rgbaMat;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
        } else {
            cameraView.enableView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    private void copyBigDataToSD(String strOutFileName) throws IOException {
        Log.d(TAG, "start copy file " + strOutFileName);
        File sdDir = Environment.getExternalStorageDirectory();
        File file = new File(sdDir.toString()+"/mtcnn/");
        if (!file.exists()) {
            Log.d(TAG, "directory mtcnn does not exist");
            if (file.mkdir()) Log.d(TAG, "make directory mtcnn success");
            else Log.d(TAG, "make directory mtcnn failed");
        }
        String tmpFile = sdDir.toString()+"/mtcnn/"+strOutFileName;
        File f = new File(tmpFile);
        if (f.exists()) {
            Log.d(TAG, "file exist "+strOutFileName);
            return;
        }
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(sdDir.toString()+"/mtcnn/"+strOutFileName);
        myInput = this.getAssets().open(strOutFileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
        Log.d(TAG, "end copy file " + strOutFileName);
    }
}
