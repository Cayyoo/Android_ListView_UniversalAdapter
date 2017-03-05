package com.imooc.baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.imooc.baseadapter.adapter.MyCommonAdapter;
import com.imooc.baseadapter.adapter.MyAdapter;
import com.imooc.baseadapter.bean.Bean;
import com.imooc.baseadapter.utils.CommonAdapter;
import com.imooc.baseadapter.utils.CommonViewHolder;

/**
 * 打造ListView、GridView万能适配器
 *
 * （一）
 * 解决ListView复用导致Item内容错乱的问题，解决方法：
 * 1、使用一个boolean值记录是否被复用
 * 2、当没有boolean值来记录是否被复用时，使用List集合来操作对应position的对象
 *
 * （二）
 * 某些会获取焦点的控件（如CheckBox、Button）作为Item的组件会抢占ListView的焦点，
 * 致使ListView不可点击，解决方案：
 * 1、（CheckBox、Button等）Item控件设置属性android:focusable="false"
 * 2、外层布局设置android:descendantFocusability="blocksDescendants"
*/
public class MainActivity extends Activity {

	private ListView mListView;
	private List<Bean> mDatas;

	private MyAdapter myAdapter;
	private MyCommonAdapter myCommonAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initDatas();
		initView();
	}

	/**
	 * 初始化数据和适配器
	 */
	private void initDatas() {
		mDatas = new ArrayList<Bean>();

		//添加13条数据
		Bean bean = new Bean("Android新技能 Get 1", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 2", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 3", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 4", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 5", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 6", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 7", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 8", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 9", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 10", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 11", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 12", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);
		bean = new Bean("Android新技能 Get 13", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
		mDatas.add(bean);

		myAdapter = new MyAdapter(this, mDatas, R.layout.item);
		myCommonAdapter = new MyCommonAdapter(this, mDatas, R.layout.item);
	}

	/**
	 * 为列表设置适配器
	 */
	private void initView() {
		mListView = (ListView) findViewById(R.id.listView);
		//mListView.setAdapter(myAdapter);//第一种方法设置adapter
		//mListView.setAdapter(myCommonAdapter);//第二种方法设置adapter

		mySetAdapter();//第三种方法设置adapter
	}

	private void mySetAdapter() {
		mListView.setAdapter(new CommonAdapter<Bean>(MainActivity.this,mDatas, R.layout.item) {
			private List<Integer> mPos=new ArrayList<Integer>();

			@Override
			public void convert(final CommonViewHolder holder, final Bean bean) {
				holder.setText(R.id.tv_title, bean.getTitle())
						.setText(R.id.tv_desc, bean.getDesc())
						.setText(R.id.tv_time, bean.getTime())
						.setText(R.id.tv_phone, bean.getPhone());

				/**
				 * 解决ListView复用导致Item内容错乱的问题（使用MyAdapter时会有错乱，可运行查看）
				 *
				 * 方法二：当没有boolean值来记录是否被复用时，使用List集合来操作对应position的对象
				 */
				final CheckBox cBox = (CheckBox)(holder.getView(R.id.cb));
				if (cBox != null) {
					cBox.setChecked(false);

					if (mPos.contains(holder.getmPosition()))
						cBox.setChecked(true);

					cBox.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (cBox.isChecked()){
								mPos.add(holder.getmPosition());
							}else{
								//这里需要remove按对象移除，若不强转为Integer则是按位置移除
								mPos.remove((Integer)holder.getmPosition());
							}
						}
					});

				}

			}
		});
	}

}
