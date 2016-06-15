package com.wxine.android;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxine_online.wxine_online.R;
import com.wxine.android.model.Comment;
import com.wxine.android.model.Info;
import com.wxine.android.utils.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2016/6/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private final Context context;
    private List<Info> list;
    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onKQClick(View view, int position);
    }

    public HomeAdapter(Context context, List<Info> list) {
        this.context = context;
        this.list = list;
        setHasStableIds(true);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_head, parent, false));
        } else {
            return new TextViewHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.home_body, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Info a;
        if (position > 0) {
            a = list.get(position - 1);
        } else {
            a = list.get(position);
        }
        if (holder instanceof ImageViewHolder) {

        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).HomeBodyName.setText(a.getUser().getName());
            if (a.getComments().size() > 0) {
                ViewGroup.LayoutParams lp = ((TextViewHolder) holder).HomeBodyRecyclerView.getLayoutParams();
                lp.height = 200;
                ((TextViewHolder) holder).HomeBodyRecyclerView.setLayoutParams(lp);
                ((TextViewHolder) holder).HomeBodyRecyclerView.requestLayout();
                final CommentsAdapter mAdapter = new CommentsAdapter(context, new ArrayList<Comment>(a.getComments()));
                ((TextViewHolder) holder).HomeBodyRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

                //自动滚动
                final int count = a.getComments().size();
                if (count > 1) {//一个滚动没有意义
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        int to = 1;

                        @Override
                        public void run() {
                            ((TextViewHolder) holder).HomeBodyRecyclerView.smoothScrollToPosition(to);
                            if (to >= count) to = 1;
                            else to = to + 1;//重新开始
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private ImageView HomeBodyUser;
        private TextView HomeBodyName;
        private ImageView HomeBodyImage;
        private ImageView HomeBodyAgreeImg;
        private ImageView HomeBodyShareImg;
        public RecyclerView HomeBodyRecyclerView;

        TextViewHolder(Context context, View view) {
            super(view);
            HomeBodyUser = (ImageView) view.findViewById(R.id.HomeBodyUser);
            HomeBodyName = (TextView) view.findViewById(R.id.HomeBodyName);
            HomeBodyImage = (ImageView) view.findViewById(R.id.HomeBodyImage);
            HomeBodyAgreeImg = (ImageView) view.findViewById(R.id.HomeBodyAgreeImg);
            HomeBodyShareImg = (ImageView) view.findViewById(R.id.HomeBodyShareImg);
            HomeBodyRecyclerView = (RecyclerView) view.findViewById(R.id.HomeBodyRecyclerView);
            HomeBodyRecyclerView.setHasFixedSize(true);

            FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(context);
            HomeBodyRecyclerView.setLayoutManager(mLayoutManager);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView HomeHeadBgImage;
        private CircleImageView HomeHeadIcon;
        private TextView HomeHeadName;
        private TextView HomeHeadAutograph;
        private TextView HomeHeadFollowMan;
        private Button HomeHeadButton;

        ImageViewHolder(final View view) {
            super(view);
            HomeHeadBgImage = (ImageView) view.findViewById(R.id.HomeHeadBgImage);
            HomeHeadIcon = (CircleImageView) view.findViewById(R.id.HomeHeadIcon);
            HomeHeadName = (TextView) view.findViewById(R.id.HomeHeadName);
            HomeHeadAutograph = (TextView) view.findViewById(R.id.HomeHeadAutograph);
            HomeHeadFollowMan = (TextView) view.findViewById(R.id.HomeHeadFollowMan);
            HomeHeadButton = (Button) view.findViewById(R.id.HomeHeadButton);

            HomeHeadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onKQClick(v, getLayoutPosition());
                }
            });
        }
    }

}
