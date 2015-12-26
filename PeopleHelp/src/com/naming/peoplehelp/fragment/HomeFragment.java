package com.naming.peoplehelp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ab.view.listener.AbOnItemClickListener;
import com.ab.view.sliding.AbSlidingPlayView;
import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.CleanCartActivity;
import com.naming.peoplehelp.adapter.HomeGridAdapter;

public class HomeFragment extends BaseFragment implements AbOnItemClickListener,OnItemClickListener,OnClickListener{
	
	private LinearLayout homeCleanLayout;
	
	private AbSlidingPlayView homePlayView = null;
	
	private GridView homeGridView;
	private HomeGridAdapter homeGridAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container,false);
		homePlayView = (AbSlidingPlayView) view.findViewById(R.id.playview_home);
		loadPlayView();
		return view;
	}
	
	private void loadPlayView(){
		View mPlayView1 = LayoutInflater.from(getActivity()).inflate(R.layout.home_playview_item, null);
		ImageView playViewImage1 = (ImageView) mPlayView1.findViewById(R.id.image_home_playview);
		playViewImage1.setBackgroundResource(R.drawable.tab_advertise_1);
		
		View mPlayView2 = LayoutInflater.from(getActivity()).inflate(R.layout.home_playview_item, null);
		ImageView playViewImage2 = (ImageView) mPlayView2.findViewById(R.id.image_home_playview);
		playViewImage2.setBackgroundResource(R.drawable.tab_advertise_2);
		
		homePlayView.setPageLineHorizontalGravity(Gravity.RIGHT);
		homePlayView.addView(mPlayView1);
		homePlayView.addView(mPlayView2);
		homePlayView.startPlay();
		homePlayView.setOnItemClickListener(this);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initUI();
	}
	
	private void initUI(){
		
		homeCleanLayout=(LinearLayout) getActivity().findViewById(R.id.layout_home_clean);
		homeGridView=(GridView) getActivity().findViewById(R.id.gridView_home);
		
		loadHomeList();
		homeCleanLayout.setOnClickListener(this);
	}
	
	private void loadHomeList(){
		homeGridAdapter=new HomeGridAdapter(getActivity());
		homeGridView.setAdapter(homeGridAdapter);
		homeGridView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(int position) {
		Toast.makeText(getActivity(), "�����"+position, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_home_clean:
			startActivity(new Intent(getActivity(), CleanCartActivity.class));
			break;

		default:
			break;
		}
	}

}
