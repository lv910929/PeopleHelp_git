package com.naming.peoplehelp.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.bitmap.AbImageDownloader;
import com.ab.global.AbConstant;
import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.Constants;

public class ImageListAdapter extends BaseAdapter{
	
	private static final boolean D = Constants.DEBUG;
  
	private Context mContext;
	//xmlתView����
    private LayoutInflater mInflater;
    //���еĲ���
    private int mResource;
    //�б�չ�ֵ�����
    private List mData;
    //Map�е�key
    private String[] mFrom;
    //view��id
    private int[] mTo;
    //ͼƬ������
    private AbImageDownloader mAbImageDownloader = null;
    
   /**
    * ���췽��
    * @param context
    * @param data �б�չ�ֵ�����
    * @param resource ���еĲ���
    * @param from Map�е�key
    * @param to view��id
    */
    public ImageListAdapter(Context context, List data,
            int resource, String[] from, int[] to){
    	this.mContext = context;
    	this.mData = data;
    	this.mResource = resource;
    	this.mFrom = from;
    	this.mTo = to;
        //���ڽ�xmlתΪView
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //ͼƬ������
        mAbImageDownloader = new AbImageDownloader(mContext);
        mAbImageDownloader.setWidth(100);
        mAbImageDownloader.setHeight(100);
        mAbImageDownloader.setType(AbConstant.SCALEIMG);
        mAbImageDownloader.setLoadingImage(R.drawable.image_loading);
        mAbImageDownloader.setErrorImage(R.drawable.image_error);
        mAbImageDownloader.setNoImage(R.drawable.image_no);
       // mAbImageDownloader.setAnimation(true);
    }   
    
    @Override
    public int getCount() {
        return mData.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
      return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	  final ViewHolder holder;
          if(convertView == null){
	           //ʹ���Զ����list_items��ΪLayout
	           convertView = mInflater.inflate(mResource, parent, false);
	           //����findView�Ĵ���
			   holder = new ViewHolder();
	           //��ʼ�������е�Ԫ��
			   holder.itemsIcon = ((ImageView) convertView.findViewById(mTo[0])) ;
			   holder.itemsTitle = ((TextView) convertView.findViewById(mTo[1]));
			   holder.itemsText = ((TextView) convertView.findViewById(mTo[2]));
			   convertView.setTag(holder);
          }else{
        	   holder = (ViewHolder) convertView.getTag();
          }
          
		  //��ȡ���е�����
          final Map<String, Object>  obj = (Map<String, Object>)mData.get(position);
          String imageUrl = (String)obj.get("itemsIcon");
          holder.itemsTitle.setText((String)obj.get("itemsTitle"));
          holder.itemsText.setText((String)obj.get("itemsText"));
          //���ü����е�View
          mAbImageDownloader.setLoadingView(convertView.findViewById(R.id.progressBar));
          //ͼƬ������
          mAbImageDownloader.display(holder.itemsIcon,imageUrl);
          
          return convertView;
    }
    
    /**
	 * ViewԪ��
	 */
	static class ViewHolder {
		ImageView itemsIcon;
		TextView itemsTitle;
		TextView itemsText;
		ImageButton itemsBtn;
	}
    
}
