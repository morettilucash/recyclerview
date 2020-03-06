package com.example.calculadora.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.calculadora.Models.Posts;
import com.example.calculadora.R;

import java.util.ArrayList;

public class ListaDatosDosAdapter extends RecyclerView.Adapter<ListaDatosDosAdapter.ViewHolder> {

    ArrayList<Posts> listdata;

    public ListaDatosDosAdapter(ArrayList<Posts> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ListaDatosDosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fila_post2, parent, false);
        ListaDatosDosAdapter.ViewHolder viewHolder = new ListaDatosDosAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView tvId, tvUserId, tvTitle, tvBody;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvId = (TextView) itemView.findViewById(R.id.id);
            this.tvUserId = (TextView) itemView.findViewById(R.id.userId);
            this.tvTitle = (TextView) itemView.findViewById(R.id.title);
            this.tvBody = (TextView) itemView.findViewById(R.id.body);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvId.setText(Integer.toString(listdata.get(position).getId()));
        holder.tvUserId.setText(Integer.toString(listdata.get(position).getUserId()));
        holder.tvTitle.setText(listdata.get(position).getTitle());
        holder.tvBody.setText(listdata.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
