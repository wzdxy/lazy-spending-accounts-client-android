package me.zchi.lazyaccounts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDbHelper extends SQLiteOpenHelper {
    final static int version = 1;
    final static String dbName = "account";
    public AccountDbHelper(Context context) {
        super(context, "accounts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String create_today_plan_sql = "CREATE TABLE [_accounts_list] ("
                + "[_CostDate] char(100),"
                + "[_CostTime] char(100),"
                + "[_Serials] varchar(100),"
                + "[_Cost] integer )";
        db.execSQL(create_today_plan_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table, nullColumnHack, values);
    }
}
