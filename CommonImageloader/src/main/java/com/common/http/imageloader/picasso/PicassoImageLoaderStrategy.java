package com.common.http.imageloader.picasso;

import android.content.Context;

import androidx.annotation.Nullable;

import com.common.imageloader.BaseImageLoaderStrategy;

public class PicassoImageLoaderStrategy implements BaseImageLoaderStrategy<PicassoImageConfig> {

    @Override
    public void loadImage(Context ctx, PicassoImageConfig config) {
//        Picasso.with(ctx)
//                .load(config.getUrl())
//                .into(config.getImageView());
    }

    @Override
    public void clear(@Nullable Context ctx, @Nullable PicassoImageConfig config) {

    }
}