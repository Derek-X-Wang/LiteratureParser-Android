package edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohaps.tldr.summarize.Factory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import edu.ucsb.intbridge.derekxinzhewang.literatureparser.R;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.utils.SharedPreferencesHelper;

/**
 *
 * Created by Derek on 3/17/2016.
 */
public class SummaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        SharedPreferencesHelper preferencesHelper = new SharedPreferencesHelper(getActivity());

        String filePath = preferencesHelper.getString(PdfFragment.KEY_NAME_PDF, "error");
        Log.e("SummaryFragment", "path is "+filePath);
        String extractedText = "error";
        String summary = "error";
        Log.e("SummaryFragment","before parsing");
        try {
            extractedText = extractTextFromPdf(filePath);
            Log.e("SummaryFragment","text extracted");
            summary = Factory.getSummarizer().summarize(extractedText, 7);
            Log.e("SummaryFragment","text converted");
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) v.findViewById(R.id.summary_textview);
        textView.setText(summary);
        return v;
    }

    private String extractTextFromPdf(String filePath) {
        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File(filePath);  // The PDF file from where you would like to extract
            pd = PDDocument.load(input);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1); //Start extracting from page 3
            stripper.setEndPage(pd.getNumberOfPages()); //Extract till page 5
            String text = stripper.getText(pd);
            if (pd != null) {
                pd.close();
            }
            return text;

        } catch (Exception e){
            e.printStackTrace();
        }
        return "error2";
    }
}
