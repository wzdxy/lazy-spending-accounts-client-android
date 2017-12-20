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

    public AddFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        class JsObject {
            @JavascriptInterface
            public String test(){
                Toast.makeText(getContext(),"This is injectedObject",Toast.LENGTH_LONG).show();
                return "This is injectedObject";
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        WebView wv1 = rootView.findViewById(R.id.add_web);

        wv1.setWebViewClient(new WebViewClient());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.addJavascriptInterface(new JsObject(),"java");
        wv1.loadUrl("file:///android_asset/add/index.html");

        wv1.evaluateJavascript("newHtml()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                if(s!=null)Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
}
