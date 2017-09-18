package com.example.gem.ardemo;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LocalActivity extends AppCompatActivity {
    private VrPanoramaView panoWidgetView;
    private static final String TAG = LocalActivity.class.getSimpleName();
    private VrPanoramaView.Options paNormalOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        panoWidgetView = (VrPanoramaView) findViewById(R.id.pano_view);
        int type = getIntent().getIntExtra("type", 1);
        panoWidgetView.setEventListener(new ActivityEventListener());
        paNormalOptions = new VrPanoramaView.Options();
//      mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        panoWidgetView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        panoWidgetView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
        panoWidgetView.setEventListener(new ActivityEventListener()); //设置监听
        if (type == 1){
            paNormalOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
            //加载本地的图片源
            panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes2), paNormalOptions);
        }else {
            paNormalOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            //加载本地的图片源
            panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes), paNormalOptions);
        }
//        //加载本地的图片源
//        panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes2), paNormalOptions);
        //设置网络图片源
//        panoWidgetView.loadImageFromByteArray();
    }
    @Override
    protected void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoWidgetView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown();
        super.onDestroy();
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        /**
         * Called by pano widget on the UI thread when it's done loading the image.
         */
        @Override
        public void onLoadSuccess() {
            Log.e(TAG, "图片加载成功了 ");
        }

        /**
         * Called by pano widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {

            Log.e(TAG, "图片加载: " + errorMessage);
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            //改变显示模式时候（全屏模式和纸板模式）
            super.onDisplayModeChanged(newDisplayMode);
            Log.i(TAG, "onDisplayModeChanged------------>" + newDisplayMode);
        }
        @Override
        public void onClick() {
            super.onClick();
            //点击VrPanoramaView
            Log.i(TAG, "onClick------------>");
        }
    }
}
