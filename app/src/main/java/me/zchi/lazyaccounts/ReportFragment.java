package me.zchi.lazyaccounts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    private WebView wv1;

    public ReportFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        WebView.setWebContentsDebuggingEnabled(true);
        wv1 = rootView.findViewById(R.id.report_web);
        wv1.loadUrl("file:///android_asset/html/report/index.html");
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setWebViewClient(new WebViewClient());
        return rootView;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        wv1.destroy();
        wv1.removeAllViews();
        wv1.clearHistory();
        wv1=null;
    }
}
