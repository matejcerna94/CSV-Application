package com.matejcerna.csvapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table(name ="resale_plans")
public class ResalePlan extends SugarRecord {

    @Column(name = "mdn")
    String mdn;

    @Column(name = "resale_plan")
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
