package com.andygeeks.bala;

import android.app.Application;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

public class MyApplication extends Application {

    static GenericRequestBuilder requestBuilder;

    @Override
    public void onCreate() {
        super.onCreate();

        requestBuilder = Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.defaultimage)
                .error(R.drawable.defaultimage)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
    }

    public static GenericRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
