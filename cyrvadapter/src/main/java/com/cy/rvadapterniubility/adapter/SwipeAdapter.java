package com.cy.rvadapterniubility.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.rvadapterniubility.swipelayout.OnSwipeListener;
import com.cy.rvadapterniubility.swipelayout.SwipeLayout;

/**
 * Created by cy on 2018/3/29.类似策略模式,引入IAdapter接口，面向多态编程
 */

public abstract class SwipeAdapter<T> implements IAdapter<T, BaseViewHolder, SimpleAdapter> {
    private IAdapter<T, BaseViewHolder, SimpleAdapter> adapter;
    private SwipeLayout swipeLayout_opened;
    private SwipeLayout swipeLayout_scrolled;

    public SwipeAdapter() {
    }

    private void dealSwipe(final BaseViewHolder holder, final T bean) {
        ((SwipeLayout) holder.itemView).getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.onItemClick(holder, holder.getBindingAdapterPosition(), bean);
            }
        });
        ((SwipeLayout) holder.itemView).setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void onScrolled(int dx) {
                swipeLayout_scrolled = (SwipeLayout) holder.itemView;
                SwipeAdapter.this.onScrolled(holder, holder.getBindingAdapterPosition(), bean, dx);
            }

            @Override
            public void onOpened() {
                swipeLayout_opened = (SwipeLayout) holder.itemView;
                SwipeAdapter.this.onOpened(holder, holder.getBindingAdapterPosition(), bean);
            }

            @Override
            public void onClosed() {
                swipeLayout_opened = null;
                SwipeAdapter.this.onClosed(holder, holder.getBindingAdapterPosition(), bean);
            }
        });
    }

    public void onScrolled(BaseViewHolder holder, int position, T bean, int dx) {
    }

    public void onOpened(BaseViewHolder holder, int position, T bean) {
    }

    public void onClosed(BaseViewHolder holder, int position, T bean) {
    }

    @Override
    public void onItemLongClick(BaseViewHolder holder, int position, T bean) {

    }

    /**
     * 再次彰显面向多态编程的威力，接口的强扩展
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {

    }


    public SwipeLayout getOpened() {
        return swipeLayout_opened;
    }

    public void closeOpened() {
        swipeLayout_opened.close();
        swipeLayout_opened = null;
    }

    public void closeOpened(OnSwipeListener onSwipeListener) {
        swipeLayout_opened.close(onSwipeListener);
        swipeLayout_opened = null;
    }

    public SwipeLayout getScrolled() {
        return swipeLayout_scrolled;
    }

    public void closeScrolled() {
        swipeLayout_scrolled.close();
        swipeLayout_scrolled = null;
    }

    @Override
    public SimpleAdapter<T> getAdapter() {
        if (adapter == null)
            adapter = new SimpleAdapter<T>() {
                @Override
                public void bindDataToView(final BaseViewHolder holder, int position, T bean, boolean isSelected) {
                    dealSwipe(holder, bean);
                    SwipeAdapter.this.bindDataToView(holder, position, bean, position == getPositionSelected());
                }

                @Override
                public int getItemLayoutID(int position, T bean) {
                    return SwipeAdapter.this.getItemLayoutID(position, bean);
                }

                @Override
                public void onItemClick(BaseViewHolder holder, int position, T bean) {
                    SwipeAdapter.this.onItemClick(holder, position, bean);
                }

                @Override
                public void onItemLongClick(BaseViewHolder holder, int position, T bean) {
                    SwipeAdapter.this.onItemLongClick(holder, position, bean);
                }

                @Override
                public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
                    super.onViewAttachedToWindow(holder);
                    SwipeAdapter.this.onViewAttachedToWindow(holder);

                }
            };
        return (SimpleAdapter<T>) adapter;
    }
}