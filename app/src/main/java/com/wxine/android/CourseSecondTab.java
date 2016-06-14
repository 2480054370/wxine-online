package com.wxine.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxine_online.wxine_online.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CourseSecondTab extends Fragment {

    private CourseSecondAdapter mAdapter;

    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_third_recyclerview, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        String[] listItems = mItemData.split(" ");

        List<String> list = new ArrayList<String>();
        Collections.addAll(list, listItems);

        mAdapter = new CourseSecondAdapter(list);
        recyclerView.setAdapter(mAdapter);

        return view;

    }
}
