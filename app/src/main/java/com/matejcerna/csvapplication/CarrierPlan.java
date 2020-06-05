package com.matejcerna.csvapplication;

public class CarrierPlan {

    String customer;
    String mdn;
    String sprint_plan;
    String socs;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
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
        return "User{" +
                "customer='" + customer + '\'' +
                ", mdn='" + mdn + '\'' +
                ", sprint_plan='" + sprint_plan + '\'' +
                ", socs='" + socs + '\'' +
                '}';
    }
}
