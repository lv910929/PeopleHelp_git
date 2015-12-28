package com.naming.peoplehelp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.data.DemoData;
import com.naming.peoplehelp.data.GoodsDataBaseInterface;
import com.naming.peoplehelp.data.OperateGoodsDataBase;
import com.naming.peoplehelp.util.GoodsAnimUtil;
import com.naming.peoplehelp.view.recycler.DividerItemDecoration;
import com.naming.peoplehelp.view.recycler.RecyclerViewContentAdapter;
import com.naming.peoplehelp.view.recycler.RecyclerViewMenuAdapter;

public class CleanCartActivity extends BaseActivity implements OnClickListener{

	public static int SELECTPOSITION = 0;

	private TextView titleLabel;

	private RelativeLayout cleanCartLayout;
	private RecyclerView menuRecyclerView;
	private RecyclerView menuContentRecyclerView;
	private TextView allPriceLabel;
	private TextView allNumLabel;
	private Button submitOrderButton;

	private List<String> stringMenuList = new ArrayList<String>();
	private List<String> stringContentList = new ArrayList<String>();

	GoodsDataBaseInterface mGoodsDataBaseInterface = null;
	private RecyclerViewMenuAdapter mRecyclerViewMenuCommonadapter = null;
	private RecyclerViewContentAdapter mRecyclerViewContentCommonadapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clean_cart);
		initData();
		initUI();
		setRecyclerView();
		initHttp();
	}
	
	private void initData(){
		mGoodsDataBaseInterface = OperateGoodsDataBase.getInstance();
        //清空数据库缓存
        mGoodsDataBaseInterface.deleteAll(CleanCartActivity.this);
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("家庭保洁");

		cleanCartLayout = (RelativeLayout) findViewById(R.id.layout_clean_cart);
		menuRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_menu);
		menuContentRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_menu_content);
		allPriceLabel=(TextView) findViewById(R.id.label_all_price);
		allNumLabel=(TextView) findViewById(R.id.label_all_num);
		submitOrderButton=(Button) findViewById(R.id.btn_submit_order);
		
		submitOrderButton.setOnClickListener(this);
	}
	
	 /**
     * 设置RecyclerView的布局方式
     */
    private void setRecyclerView() {
        //垂直显示方式
    	menuRecyclerView.setLayoutManager(new LinearLayoutManager(CleanCartActivity.this, LinearLayoutManager.VERTICAL, false));
    	menuRecyclerView.addItemDecoration(new DividerItemDecoration(CleanCartActivity.this, LinearLayoutManager.VERTICAL));
    	menuContentRecyclerView.setLayoutManager(new LinearLayoutManager(CleanCartActivity.this, LinearLayoutManager.VERTICAL, false));
    }
    
    /**
     * 模拟网络请求数据
     */
    private void initHttp() {
        for (int i = 0; i < 7; i++) {
            stringMenuList.add("1111");
        }
        for (int i = 0; i < 4; i++) {
            stringContentList.add("2222");
        }
        setMenuCommonadapter();
        setContentCommonadapter();
    }
    
    /**
     * 菜单列表    数据填充
     */
    private void setMenuCommonadapter() {
        mRecyclerViewMenuCommonadapter = new RecyclerViewMenuAdapter(CleanCartActivity.this, stringMenuList);
        menuRecyclerView.setAdapter(mRecyclerViewMenuCommonadapter);
        mRecyclerViewMenuCommonadapter.setOnItemClickListener(new RecyclerViewMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                SELECTPOSITION = position;
                System.out.println("当前位置："+SELECTPOSITION);
                mRecyclerViewMenuCommonadapter.notifyDataSetChanged();
                mRecyclerViewContentCommonadapter.notifyDataSetChanged();
                setAll();
            }
            @Override
            public void onItemLongClick(View v, int position) {}
        });
    }
    /**
     * 商品种类列表    数据填充
     */
    private void setContentCommonadapter() {
        mRecyclerViewContentCommonadapter = new RecyclerViewContentAdapter(CleanCartActivity.this, mRecyclerViewMenuCommonadapter, stringContentList);
        menuContentRecyclerView.setAdapter(mRecyclerViewContentCommonadapter);
        mRecyclerViewContentCommonadapter.setOnItemClickListener(new RecyclerViewContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewContentAdapter.ViewHolder holder) {}
            @Override
            public void onItemLongClick(RecyclerViewContentAdapter.ViewHolder holder) {}
            /** 添加 */
            @Override
            public void onItemJiaClick(RecyclerViewContentAdapter.ViewHolder holder) {
                String numText = holder.mNumber.getText().toString().trim();
                /** 点击加号之前还没有数据的时候 */
                if (numText.isEmpty() || numText.equals("0")) {
                    holder.mImgJian.setVisibility(View.VISIBLE);
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], "1", DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                    holder.mNumber.setVisibility(View.VISIBLE);
                }/** 点击加号之前有数据的时候 */
                else {
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], String.valueOf(Integer.parseInt(numText) + 1), DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                }
                /** 动画 */
                GoodsAnimUtil.setAnim(CleanCartActivity.this, holder.mImgJia, cleanCartLayout);
                GoodsAnimUtil.setOnEndAnimListener(new onEndAnim());
                /** 统计购物总数和购物总价 */
            }
            /** 减少 */
            @Override
            public void onItemJianClick(RecyclerViewContentAdapter.ViewHolder holder) {
                String numText = holder.mNumber.getText().toString().trim();
                holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], String.valueOf(Integer.parseInt(numText) - 1), DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                numText = holder.mNumber.getText().toString().trim();
                /** 减完之后  数据为0 */
                if (numText.equals("0")) {
                    holder.mNumber.setVisibility(View.GONE);
                    holder.mImgJian.setVisibility(View.GONE);
                }
                setAll();
            }
        });
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit_order:
			if (ContextApplication.hasLogin) {
				if (mGoodsDataBaseInterface.getAllGoodsPrice(CleanCartActivity.this)==0) {
					Toast.makeText(getApplicationContext(), "请先选择商品", Toast.LENGTH_SHORT).show();
				}else {
					finish();
				}
			}else {
				redirtToLogin();
			}
			break;
		}
	}
	
	/**
     * 动画结束后，更新所有数量和所有价格
     */
    class onEndAnim implements GoodsAnimUtil.OnEndAnimListener {
        @Override
        public void onEndAnim() {
            setAll();
        }
    }
    /**
     * 点击加号和减号的时候设置总数和总价格
     */
    private void setAll() {
        //设置所有购物数量
    	if (mGoodsDataBaseInterface.getAllGoodsPrice(CleanCartActivity.this) == 0) {
    		allNumLabel.setVisibility(View.GONE);
            allPriceLabel.setText("￥0");
            allNumLabel.setText("0");
		}else {
			allPriceLabel.setText("￥" + mGoodsDataBaseInterface.getAllGoodsPrice(CleanCartActivity.this) + "");
        	allNumLabel.setText(mGoodsDataBaseInterface.getAllGoodsNumber(CleanCartActivity.this) + "");
        	allNumLabel.setVisibility(View.VISIBLE);
		}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == LOGIN_CODE && resultCode == RESULT_OK) {
    		
		}
    }
}
