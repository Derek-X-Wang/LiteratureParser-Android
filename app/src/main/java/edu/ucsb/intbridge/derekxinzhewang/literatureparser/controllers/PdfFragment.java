package edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import edu.ucsb.intbridge.derekxinzhewang.literatureparser.R;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.utils.SharedPreferencesHelper;

/**
 *
 * Created by Derek on 3/17/2016.
 */
public class PdfFragment extends Fragment implements OnPageChangeListener {

    public static final String KEY_NAME_PDF = "current_pdf_name";
    private PDFView pdfView;
    private SharedPreferencesHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pdf, container, false);
        pdfView = (PDFView) v.findViewById(R.id.main_pdf);
        helper = new SharedPreferencesHelper(getActivity());
        if (savedInstanceState == null) {
            pdfView.fromAsset("sample.pdf")
                    .defaultPage(1)
                    .onPageChange(this)
                    .swipeVertical(true)
                    .load();
        } else {
            displayPdf();
        }

        return v;
    }

    public void displayPdf() {
        String filePath = helper.getString(KEY_NAME_PDF, "error");
        if (filePath.equals("error")) {
            pdfView.fromAsset("sample.pdf")
                    .defaultPage(1)
                    .onPageChange(this)
                    .swipeVertical(true)
                    .load();
        } else {

            pdfView.fromFile(new File(filePath))
                    .defaultPage(1)
                    .onPageChange(this)
                    .swipeVertical(true)
                    .load();
        }
    }

    public void displayPdf(String filePath) {
        if (filePath.equals("error")) {
            pdfView.fromAsset("sample.pdf")
                    .defaultPage(1)
                    .onPageChange(this)
                    .swipeVertical(true)
                    .load();
        } else {
            pdfView.fromFile(new File(filePath))
                    .defaultPage(1)
                    .onPageChange(this)
                    .swipeVertical(true)
                    .load();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("opened", true);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
