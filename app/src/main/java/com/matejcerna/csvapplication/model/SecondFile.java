package com.matejcerna.csvapplication.model;

public class SecondFile {
    long mdn;
    String resale_plan;
    String sprint_plan;
    String lteSocs;

    public SecondFile(long mdn, String resale_plan, String sprint_plan, String lteSocs) {
        this.mdn = mdn;
        this.resale_plan = resale_plan;
        this.sprint_plan = sprint_plan;
        this.lteSocs = lteSocs;
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

    public String getLteSocs() {
        return lteSocs;
    }

    public void setLteSocs(String lteSocs) {
        this.lteSocs = lteSocs;
    }

    @Override
    public String toString() {
        return "SecondFile{" +
                "mdn=" + mdn +
                ", resale_plan='" + resale_plan + '\'' +
                ", sprint_plan='" + sprint_plan + '\'' +
                ", lteSocs='" + lteSocs + '\'' +
                '}';
    }
}
