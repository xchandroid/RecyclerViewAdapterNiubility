package com.cy.recyclerviewadapter.activity.grv;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.cyrvadapter.adapter.MultiAdapter;
import com.cy.cyrvadapter.adapter.SimpleAdapter;
import com.cy.cyrvadapter.recyclerview.GridRecyclerView;
import com.cy.cyrvadapter.adapter.BaseViewHolder;
import com.cy.recyclerviewadapter.BaseActivity;
import com.cy.recyclerviewadapter.R;
import com.cy.recyclerviewadapter.bean.HRVBean;
import com.cy.recyclerviewadapter.bean.VRBean;

import java.util.ArrayList;
import java.util.List;

public class GRVHeadFootActivity extends BaseActivity {
    private MultiAdapter<SimpleAdapter> multiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grvhead_foot);


        multiAdapter = new MultiAdapter<SimpleAdapter>().addAdapter(new SimpleAdapter<String>() {
            @Override
            public void bindDataToView(BaseViewHolder holder, int position, String bean,boolean isSelected) {
                holder.setText(R.id.tv, "head" + position);
            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.head;
            }

            @Override
            public void onItemClick(BaseViewHolder holder, int position, String bean) {
                showToast("点击head,删除head");
                remove(position);
            }
        }).addAdapter(new SimpleAdapter<HRVBean>() {

            @Override
            public void bindDataToView(BaseViewHolder holder, int position, HRVBean bean,boolean isSelected) {

                holder.setImageResource(R.id.iv, bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, HRVBean bean) {
                return R.layout.item_grv;
            }


            @Override
            public void onItemClick(BaseViewHolder holder, int position, HRVBean bean) {
                showToast("点击" + position);
            }

            @Override
            public void onViewAttachedToWindow(BaseViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                getAdapter().startDefaultAttachedAnim(holder);
            }

        }).addAdapter(new SimpleAdapter<String>() {
            @Override
            public void bindDataToView(BaseViewHolder holder, int position, String bean,boolean isSelected) {
                holder.setText(R.id.tv, "foot" + position);

            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.foot;

            }

            @Override
            public void onItemClick(BaseViewHolder holder, int position, String bean) {
                showToast("点击foot,删除foot");
                remove(position);
            }
        });
        ((GridRecyclerView) findViewById(R.id.grv)).setSpanCount(2).setAdapter(multiAdapter.getMergeAdapter());
        final List<String> list_head = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list_head.add("head" + i);
        }


        List<HRVBean> list_content = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 5 == 0) {
                list_content.add(new HRVBean(R.drawable.pic3));
                continue;

            }
            list_content.add(new HRVBean(R.drawable.pic1));
        }

        final List<String> list_foot = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list_foot.add("foot" + i);
        }
        multiAdapter.getAdapter(0).add(list_head);
        multiAdapter.getAdapter(1).add(list_content);
        multiAdapter.getAdapter(2).add(list_foot);
    }

    @Override
    public void onClick(View v) {

    }
}
