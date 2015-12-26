package com.naming.peoplehelp.view.recycler;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.CleanCartActivity;
import com.naming.peoplehelp.data.DemoData;

public class RecyclerViewMenuAdapter extends RecyclerView.Adapter<RecyclerViewMenuAdapter.ViewHolder>{
	
    private LayoutInflater mLayoutInflater;
    protected List<String> mListMenuData;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    //����ӿ�
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener ;
    }
    public RecyclerViewMenuAdapter(Context context, List<String> datas){
        this.mListMenuData = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    //����ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    	View v = mLayoutInflater.inflate(R.layout.item_menu,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    //��ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    	holder.mTextView.setText(DemoData.ListMenu_STYLE[position]);
        //�������е�item��ʾΪĬ��״̬
        holder.mLinearLayout.setBackgroundResource(R.color.color_menu_back);
        holder.viewRed.setVisibility(View.GONE);
        holder.viewV.setVisibility(View.VISIBLE);
        //���ݵ��item��Ӧ��position���ı��item��״̬
        if (holder.getPosition() == CleanCartActivity.SELECTPOSITION) {
            holder.mLinearLayout.setBackgroundResource(R.color.white);
            holder.viewRed.setVisibility(View.VISIBLE);
            holder.viewV.setVisibility(View.GONE);
        }
        setOnListtener(holder);
    }
    //����
    protected void setOnListtener(final RecyclerView.ViewHolder holder){
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,layoutPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView,layoutPosition);
                    return true;
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mListMenuData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        LinearLayout mLinearLayout;
        View viewRed , viewV;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_menu_text);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.black_lay);
            viewRed = itemView.findViewById(R.id.item_menu_view_red);
            viewV = itemView.findViewById(R.id.item_menu_view_v);
        }
    }
}
