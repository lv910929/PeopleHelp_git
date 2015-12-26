package com.naming.peoplehelp.view.recycler;

import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.CleanCartActivity;
import com.naming.peoplehelp.activity.MainActivity;
import com.naming.peoplehelp.data.DemoData;
import com.naming.peoplehelp.data.GoodsDataBaseInterface;
import com.naming.peoplehelp.data.OperateGoodsDataBase;


public class RecyclerViewContentAdapter extends RecyclerView.Adapter<RecyclerViewContentAdapter.ViewHolder>{
	
    private LayoutInflater mLayoutInflater;
    protected List<String> mListContentData;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    /** 数据操作接口 */
    GoodsDataBaseInterface mGoodsDataBaseInterface = null;

    //定义接口
    public interface OnItemClickListener{
        void onItemClick(ViewHolder holder);
        void onItemLongClick(ViewHolder holder);
        void onItemJiaClick(ViewHolder holder);
        void onItemJianClick(ViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener ;
    }
    public RecyclerViewContentAdapter(Context context, List<String> datas){
        this.mListContentData = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mGoodsDataBaseInterface = OperateGoodsDataBase.getInstance();
    }
    //创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAG", "Hellow");
        View v = mLayoutInflater.inflate(R.layout.item_menu_content,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
    	
        holder.mTitle.setText(DemoData.ListMenu_PTITLE[position]);
        holder.mPrice.setText(DemoData.ListMenu_PPRICE[position]);

        /** 获取存储的商品数量 */
        if (mGoodsDataBaseInterface.getSecondGoodsNumber(mContext, CleanCartActivity.SELECTPOSITION , DemoData.ListMenu_GOODSID[holder.getPosition()]) == 0) {
            holder.mNumber.setText("");
            holder.mNumber.setVisibility(View.GONE);
            holder.mImgJian.setVisibility(View.GONE);
        } else {
            holder.mNumber.setText("" + mGoodsDataBaseInterface.getSecondGoodsNumber(mContext, CleanCartActivity.SELECTPOSITION , DemoData.ListMenu_GOODSID[holder.getPosition()]));
            holder.mNumber.setVisibility(View.VISIBLE);
            holder.mImgJian.setVisibility(View.VISIBLE);
        }

        setOnListtener(holder);
    }
    //触发
    protected void setOnListtener(final ViewHolder holder){
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder);
                    return true;
                }
            });
            holder.mImgJia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemJiaClick(holder);
                }
            });
            holder.mImgJian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemJianClick(holder);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mListContentData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImgJia , mImgJian;
        public TextView mTitle , mYueSale , mPrice , mNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_menu_content_title);
            mPrice = (TextView) itemView.findViewById(R.id.item_menu_content_price);
            mImgJia = (ImageView) itemView.findViewById(R.id.item_menu_content_jia);
            mImgJian = (ImageView) itemView.findViewById(R.id.item_menu_content_jian);
            mNumber = (TextView) itemView.findViewById(R.id.item_menu_content_number);
        }
    }
}
