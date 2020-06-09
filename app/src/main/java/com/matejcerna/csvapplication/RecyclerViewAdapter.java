package com.matejcerna.csvapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matejcerna.csvapplication.model.CarrierPlan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<CarrierPlan> carrierPlansList;

    public RecyclerViewAdapter(Context context, ArrayList<CarrierPlan> carrierPlansList) {
        this.context = context;
        this.carrierPlansList = carrierPlansList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_content_orders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CarrierPlan carrierPlan = carrierPlansList.get(position);

        final String order_id = carrierPlan.getCustomer();
        final String item_name = String.valueOf(carrierPlan.getMdn());
        final String amount = carrierPlan.getSprint_plan();
        final String single_item_price = carrierPlan.getSocs();



        holder.textView1.setText(order_id);
        holder.textView2.setText(item_name);
        holder.textView3.setText(amount);
        holder.textView4.setText(single_item_price);

    }

    @Override
    public int getItemCount() {
        return carrierPlansList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView textView1;
        @BindView(R.id.tv2)
        TextView textView2;
        @BindView(R.id.tv3)
        TextView textView3;
        @BindView(R.id.tv4)
        TextView textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
