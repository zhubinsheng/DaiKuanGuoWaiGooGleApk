package com.chaojidaikuan.daikuanguowai.ui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chaojidaikuan.daikuanguowai.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }
    @Override
    public ImageView createImageView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_img, null);
        RoundedImageView imageView = view.findViewById(R.id.banner);
        return imageView;
    }
}
