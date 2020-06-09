package com.matejcerna.csvapplication;

public class FirstFile {
    long mdn;
    String resale_plan;
    String sprint_plan;
    String socs;

    public FirstFile(long mdn, String resale_plan, String sprint_plan, String socs) {
        this.mdn = mdn;
        this.resale_plan = resale_plan;
        this.sprint_plan = sprint_plan;
        this.socs = socs;
    }

    public long getMdn() {
        return mdn;
    }

    public void setMdn(long mdn) {
        this.mdn = mdn;
    }

    public String getResale_plan() {
        return resale_plan;
    }

    public void setResale_plan(String resale_plan) {
        this.resale_plan = resale_plan;
    }

    public String getSprint_plan() {
        return sprint_plan;
    }

    public void setSprint_plan(String sprint_plan) {
        this.sprint_plan = sprint_plan;
    }

    public String getSocs() {
        return socs;
    }

    public void setSocs(String socs) {
        this.socs = socs;
    }

    @Override
    public String toString() {
        return "FirstFile{" +
                "mdn=" + mdn +
                ", resale_plan='" + resale_plan + '\'' +
                ", sprint_plan='" + sprint_plan + '\'' +
                ", socs='" + socs + '\'' +
                '}';
    }
}
