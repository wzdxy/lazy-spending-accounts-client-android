package me.zchi.lazyaccounts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private WebView wv1;

    public AddFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        class JsObject {
            @JavascriptInterface
            public Boolean addAccount(String s){
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                return true;
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        wv1 = rootView.findViewById(R.id.add_web);

        wv1.setWebViewClient(new WebViewClient());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.addJavascriptInterface(new JsObject(),"native");
        wv1.loadUrl("file:///android_asset/html_dist/add/index.html");

        wv1.evaluateJavascript("newHtml()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                if(s!=null)Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }
        });


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
