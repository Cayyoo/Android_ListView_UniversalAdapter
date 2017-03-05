package com.imooc.baseadapter.adapter;

import java.util.List;

import com.imooc.baseadapter.R;
import com.imooc.baseadapter.bean.Bean;
import com.imooc.baseadapter.utils.CommonAdapter;
import com.imooc.baseadapter.utils.CommonViewHolder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * 直接借助CommonAdapter实现的通用adapter
 *
 * 注意：使用MyCommonAdapter作为适配器时，解决了CheckBox错乱的问题
 */
public class MyCommonAdapter extends CommonAdapter<Bean> {

	public MyCommonAdapter(Context context, List<Bean> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	/**
	 * 实现public abstract View getView(int position, View convertView, ViewGroup parent);
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
/*	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, R.layout.item, position);
		Bean bean = mDatas.get(position);
		
		((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
		((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
		((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
		((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());
		
		return holder.getConvertView();
	}
*/

	/**
	 * 实现public abstract void convert(CommonViewHolder holder, T t);
	 * @see com.imooc.baseadapter.utils.CommonAdapter#convert(CommonViewHolder, java.lang.Object)
	 */
	@Override
	public void convert(CommonViewHolder holder, final Bean bean) {
/*
		((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
		((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
		((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
		((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());
*/
		holder.setText(R.id.tv_title, bean.getTitle())
				.setText(R.id.tv_desc, bean.getDesc())
				.setText(R.id.tv_time, bean.getTime())
				.setText(R.id.tv_phone, bean.getPhone());


		/**
		 * 解决ListView复用导致Item内容错乱的问题（使用MyAdapter时会有错乱，可运行查看）
		 *
		 * 方法一：使用一个boolean值记录是否被复用
		 */
		final CheckBox cBox = (CheckBox)(holder.getView(R.id.cb));
		if (cBox != null) {
			cBox.setChecked(bean.isChecked());

			cBox.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					bean.setChecked(cBox.isChecked());
				}
			});
		}

	}

}
