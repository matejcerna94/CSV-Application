package com.matejcerna.csvapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.matejcerna.csvapplication.model.FirstFile;
import com.matejcerna.csvapplication.model.SecondFile;
import com.matejcerna.csvapplication.model.ThirdFile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context) {
        super(context, "csv_database.db", null, 1);
    }


    public List<FirstFile> getDataForFirstFile() {
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
                Log.d("List size 1", String.valueOf(returnList.size()));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "No data in database!", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<SecondFile> getDataForSecondFile() {
        List<SecondFile> returnList = new ArrayList<>();

        String query = "SELECT carrier_plans.mdn, resale_plans.resale_plan, carrier_plans.sprint_plan, carrier_plans.socs FROM carrier_plans JOIN resale_plans ON carrier_plans.mdn=resale_plans.mdn";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                long mdn = cursor.getLong(0);
                String resale_plan = cursor.getString(1);
                String sprint_plan = cursor.getString(2);
                String socs = cursor.getString(3);
                if (socs.contains("DSMLTESOC")){
                    socs = "Y";
                }else {
                    socs = "N";
                }
                SecondFile secondFile = new SecondFile(mdn, resale_plan, sprint_plan, socs);
                returnList.add(secondFile);
                Log.d("List size 2", String.valueOf(returnList.size()));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "No data in database!", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<ThirdFile> getDataForThirdFile() {
        List<ThirdFile> returnList = new ArrayList<>();

        String query = "SELECT resale_plans.resale_plan, COUNT(resale_plan) FROM resale_plans GROUP BY resale_plan";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String resale_plan = cursor.getString(0);
                int number_of_devices = cursor.getInt(1);
                ThirdFile thirdFile = new ThirdFile(resale_plan, number_of_devices);
                returnList.add(thirdFile);
                Log.d("List size 3", String.valueOf(returnList.size()));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "No data in database!", Toast.LENGTH_SHORT).show();
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
