package com.matejcerna.csvapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.matejcerna.csvapplication.model.CarrierPlan;
import com.matejcerna.csvapplication.model.ResalePlan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<CarrierPlan> carrierPlanList;
    private ArrayList<ResalePlan> resalePlanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carrierPlanList = new ArrayList<>();
        resalePlanList = new ArrayList<>();
        loadFirstFile();
        //loadSecondFile();
        //readWeatherDataByColumn();


    }

    private void loadFirstFile() {
        // Read the raw csv file
        InputStream is1 = getResources().openRawResource(R.raw.carrier_plans);
        InputStream is2 = getResources().openRawResource(R.raw.resale_plans);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader1 = new BufferedReader(
                new InputStreamReader(is1, Charset.forName("UTF-8"))
        );

        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(is2, Charset.forName("UTF-8"))
        );

        // Initialization
        String line1 = "";
        String line2 = "";

        // Initialization
        try {
            // Step over headers
            reader1.readLine();
            reader2.readLine();

            // If buffer is not empty
            while ((line1 = reader1.readLine()) != null) {
                Log.d("My Activit first file", "Line: " + line1);
                // use comma as separator columns of CSV
                String[] tokens1 = line1.split(",");
                // Read the data
                CarrierPlan carrierPlan = new CarrierPlan();

                // Setters
                carrierPlan.setCustomer(tokens1[0]);
                carrierPlan.setMdn(Long.parseLong(tokens1[1]));
                carrierPlan.setSprint_plan(tokens1[2]);
                carrierPlan.setSocs(tokens1[3]);
                carrierPlan.save();
                Toast.makeText(this, "Carrier plans successfully saved to database!", Toast.LENGTH_LONG).show();
                // Adding object to a class
                carrierPlanList.add(carrierPlan);

                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, (ArrayList<CarrierPlan>) carrierPlanList);
                recyclerView.setAdapter(recyclerViewAdapter);

                // Log the object
                Log.d("My Activit first file", "Just created: " + carrierPlan);
            }

            while ((line2 = reader2.readLine()) != null) {
                Log.d("My Activit second file", "Line: " + line2);
                // use comma as separator columns of CSV
                String[] tokens2 = line2.split(",");
                // Read the data
                ResalePlan resalePlan = new ResalePlan();
                // Setters

                    resalePlan.setMdn(Long.parseLong(tokens2[0]));


                    resalePlan.setResale_plan(tokens2[1]);


                resalePlan.save();
                // Adding object to a class
                resalePlanList.add(resalePlan);

                //recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, (ArrayList<CarrierPlan>) carrierPlanList);
                // recyclerView.setAdapter(recyclerViewAdapter);


                // Log the object
                Log.d("My Activit second file", "Just created: " + resalePlan);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activit second file", "Error reading data file on line" + line2, e);

            // Prints throwable details
            e.printStackTrace();
        }
    }

    private void loadSecondFile() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.resale);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        // Initialization
        String line = "";

        // Initialization
        try {
            // Step over headers
            reader.readLine();

            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
                Log.d("My Activit second file", "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                ResalePlan resalePlan = new ResalePlan();

                // Setters


                if (tokens.length >= 2) {
                    resalePlan.setMdn(Integer.parseInt(tokens[0]));
                }
                if (tokens.length >= 2) {
                    resalePlan.setResale_plan(tokens[1]);
                }


                // Adding object to a class
                resalePlanList.add(resalePlan);


                // Log the object
                Log.d("My Activit second file", "Just created: " + resalePlan);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activit second file", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
        //saveData();

    }


    @OnClick(R.id.button1)
    public void generateFirstFile() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<FirstFile> firstFile = dataBaseHelper.getDataForFirstFile();
        Toast.makeText(this, firstFile.toString(), Toast.LENGTH_SHORT).show();
        Log.d("everyone1", firstFile.toString());
        try {
            String file_name = "Datoteka1.csv";
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, "Datoteka1.csv");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("MDN,Resale Plan,Sprint Plan,SOCs");
            for (int i = 0; i < firstFile.size(); i++) {
                writer.append("\n" + firstFile.get(i).getMdn() + ","
                        + firstFile.get(i).getResale_plan() + ","
                        + firstFile.get(i).getSprint_plan() + ","
                        + firstFile.get(i).getSocs());
            }

            //generate whatever data you want

            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved in" + root + " with name" + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.button2)
    public void generateSecondFile() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<SecondFile> secondFile = dataBaseHelper.getDataForSecondFile();
        Toast.makeText(this, secondFile.toString(), Toast.LENGTH_LONG).show();
        Log.d("everyone2", secondFile.toString());
        try {
            String file_name = "Datoteka2.csv";
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, "Datoteka2.csv");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("MDN,Resale Plan,Sprint Plan,LTE SOCs");
            for (int i = 0; i < secondFile.size(); i++) {
                writer.append("\n" + secondFile.get(i).getMdn() + ","
                        + secondFile.get(i).getResale_plan() + ","
                        + secondFile.get(i).getSprint_plan() + ","
                        + secondFile.get(i).getLteSocs());
            }
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved in" + root + " with name" + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button3)
    public void getThirdData() {
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
        List<ThirdFile> thirdFile = dataBaseHelper.getDataForThirdFile();
        Toast.makeText(this, thirdFile.toString(), Toast.LENGTH_LONG).show();
        Log.d("everyone2", thirdFile.toString());
        try {
            String file_name = "Datoteka3.csv";
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, file_name);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("Resale Plan,Number Of Devices");
            for (int i = 0; i < thirdFile.size(); i++) {
                writer.append("\n" + thirdFile.get(i).getResale_plan() + ","
                        + thirdFile.get(i).getNumber_of_devices());
            }
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved in" + root + " with name" + file_name, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
