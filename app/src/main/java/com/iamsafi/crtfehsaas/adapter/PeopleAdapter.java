package com.iamsafi.crtfehsaas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iamsafi.crtfehsaas.R;
import com.iamsafi.crtfehsaas.database.Person;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    Context context;
    List<Person> mPersonList;
    LayoutInflater layoutInflater;

    public PeopleAdapter(Context context, List<Person> mPersonList) {
        this.context = context;
        this.mPersonList = mPersonList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.person_record_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_name.setText(mPersonList.get(position).getFull_Name());
        holder.txt_cnic.setText(mPersonList.get(position).getCNIC());
        holder.txt_issuedate.setText(mPersonList.get(position).getCNIC_Issue_Date());
        holder.txt_gender.setText(mPersonList.get(position).getGender() + ", " + mPersonList.get(position).getMartial_Status());
        holder.txt_mobile.setText(mPersonList.get(position).getMobile());
//        if (mPersonList.get(position).getRegistration_Status().equalsIgnoreCase("UnRegistered")) {
//            holder.txt_unregister.setVisibility(View.VISIBLE);
//            holder.txt_register.setVisibility(View.GONE);
//        } else {
//            holder.txt_unregister.setVisibility(View.GONE);
//            holder.txt_register.setVisibility(View.VISIBLE);
//        }
    }

    public Person getPersonAtPosition(int position) {
        return mPersonList.get(position);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        TextView txt_cnic;
        TextView txt_issuedate;
        TextView txt_gender;
        TextView txt_mobile;
        TextView txt_register;
        TextView txt_unregister;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.tv_name);
            txt_cnic = itemView.findViewById(R.id.tv_cnic);
            txt_issuedate = itemView.findViewById(R.id.tvissuedate);
            txt_gender = itemView.findViewById(R.id.tvgender);
            txt_mobile = itemView.findViewById(R.id.tv_mobile);
            txt_register = itemView.findViewById(R.id.tvregister);
            txt_unregister = itemView.findViewById(R.id.tvunregister);

        }
    }

}
