package edu.ucsb.intbridge.derekxinzhewang.literatureparser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.net.URISyntaxException;

import edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers.DemoFragment;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers.PdfFragment;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.controllers.SummaryFragment;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.utils.FileUtils;
import edu.ucsb.intbridge.derekxinzhewang.literatureparser.utils.SharedPreferencesHelper;

public class MainActivity extends Activity {

    private final int REQ_CODE_PICK_PDF = 1;

    private PdfFragment pdfFragment;
    private DemoFragment demoFragment;
    private SummaryFragment summaryFragment;
    private DrawerLayout drawerLayout;

    private int currentTab = R.id.tab_pdf;
    private SharedPreferencesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfFragment = new PdfFragment();
        demoFragment = new DemoFragment();
        summaryFragment =  new SummaryFragment();

        helper = new SharedPreferencesHelper(this);
        initFragments();
        initDrawer();
    }



    private void initFragments() {
        getFragmentManager().beginTransaction().add(R.id.fragment_content, pdfFragment)
                .commit();
    }

    public void onContentFragmentChange(int id) {
        switch (id){
            case R.id.tab_pdf:
                currentTab = R.id.tab_pdf;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, pdfFragment)
                        .commit();
                break;
            case R.id.tab_summary_demo:
                currentTab = R.id.tab_summary_demo;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, demoFragment)
                        .commit();
                break;
            case R.id.tab_summary:
                currentTab = R.id.tab_summary;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, summaryFragment)
                        .commit();
                break;
//            case R.id.tab_bus:
//                currentTab = R.id.tab_bus;
//                getFragmentManager().beginTransaction()
//                        .show(busFragment)
//                        .commit();
//                break;
//            case R.id.tab_settings:
//                //currentTab = R.id.tab_settings;
//                getFragmentManager().beginTransaction()
//                        .show(settingsFragment)
//                        .commit();
//                break;
            case R.id.tab_upload:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, REQ_CODE_PICK_PDF);
                break;
//            case R.id.tab_feedback:
//                //currentTab = R.id.tab_feedback;
//                getFragmentManager().beginTransaction()
//                        .show(feedbackFragment)
//                        .commit();
//                break;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }

    }

    private void initDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        View menuLeft = findViewById(R.id.main_menu_left);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.7);
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) menuLeft.getLayoutParams();
        params.width = width;
    }


    public int getCurrentTab() {
        return currentTab;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQ_CODE_PICK_PDF) {
            //Log.e("debug", "onActivityResult");
            Uri fileUri = data.getData();
            String path = FileUtils.getPath(this,fileUri);

            Log.e("Main", "The file uri is : " + fileUri.toString());
            Log.e("Main", "The file path is : " + path);
            helper.putString(PdfFragment.KEY_NAME_PDF, path);
            onContentFragmentChange(R.id.tab_pdf);
            pdfFragment.displayPdf();
        }
    }


}
