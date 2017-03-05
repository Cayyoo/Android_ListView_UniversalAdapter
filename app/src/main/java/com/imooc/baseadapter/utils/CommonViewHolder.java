package com.imooc.baseadapter.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 通用的ViewHolder封装类：
 *
 * 注意SparseArray的使用，存储的键值对类型，适用<int,object>的情况
 */
public class CommonViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public View getConvertView() {
		return mConvertView;
	}

	public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.mConvertView.setTag(this);
	}

	/**
	 * 获得ViewHolder
	 */
	public static CommonViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position) {
		if (null == convertView) {
			return new CommonViewHolder(context, parent, layoutId, position);
		} else {
			CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
			holder.mPosition = position;//更新position

			return holder;
		}
	}

	/**
	 * 通过viewId获取控件
	 */
	public <T extends View>T getView(int viewId) {
		View view = mViews.get(viewId);

		if (null == view) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}

		return (T)view;
	}

	public int getmPosition() {
		return mPosition;
	}

	public void setmPosition(int mPosition) {
		this.mPosition = mPosition;
	}

	//以下方法区，可添加相应的方法给控件设置对应的属性值
	//当Item包含其他控件时，再来这里添加相应的方法，长此以往，就是万能的了

	/**
	 * 给ID为viewId的TextView设置文字text，并返回this
	 */
	public CommonViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * 给ID为viewId的ImageView设置resource，并返回this
	 */
	public CommonViewHolder setImageResource(int viewId, int resId) {
		ImageView img = getView(viewId);
		img.setImageResource(resId);
		return this;
	}

	public CommonViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView img = getView(viewId);
		img.setImageBitmap(bitmap);
		return this;
	}

	public CommonViewHolder setImageUri(int viewId, Uri uri) {
		ImageView img = getView(viewId);
		img.setImageURI(uri);
		return this;
	}

}
