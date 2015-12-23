package com.naming.peoplehelp.adapter;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.api.bean.ArticleItem;
import com.naming.peoplehelp.api.bean.Type;
import com.naming.peoplehelp.util.DateUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ArticleAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<ArticleItem> mArticles = new ArrayList<ArticleItem>();
	private ImageLoader instance;
	private DisplayImageOptions options;

	public ArticleAdapter(Context context) {
		this.context = context;
		instance = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.def_image)
				.showImageForEmptyUri(R.drawable.def_image)
				.showImageOnFail(R.drawable.def_image).cacheInMemory(true)
				.cacheOnDisc(true).build();

	}

	public void addData(ArrayList<ArticleItem> articles) {
		if (articles != null) {
			this.mArticles.addAll(articles);
			this.notifyDataSetChanged();
		}
	}

	public ArrayList<ArticleItem> getData() {
		return mArticles;
	}

	@Override
	public int getCount() {
		return mArticles.size();
	}

	@Override
	public ArticleItem getItem(int position) {
		return mArticles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler viewholder = null;
		if (convertView == null) {
			viewholder = new ViewHodler();
			convertView = View.inflate(context,
					R.layout.list_article_item, null);
			viewholder.image_icon = (ImageView) convertView
					.findViewById(R.id.article_item_icon);
			viewholder.type = (TextView) convertView
					.findViewById(R.id.article_item_type);
			viewholder.time = (TextView) convertView
					.findViewById(R.id.article_item_time);
			viewholder.title = (TextView) convertView
					.findViewById(R.id.article_item_title);
			viewholder.content = (TextView) convertView
					.findViewById(R.id.article_item_content);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHodler) convertView.getTag();
		}
		ArticleItem item = getItem(position);
		if ("".equals(item.getThumb())) {
			viewholder.image_icon.setVisibility(View.GONE);
		} else {
			viewholder.image_icon.setVisibility(View.VISIBLE);
			instance.displayImage(item.getThumb(), viewholder.image_icon,
					options);
		}
		if ("新闻".equals(new Type(item.getModelid()).toString())) {
			viewholder.type.setText("资讯");
		} else {
			viewholder.type.setText(new Type(item.getModelid()).toString());
		}
		viewholder.title.setText(item.getTitle());
		viewholder.content.setText(item.getDescription());
		viewholder.time.setText(DateUtils.formatDateDifference(new Date(item
				.getSorttime() * 1000)));
		return convertView;
	}

	class ViewHodler {
		public ImageView image_icon;
		public TextView type, time, title, content;
	}
}
