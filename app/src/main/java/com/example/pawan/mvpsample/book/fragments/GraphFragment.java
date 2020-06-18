package com.example.pawan.mvpsample.book.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.pawan.mvpsample.BaseFragment;
import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.activities.AddUpdateBookActivity;
import com.wittgroupinc.donutchart.DonutChart;

/**
 * Created by Pawan on 27/01/18.
 */

public class GraphFragment extends BaseFragment {
    private View view;
    private Context mContext;
    private DonutChart donutChart;

    public static GraphFragment getInstance(Bundle bundle) {
        GraphFragment fragment = new GraphFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_graph, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drawChart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void drawChart() {
        donutChart = view.findViewById(R.id.donutchart);
        donutChart.clear();
        donutChart.addSector(getResources().getColor(R.color.one),50);
        donutChart.addSector(getResources().getColor(R.color.two),20);
        donutChart.addSector(getResources().getColor(R.color.three),10);
        donutChart.addSector(getResources().getColor(R.color.four),10);
        donutChart.addSector(getResources().getColor(R.color.five),10);
    }
}
