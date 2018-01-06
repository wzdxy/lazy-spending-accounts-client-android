package me.zchi.lazyaccounts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    public WebView wv1;

    public AccountFragment() {
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

            @JavascriptInterface
            public String deleteAccount(){
                AccountDbHelper db=new AccountDbHelper(getContext());
                return "";
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        wv1 = rootView.findViewById(R.id.account_web);
        wv1.addJavascriptInterface(new JsObject(),"native");
        wv1.loadUrl("file:///android_asset/html/account/index.html");
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

    public void reFreshAccounts(){
        Log.d("Account","Before evaluateJavascript");
        try {
            wv1.evaluateJavascript("refreshList()",new ValueCallback<String>() {
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
