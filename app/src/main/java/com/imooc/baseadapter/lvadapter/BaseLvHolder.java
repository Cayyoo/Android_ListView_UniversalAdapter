package com.imooc.baseadapter.lvadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * 通用的ViewHolder封装类：
 * <p>
 * 注意SparseArray的使用，存储的键值对类型，适用<int,object>的情况
 *
 * @author Administrator
 */
public class BaseLvHolder {
    private Context context;

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;


    public BaseLvHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.context = context;
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    /**
     * 获得ViewHolder
     */
    public static BaseLvHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new BaseLvHolder(context, parent, layoutId, position);
        } else {
            BaseLvHolder holder = (BaseLvHolder) convertView.getTag();
            //更新position
            holder.mPosition = position;

            return holder;
        }
    }


    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }


    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    /**
     * 设置图片
     */
    public BaseLvHolder setImageByUrl(int viewId, String url) {
        //Glide.with(context).load(url).into((ImageView) getView(viewId));
        //ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
        //ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
        return this;
    }

    public BaseLvHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    public BaseLvHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public BaseLvHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseLvHolder setImageUri(int viewId, Uri uri) {
        ImageView img = getView(viewId);
        img.setImageURI(uri);
        return this;
    }


    /**
     * 设置背景
     */
    public BaseLvHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseLvHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseLvHolder setBackground(int viewId, Drawable background) {
        View view = getView(viewId);
        view.setBackground(background);
        return this;
    }


    /**
     * 设置字符串
     */
    public BaseLvHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseLvHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseLvHolder setText(int viewId, int resId) {
        TextView tv = getView(viewId);
        tv.setText(resId);
        return this;
    }

    public BaseLvHolder setTextColor(int viewId, int colorId) {
        TextView view = getView(viewId);
        //view.setTextColor(context.getResources().getColor(colorId));
        view.setTextColor(ContextCompat.getColor(context, colorId));
        return this;
    }

    public BaseLvHolder setTextColor(int viewId, String colorValue) {
        TextView view = getView(viewId);
        view.setTextColor(Color.parseColor(colorValue));
        return this;
    }


    /**
     * 设置字体样式
     */
    public BaseLvHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }


    /**
     * 设置TextView下划线
     */
    public BaseLvHolder setDeleteLine(int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }


    /**
     * 设置透明度
     */
    @SuppressLint("NewApi")
    public BaseLvHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }


    /**
     * 设置可见
     */
    public BaseLvHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }


    /**
     * 设置链接
     */
    public BaseLvHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }


    /**
     * 设置进度条
     */
    public BaseLvHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public BaseLvHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public BaseLvHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }


    /**
     * 设置旋转
     */
    public BaseLvHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public BaseLvHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }


    /**
     * 设置标签
     */
    public BaseLvHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseLvHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }


    /**
     * 设置选中
     */
    public BaseLvHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }


    /**
     * 设置点击
     */
    public BaseLvHolder setClicked(int viewId, boolean click) {
        View view = getView(viewId);
        view.setEnabled(click);
        view.setClickable(click);
        return this;
    }

    public BaseLvHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseLvHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseLvHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

}
