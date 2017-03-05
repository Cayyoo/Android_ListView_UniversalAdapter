package com.imooc.baseadapter.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 封装类，万能适配器Adapter：
 * 继承了BaseAdapter，然后借助CommonViewHolder再次封装
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int mlayoutId;

	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mDatas = datas;
		this.mlayoutId = layoutId;
		mInflater = LayoutInflater.from(context);
	}

	/**
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mDatas!=null ? mDatas.size() : 0;
	}

	/**
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	/**
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	/*@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);*/

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, mlayoutId, position);

		convert(holder, getItem(position));

		return holder.getConvertView();
	}

	public abstract void convert(CommonViewHolder holder, T t);
}
