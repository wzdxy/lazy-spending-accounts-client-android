package me.zchi.lazyaccounts;

import android.annotation.SuppressLint;
import android.database.Cursor;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    public WebView wv1;

    public ReportFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        class JsObject {
            @JavascriptInterface
            public String getAllAccounts(){
                AccountDbHelper db=new AccountDbHelper(getContext());
                Cursor cursor = db.search(null);
                ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        JSONObject acc=new JSONObject();
                        try {
                            acc.put("id",cursor.getInt(cursor.getColumnIndex("_Id")));
                            acc.put("cost",cursor.getInt(cursor.getColumnIndex("_Cost")));
                            acc.put("date",cursor.getString(cursor.getColumnIndex("_CostDate")));
                            acc.put("time",cursor.getString(cursor.getColumnIndex("_CostTime")));
                            acc.put("serial",cursor.getString(cursor.getColumnIndex("_Serials")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        list.add(acc);
                        cursor.moveToNext();
                    }
                }
                JSONArray json = new JSONArray(list);
                return json.toString();
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        WebView.setWebContentsDebuggingEnabled(true);
        wv1 = rootView.findViewById(R.id.report_web);
        wv1.loadUrl("file:///android_asset/html/report/index.html");
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setWebViewClient(new WebViewClient());
        wv1.addJavascriptInterface(new JsObject(),"native");
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

    public void reFreshReport(){
        try {
            wv1.evaluateJavascript("refreshReport()",new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Log.d("Account","onReceiveValue");
                    if(s!=null)Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.e("Eval Js ReFreshAccounts",e.toString());
        }
    }
}
