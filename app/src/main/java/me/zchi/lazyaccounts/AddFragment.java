package me.zchi.lazyaccounts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


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
            /**
             * 从 js 保存账单到数据库
             * @param  s 发送的json文件
             * @return Boolean 是否保存成功
             */
            @JavascriptInterface
            public Boolean addAccount(String s){
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                AccountDbHelper db=new AccountDbHelper(getContext());
                ContentValues c = new ContentValues();
                try {
                    JSONObject json = new JSONObject(s);
                    c.put("[_CostDate]",json.getString("costDate"));
                    c.put("[_CostTime]",json.getString("costTime"));
                    c.put("[_Serials]",json.getString("serials"));
                    c.put("[_Cost]",json.getInt("cost"));
                }catch (JSONException e){
                    Log.d("JSON", "addAccount: "+e.getMessage());
                    return false;
                }
                db.insert("_accounts_list",null,c);
                return true;
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        wv1 = rootView.findViewById(R.id.add_web);

        wv1.setWebViewClient(new WebViewClient());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.addJavascriptInterface(new JsObject(),"native");
        wv1.loadUrl("file:///android_asset/html/add/index.html");

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
