package edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ucsb.intbridge.derekxinzhewang.literatureparser.MainActivity;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.R;

/**
 * Left bar
 * Created by Derek on 3/16/2016.
 */
public class MenuLeftFragment extends Fragment implements View.OnClickListener {

    MainActivity host;
    View tabUpload;
    View tabDemo;
    View tabPdf;
    View tabSummary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_menu_left, container, false);

        initViews(v);
        return v;
    }

    private void initViews(View v) {
        host = (MainActivity)getActivity();

        tabUpload = v.findViewById(R.id.tab_upload);
        tabDemo = v.findViewById(R.id.tab_summary_demo);
        tabPdf = v.findViewById(R.id.tab_pdf);
        tabSummary = v.findViewById(R.id.tab_summary);

        tabUpload.setOnClickListener(this);
        tabDemo.setOnClickListener(this);
        tabPdf.setOnClickListener(this);
        tabSummary.setOnClickListener(this);

        setCurrentTabColor(host.getCurrentTab());
    }

    @Override
    public void onClick(View v) {
        resetTabsColor();
        setCurrentTabColor(v.getId());
        host.onContentFragmentChange(v.getId());
    }

    private void setCurrentTabColor(int id) {
        int currentTabColor = Color.parseColor("#FFDEDEDE");
        switch (id) {
            case R.id.tab_summary_demo:
                tabDemo.setBackgroundColor(currentTabColor);
                break;
            case R.id.tab_pdf:
                tabPdf.setBackgroundColor(currentTabColor);
                break;
            case R.id.tab_summary:
                tabPdf.setBackgroundColor(currentTabColor);
                break;

        }
    }

    private void resetTabsColor() {
        tabDemo.setBackgroundColor(Color.WHITE);
        tabPdf.setBackgroundColor(Color.WHITE);
        tabSummary.setBackgroundColor(Color.WHITE);
    }
}
