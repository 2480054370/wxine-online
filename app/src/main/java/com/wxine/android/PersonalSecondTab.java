package com.wxine.android;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by zz on 2016/6/21.
 */
public class PersonalSecondTab extends Fragment {

    Button SelectFile;
    Button SecondUpdata;
    private View view;
    private LayoutInflater mInflater;
    private LinearLayout mGallery;
    private String[] mImgIds;
    private ImageLoader imageLoader;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getContext());
        imageLoader = ImageLoader.getInstance(); // Get singleton instance
        view = inflater.inflate(R.layout.personal_second_tab, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        SecondUpdata = (Button) view.findViewById(R.id.SecondUpdata);
        SecondUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "上传已确认", Toast.LENGTH_LONG).show();
            }
        });

        SelectFile = (Button) view.findViewById(R.id.SelectFile);
        SelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Intent.ACTION_PICK, null);
                int1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(int1, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图库
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            initData(picturePath);
            initView();

            /*//压缩，用于节省BITMAP内存空间--解决BUG的关键步骤
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;    //这个的值压缩的倍数（2的整数倍），数值越小，压缩率越小，图片越清晰

            //返回原图解码之后的bitmap对象
            initData(BitmapFactory.decodeFile(picturePath,opts));*/
            //显示在Gallery视图
//            initData(BitmapFactory.decodeFile(picturePath));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //照片的显示
    private void initData(String pub_photos) {
        mImgIds = new String[]{pub_photos};
    }

    private void initView() {
        mGallery = (LinearLayout) view.findViewById(R.id.person_gallery);
        for (int i = 0; i < mImgIds.length; i++) {
            final View personalphoto_item = mInflater.inflate(R.layout.personalphoto_item,
                    mGallery, false);
            final ImageView img = (ImageView) personalphoto_item.findViewById(R.id.per_photos);
            mImgIds[i] = "file:///"+mImgIds[i];
            imageLoader.displayImage(mImgIds[i], img);
            mGallery.addView(personalphoto_item);
            //删除照片
            ImageView remove = (ImageView) personalphoto_item.findViewById(R.id.remove_perimg);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGallery.removeView(personalphoto_item);
                }
            });
        }
    }
}
