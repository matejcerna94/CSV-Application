package com.matejcerna.csvapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.matejcerna.csvapplication.model.CarrierPlan;
import com.matejcerna.csvapplication.model.FirstFile;
import com.matejcerna.csvapplication.model.ResalePlan;
import com.matejcerna.csvapplication.model.SecondFile;
import com.matejcerna.csvapplication.model.ThirdFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CarrierPlan> carrierPlanList;
    private ArrayList<ResalePlan> resalePlanList;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, data is loading!");

        carrierPlanList = new ArrayList<>();
        resalePlanList = new ArrayList<>();
    }

    private void loadFirstFile() {
        // Read the raw csv file
        InputStream inputStream = getResources().openRawResource(R.raw.carrier_plans);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );
        // Initialization
        String line = "";

        try {
            // Step over headers
            reader.readLine();

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.d("My Activity first file", "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                CarrierPlan carrierPlan = new CarrierPlan();
                // Setters
                carrierPlan.setCustomer(tokens[0]);
                carrierPlan.setMdn(Long.parseLong(tokens[1]));
                carrierPlan.setSprint_plan(tokens[2]);
                carrierPlan.setSocs(tokens[3]);
                carrierPlan.save();

                // Adding object to a class
                carrierPlanList.add(carrierPlan);

                // Log the object
                Log.d("My Activity first file", "Just created: " + carrierPlan);
            }
        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activity second file", "Error reading data file on line" + line, e);
            // Prints throwable details
            e.printStackTrace();
        }
    }

    private void loadSecondFile() {
        // Read the raw csv file
        InputStream inputStream = getResources().openRawResource(R.raw.resale_plans);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        // Initialization
        String line = "";

        // Initialization
        try {
            // Step over headers
            reader.readLine();

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.d("My Activity second file", "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                ResalePlan resalePlan = new ResalePlan();
                // Setters
                resalePlan.setMdn(Long.parseLong(tokens[0]));
                resalePlan.setResale_plan(tokens[1]);
                resalePlan.save();
                // Adding object to a class
                resalePlanList.add(resalePlan);
                // Log the object
                Log.d("My Activity second file", "Just created: " + resalePlan);
            }
        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activity second file", "Error reading data file on line" + line, e);
            // Prints throwable details
            e.printStackTrace();
        }
    }


    @OnClick(R.id.button1)
    public void generateFirstFile() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<FirstFile> firstFile = dataBaseHelper.getDataFromDatabaseForFirstFile();
        Toast.makeText(this, firstFile.toString(), Toast.LENGTH_SHORT).show();
        try {
            String file_name = "Datoteka1.csv";
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, file_name);
            FileWriter writer = new FileWriter(file);
            writer.append("MDN,Resale Plan,Sprint Plan,SOCs");
            for (int i = 0; i < firstFile.size(); i++) {
                writer.append("\n" + firstFile.get(i).getMdn() + ","
                        + firstFile.get(i).getResale_plan() + ","
                        + firstFile.get(i).getSprint_plan() + ","
                        + firstFile.get(i).getSocs());
            }
            writer.flush();
            writer.close();
            Toast.makeText(this, "First file saved in" + root + " with name " + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.button2)
    public void generateSecondFile() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<SecondFile> secondFile = dataBaseHelper.getDataFromDatabaseForSecondFile();
        Toast.makeText(this, secondFile.toString(), Toast.LENGTH_LONG).show();
        try {
            String file_name = "Datoteka2.csv";
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, file_name);
            FileWriter writer = new FileWriter(file);
            writer.append("MDN,Resale Plan,Sprint Plan,LTE SOCs");
            for (int i = 0; i < secondFile.size(); i++) {
                writer.append("\n" + secondFile.get(i).getMdn() + ","
                        + secondFile.get(i).getResale_plan() + ","
                        + secondFile.get(i).getSprint_plan() + ","
                        + secondFile.get(i).getLteSocs());
            }
            writer.flush();
            writer.close();
            Toast.makeText(this, "Second file saved in" + root + " with name " + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button3)
    public void generateThirdFile() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<ThirdFile> thirdFile = dataBaseHelper.getDataFromDatabaseForThirdFile();
        Toast.makeText(this, thirdFile.toString(), Toast.LENGTH_LONG).show();
        try {
            String file_name = "Datoteka3.csv";
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, file_name);
            FileWriter writer = new FileWriter(file);
            writer.append("Resale Plan,Number Of Devices");
            for (int i = 0; i < thirdFile.size(); i++) {
                writer.append("\n" + thirdFile.get(i).getResale_plan() + ","
                        + thirdFile.get(i).getNumber_of_devices());
            }
            writer.flush();
            writer.close();
            Toast.makeText(this, "Third file saved in" + root + " with name " + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button_load_first_file)
    public void loadFirstFileButton() {
        new LoadFirstFileClass().execute();
    }

    @OnClick(R.id.button_load_second_file)
    public void loadSecondFileButton() {
        new LoadSecondFileClass().execute();
    }

    private class LoadFirstFileClass extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadFirstFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "First file successfully loaded!", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }

    private class LoadSecondFileClass extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadSecondFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Second file successfully loaded!", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}
