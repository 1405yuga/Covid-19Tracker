package com.example.covid_19tracker;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.vHolder>{

    private List<Model> casesList;

    public myAdapter(List<Model> casesList) {
        this.casesList = casesList;
    }

    public myAdapter() {
    }

    public static class vHolder extends RecyclerView.ViewHolder {
        public TextView vstatename,vdistrict,vactive,vconfirm,vdeceased,vrecover;
        public LinearLayout vhide;
        public ImageView vimgbutton;
        public CardView vcardview;
        public vHolder(View itemView) {
            super(itemView);
            vstatename=itemView.findViewById(R.id.statename);
            vdistrict=itemView.findViewById(R.id.district);
            vactive=itemView.findViewById(R.id.Cactive);
            vconfirm=itemView.findViewById(R.id.Cconfirm);
            vdeceased=itemView.findViewById(R.id.Cdecrease);
            vrecover=itemView.findViewById(R.id.Crecover);
            vhide=itemView.findViewById(R.id.Hide);
            vimgbutton=itemView.findViewById(R.id.imgButton);
            vcardview=itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public myAdapter.vHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.testing,parent,false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder( myAdapter.vHolder holder, int position) {
        Model currentModel=casesList.get(position);
        holder.vstatename.setText(currentModel.getState());
        holder.vdistrict.setText(currentModel.getDist());
        holder.vactive.setText(currentModel.getActive());
        holder.vconfirm.setText(currentModel.getConfirmed());
        holder.vdeceased.setText(currentModel.getDeceased());
        holder.vrecover.setText(currentModel.getRecovered());
        holder.vhide.setVisibility(View.GONE);
        holder.vimgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if expanded
                if(holder.vhide.getVisibility()==View.VISIBLE){
                    TransitionManager.beginDelayedTransition(holder.vcardview,new AutoTransition());
                    holder.vhide.setVisibility(View.GONE);
                    holder.vimgbutton.setImageResource(R.drawable.ic_baseline_arrow_downward_24);

                }
                //if not expanded
                else{

                    TransitionManager.beginDelayedTransition(holder.vcardview,new AutoTransition());
                    holder.vhide.setVisibility(View.VISIBLE);
                    holder.vimgbutton.setImageResource(R.drawable.ic_baseline_arrow_upward_24);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return casesList.size();
    }


}
