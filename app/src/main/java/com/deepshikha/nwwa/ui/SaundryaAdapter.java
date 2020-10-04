package com.deepshikha.nwwa.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepshikha.nwwa.R;
import com.deepshikha.nwwa.model.SaundryaServices;

import java.util.List;

public class SaundryaAdapter extends RecyclerView.Adapter<SaundryaAdapter.itemViewHolder>  {
    List<SaundryaServices> list;
Context ctx;
    private static ItemClickListener mClickListener;

    public SaundryaAdapter(List<SaundryaServices> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.saundrya_services_items,parent,false);
        return new itemViewHolder(view,mClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        final SaundryaServices  pl = list.get(position);
        holder.itemTitle.setText(pl.getTitle());
        holder.st1.setText(pl.getSt1());
        holder.st2.setText(pl.getSt2());
        holder.st3.setText(pl.getSt3());
        holder.st4.setText(pl.getSt4());
        holder.st5.setText(pl.getSt5());
        holder.st6.setText(pl.getSt6());
        holder.st7.setText(pl.getSt7());
        holder.st8.setText(pl.getSt8());

          if(holder.cb1.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt1());
  if(holder.cb2.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt2());
  if(holder.cb3.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt3());
  if(holder.cb4.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt4());
  if(holder.cb5.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt5());
  if(holder.cb6.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt6());
  if(holder.cb7.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt7());
  if(holder.cb8.isEnabled())
              Saundrya.listOfSrevces.add(pl.getSt8());
//hiding chkboxes with epty strings
        if(holder.st3.getText().equals("")){
            holder.cb3.setVisibility(View.GONE);
            holder.st3.setVisibility(View.GONE);
        }
  if(holder.st4.getText().equals("")){
            holder.cb4.setVisibility(View.GONE);
            holder.st4.setVisibility(View.GONE);
        }
  if(holder.st5.getText().equals("")){
            holder.cb5.setVisibility(View.GONE);
            holder.st5.setVisibility(View.GONE);
        }
  if(holder.st6.getText().equals("")){
            holder.cb6.setVisibility(View.GONE);
            holder.st6.setVisibility(View.GONE);
        }
  if(holder.st7.getText().equals("")){
            holder.cb7.setVisibility(View.GONE);
            holder.st7.setVisibility(View.GONE);
        }
  if(holder.st8.getText().equals("")){
            holder.cb8.setVisibility(View.GONE);
            holder.st8.setVisibility(View.GONE);
        }

    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    TextView st1,st2,st3,st4,st5,st6,st7,st8,itemTitle;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
        ItemClickListener itemClickListener;

        public itemViewHolder(@NonNull View itemView, ItemClickListener mClickListener) {
        super(itemView);
        l1= itemView.findViewById(R.id.l1);
        l2= itemView.findViewById(R.id.l2);
        l3= itemView.findViewById(R.id.l3);
        l4= itemView.findViewById(R.id.l4);
        l5= itemView.findViewById(R.id.l5);
        l6= itemView.findViewById(R.id.l6);
        l7= itemView.findViewById(R.id.l7);
        l8= itemView.findViewById(R.id.l8);


        st1= itemView.findViewById(R.id.st1);
        st2= itemView.findViewById(R.id.st2);
        st3= itemView.findViewById(R.id.st3);
        st4= itemView.findViewById(R.id.st4);
        st5= itemView.findViewById(R.id.st5);
        st6= itemView.findViewById(R.id.st6);
        st7= itemView.findViewById(R.id.st7);
        st8= itemView.findViewById(R.id.st8);
        itemTitle = itemView.findViewById(R.id.Itemtitle);


        cb1= itemView.findViewById(R.id.cb1);  cb1.setOnClickListener(this);
        cb2= itemView.findViewById(R.id.cb2);cb2.setOnClickListener(this);
        cb3= itemView.findViewById(R.id.cb3);cb3.setOnClickListener(this);
        cb4= itemView.findViewById(R.id.cb4);cb4.setOnClickListener(this);
        cb5= itemView.findViewById(R.id.cb5);cb5.setOnClickListener(this);
        cb6= itemView.findViewById(R.id.cb6);cb6.setOnClickListener(this);
        cb7= itemView.findViewById(R.id.cb7);cb7.setOnClickListener(this);
        cb8= itemView.findViewById(R.id.cb8);cb8.setOnClickListener(this);

        this.itemClickListener = mClickListener;




    }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
        }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
