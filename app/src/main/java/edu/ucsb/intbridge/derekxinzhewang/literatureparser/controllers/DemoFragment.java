package edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mohaps.tldr.summarize.Factory;

import java.io.IOException;

import edu.ucsb.intbridge.derekxinzhewang.literatureparser.R;

/**
 *
 * Created by Derek on 3/17/2016.
 */
public class DemoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_demo, container, false);

        initViews(v);
        return v;
    }

    private void initViews(View v) {
        Button button = (Button) v.findViewById(R.id.demo_button);
        final TextView textView1 = (TextView) v.findViewById(R.id.demo_textView);
        final TextView textView2 = (TextView) v.findViewById(R.id.demo_textView_result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textView1.getText().toString();
                String summaryText = null;
                try {
                    summaryText = Factory.getSummarizer().summarize(input,
                            3);
                    textView2.setText(summaryText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
