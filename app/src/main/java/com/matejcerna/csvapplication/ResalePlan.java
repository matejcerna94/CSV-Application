package com.matejcerna.csvapplication;

public class ResalePlan {
    String mdn;
    String resale_plan;

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getResale_plan() {
        return resale_plan;
    }

    public void setResale_plan(String resale_plan) {
        this.resale_plan = resale_plan;
    }

    @Override
    public String toString() {
        return "ResalePlan{" +
                "mdn='" + mdn + '\'' +
                ", resale_plan='" + resale_plan + '\'' +
                '}';
    }
}
