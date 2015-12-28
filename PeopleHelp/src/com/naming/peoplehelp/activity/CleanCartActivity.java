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
        //������ݿ⻺��
        mGoodsDataBaseInterface.deleteAll(CleanCartActivity.this);
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("��ͥ����");

		cleanCartLayout = (RelativeLayout) findViewById(R.id.layout_clean_cart);
		menuRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_menu);
		menuContentRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_menu_content);
		allPriceLabel=(TextView) findViewById(R.id.label_all_price);
		allNumLabel=(TextView) findViewById(R.id.label_all_num);
		submitOrderButton=(Button) findViewById(R.id.btn_submit_order);
		
		submitOrderButton.setOnClickListener(this);
	}
	
	 /**
     * ����RecyclerView�Ĳ��ַ�ʽ
     */
    private void setRecyclerView() {
        //��ֱ��ʾ��ʽ
    	menuRecyclerView.setLayoutManager(new LinearLayoutManager(CleanCartActivity.this, LinearLayoutManager.VERTICAL, false));
    	menuRecyclerView.addItemDecoration(new DividerItemDecoration(CleanCartActivity.this, LinearLayoutManager.VERTICAL));
    	menuContentRecyclerView.setLayoutManager(new LinearLayoutManager(CleanCartActivity.this, LinearLayoutManager.VERTICAL, false));
    }
    
    /**
     * ģ��������������
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
     * �˵��б�    �������
     */
    private void setMenuCommonadapter() {
        mRecyclerViewMenuCommonadapter = new RecyclerViewMenuAdapter(CleanCartActivity.this, stringMenuList);
        menuRecyclerView.setAdapter(mRecyclerViewMenuCommonadapter);
        mRecyclerViewMenuCommonadapter.setOnItemClickListener(new RecyclerViewMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                SELECTPOSITION = position;
                System.out.println("��ǰλ�ã�"+SELECTPOSITION);
                mRecyclerViewMenuCommonadapter.notifyDataSetChanged();
                mRecyclerViewContentCommonadapter.notifyDataSetChanged();
                setAll();
            }
            @Override
            public void onItemLongClick(View v, int position) {}
        });
    }
    /**
     * ��Ʒ�����б�    �������
     */
    private void setContentCommonadapter() {
        mRecyclerViewContentCommonadapter = new RecyclerViewContentAdapter(CleanCartActivity.this, mRecyclerViewMenuCommonadapter, stringContentList);
        menuContentRecyclerView.setAdapter(mRecyclerViewContentCommonadapter);
        mRecyclerViewContentCommonadapter.setOnItemClickListener(new RecyclerViewContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewContentAdapter.ViewHolder holder) {}
            @Override
            public void onItemLongClick(RecyclerViewContentAdapter.ViewHolder holder) {}
            /** ��� */
            @Override
            public void onItemJiaClick(RecyclerViewContentAdapter.ViewHolder holder) {
                String numText = holder.mNumber.getText().toString().trim();
                /** ����Ӻ�֮ǰ��û�����ݵ�ʱ�� */
                if (numText.isEmpty() || numText.equals("0")) {
                    holder.mImgJian.setVisibility(View.VISIBLE);
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], "1", DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                    holder.mNumber.setVisibility(View.VISIBLE);
                }/** ����Ӻ�֮ǰ�����ݵ�ʱ�� */
                else {
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], String.valueOf(Integer.parseInt(numText) + 1), DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                }
                /** ���� */
                GoodsAnimUtil.setAnim(CleanCartActivity.this, holder.mImgJia, cleanCartLayout);
                GoodsAnimUtil.setOnEndAnimListener(new onEndAnim());
                /** ͳ�ƹ��������͹����ܼ� */
            }
            /** ���� */
            @Override
            public void onItemJianClick(RecyclerViewContentAdapter.ViewHolder holder) {
                String numText = holder.mNumber.getText().toString().trim();
                holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(CleanCartActivity.this, SELECTPOSITION, DemoData.ListMenu_GOODSID[SELECTPOSITION][holder.getPosition()], String.valueOf(Integer.parseInt(numText) - 1), DemoData.ListMenu_PPRICE[SELECTPOSITION][holder.getPosition()]) + "");
                numText = holder.mNumber.getText().toString().trim();
                /** ����֮��  ����Ϊ0 */
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
					Toast.makeText(getApplicationContext(), "����ѡ����Ʒ", Toast.LENGTH_SHORT).show();
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
     * ���������󣬸����������������м۸�
     */
    class onEndAnim implements GoodsAnimUtil.OnEndAnimListener {
        @Override
        public void onEndAnim() {
            setAll();
        }
    }
    /**
     * ����Ӻźͼ��ŵ�ʱ�������������ܼ۸�
     */
    private void setAll() {
        //�������й�������
    	if (mGoodsDataBaseInterface.getAllGoodsPrice(CleanCartActivity.this) == 0) {
    		allNumLabel.setVisibility(View.GONE);
            allPriceLabel.setText("��0");
            allNumLabel.setText("0");
		}else {
			allPriceLabel.setText("��" + mGoodsDataBaseInterface.getAllGoodsPrice(CleanCartActivity.this) + "");
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
