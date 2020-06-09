package com.matejcerna.csvapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "baza4.db", null, 1);
    }


    public List<FirstFile> getFilesForFirstFile() {
        List<FirstFile> returnList = new ArrayList<>();

        String query = "SELECT carrier_plans.mdn, resale_plans.resale_plan, carrier_plans.sprint_plan, carrier_plans.socs FROM carrier_plans JOIN resale_plans ON carrier_plans.mdn=resale_plans.mdn";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                long mdn = cursor.getLong(0);
                String resale_plan = cursor.getString(1);
                String sprint_plan = cursor.getString(2);
                String socs = cursor.getString(3);
                FirstFile firstFile = new FirstFile(mdn, resale_plan, sprint_plan, socs);
                returnList.add(firstFile);
            } while (cursor.moveToNext());

        } else {

        }

        cursor.close();
        db.close();

        return returnList;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
