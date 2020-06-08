package com.matejcerna.csvapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.matejcerna.csvapplication.model.CarrierPlan;
import com.matejcerna.csvapplication.model.ResalePlan;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

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








    private void readWeatherDataByColumn() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.pretplatnici);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Initialization
        String line = "";

        // Handling exceptions
        try {
            br.readLine();
            // If buffer is not empty
            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");

                // Print in logcat
//                System.out.println("Column 0 = '" + cols[0] + "', Column 1 = '" + cols[1] + "', Column 2: '" + cols[2] + "'");
                if (cols.length >= 4) {
                   // if (cols[3].contains("DSMLTESOC")) {
                        System.out.println("customerr = " + cols[0] + ", mbn = " + cols[1] + ", plan: " + cols[2] + ", soc: " + cols[3] + "");
                    //}
                }


            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }
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
              /*  carrierPlan.setCustomer(tokens1[0]);
                if (tokens1.length >= 4) {
                    carrierPlan.setMdn(tokens1[1]);
                }
                if (tokens1.length >= 4) {
                    carrierPlan.setSprint_plan(tokens1[2]);
                }
                if (tokens1.length >= 4) {
                    carrierPlan.setSocs(tokens1[3]);
                }*/

                carrierPlan.setCustomer(tokens1[0]);

                    carrierPlan.setMdn(tokens1[1]);


                    carrierPlan.setSprint_plan(tokens1[2]);


                    carrierPlan.setSocs(tokens1[3]);


                carrierPlan.save();
                Toast.makeText(this, "Carrier plans successfully saved to database!", Toast.LENGTH_SHORT).show();
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
                if (tokens2.length >= 2) {
                    resalePlan.setMdn(tokens2[0]);
                }
                if (tokens2.length >= 2) {
                    resalePlan.setResale_plan(tokens2[1]);
                }

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
                    resalePlan.setMdn(tokens[0]);
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


}
