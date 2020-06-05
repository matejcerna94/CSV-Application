package com.matejcerna.csvapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
        // readWeatherDataByColumn();
    }




    private void readWeatherDataByColumn() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.carrier_plans);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Initialization
        String line = "";

        // Handling exceptions
        try {
            // If buffer is not empty
            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");

                // Print in logcat
//                System.out.println("Column 0 = '" + cols[0] + "', Column 1 = '" + cols[1] + "', Column 2: '" + cols[2] + "'");
                if (cols.length >= 4) {
                    if (cols[3].contains("DSMLTESOC")) {
                        System.out.println("customer = " + cols[0] + ", mbn = " + cols[1] + ", plan: " + cols[2] + ", soc: " + cols[3] + "");
                    }
                }


            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }
    }

    private void loadFirstFile() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.carrier_plans);

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
                Log.d("MyActivity", "Line: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                CarrierPlan carrierPlan = new CarrierPlan();

                // Setters
                carrierPlan.setCustomer(tokens[0]);
                if (tokens.length >= 4) {
                    carrierPlan.setMdn(tokens[1]);
                }
                if (tokens.length >= 4) {
                    carrierPlan.setSprint_plan(tokens[2]);
                }
                if (tokens.length >= 4) {
                    carrierPlan.setSocs(tokens[3]);
                }

                // Adding object to a class
                carrierPlanList.add(carrierPlan);

                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, (ArrayList<CarrierPlan>) carrierPlanList);
                recyclerView.setAdapter(recyclerViewAdapter);


                // Log the object
                Log.d("My Activity", "Just created: " + carrierPlan);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activity", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
    }

    private void loadSecondFile() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.resale_plans);

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
                Log.d("MyActivity", "Line: " + line);
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
                Log.d("My Activity", "Just created: " + resalePlan);
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("My Activity", "Error reading data file on line" + line, e);

            // Prints throwable details
            e.printStackTrace();
        }
        saveData();
    }

    public void saveData() {
        try {
            FileOutputStream file = openFileOutput("SecondFile.csv", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);

            for (int i = 0; i < resalePlanList.size(); i++) {
                outputStreamWriter.write(resalePlanList.get(i).getMdn() + "," + resalePlanList.get(i).getResale_plan() + "/n");
            }
            outputStreamWriter.flush();
            outputStreamWriter.close();
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
