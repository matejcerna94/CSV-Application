package com.matejcerna.csvapplication.model;

public class ThirdFile {
    String resale_plan;
    int number_of_devices;

    public ThirdFile(String resale_plan, int number_of_devices) {
        this.resale_plan = resale_plan;
        this.number_of_devices = number_of_devices;
    }

    public String getResale_plan() {
        return resale_plan;
    }

    public void setResale_plan(String resale_plan) {
        this.resale_plan = resale_plan;
    }

    public int getNumber_of_devices() {
        return number_of_devices;
    }

    public void setNumber_of_devices(int number_of_devices) {
        this.number_of_devices = number_of_devices;
    }

    @Override
    public String toString() {
        return "ThirdFile{" +
                "resale_plan='" + resale_plan + '\'' +
                ", number_of_devices=" + number_of_devices +
                '}';
    }
}
