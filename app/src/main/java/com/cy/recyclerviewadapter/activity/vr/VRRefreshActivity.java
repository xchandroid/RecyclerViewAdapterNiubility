package com.cy.recyclerviewadapter.activity.vr;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cy.cyrvadapter.adapter.SimpleAdapter;
import com.cy.cyrvadapter.refreshrv.OnRefreshListener;
import com.cy.cyrvadapter.refreshrv.VerticalRefreshLayout;
import com.cy.cyrvadapter.adapter.BaseViewHolder;
import com.cy.recyclerviewadapter.BaseActivity;
import com.cy.recyclerviewadapter.R;
import com.cy.recyclerviewadapter.bean.VRBean;

import java.util.ArrayList;
import java.util.List;

public class VRRefreshActivity extends BaseActivity {
    private SimpleAdapter<VRBean> rvAdapter;
    private VerticalRefreshLayout verticalRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrrefresh2);

        verticalRefreshLayout= (VerticalRefreshLayout) findViewById(R.id.vrl);
        List<VRBean> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(new VRBean("内容"+i));
        }
        rvAdapter = new SimpleAdapter<VRBean>() {
            @Override
            public void bindDataToView(BaseViewHolder holder, int position, VRBean bean,boolean isSelected) {
                holder.setText(R.id.tv, bean.getStr());
            }

            @Override
            public int getItemLayoutID(int position, VRBean bean) {
                return R.layout.item_rv;

            }

            @Override
            public void onItemClick(BaseViewHolder holder,int position, VRBean bean) {
                showToast("点击" + position);

            }
        };
        verticalRefreshLayout.setAdapter(rvAdapter,new OnRefreshListener() {
            @Override
            public void onRefreshStart() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        verticalRefreshLayout.finishRefresh(new RefreshFinishListener() {
//                            @Override
//                            public void onRefreshFinish(final FrameLayout headLayout) {
//                                final TextView textView = new TextView(headLayout.getContext());
//                                textView.setGravity(Gravity.CENTER);
//                                textView.setBackgroundColor(Color.WHITE);
//                                textView.setText("有8条更新");
//                                headLayout.addView(textView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        headLayout.removeView(textView);
//                                        verticalRefreshLayout.closeRefresh();
//                                    }
//                                }, 2000);
//                            }
//                        });
                        verticalRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });
        rvAdapter.add(list);
    }

    @Override
    public void onClick(View v) {

    }
}
