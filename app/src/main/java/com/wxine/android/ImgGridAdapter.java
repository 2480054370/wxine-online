package com.wxine.android;

/**
 * Created by Bumblebee on 2016/5/31.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.HashMap;

public class ImgGridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HashMap<String, Object>> mList;
    private ArrayList<String>mImg;

    public ImgGridAdapter(Context mContext, ArrayList<HashMap<String, Object>> mList,ArrayList<String>mImg) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        this.mImg = mImg;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        } else {
            return (int)Math.ceil(Math.random()*10);
            //return mImg.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList == null) {
            return null;
        } else {
            return this.mList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from
                    (this.mContext).inflate(R.layout.img_img, null, false);
            holder.image = (ImageView) convertView.findViewById(R.id.iv_head);
            ImageSize mImageSize = new ImageSize(100, 100);
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("http://img2.imgtn.bdimg.com/it/u=3642030749,2966168779&fm=21&gp=0.jpg",holder.image);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (this.mList != null) {
            HashMap<String, Object> hashMap = this.mList.get(position);
            if (holder.image != null) {
                holder.image.setTag(hashMap.get("content").toString());
                holder.image.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "第" + (position + 1) + "个", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, ImgShow.class);
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        return convertView;

    }

    private class ViewHolder {
        ImageView image;
    }
}
