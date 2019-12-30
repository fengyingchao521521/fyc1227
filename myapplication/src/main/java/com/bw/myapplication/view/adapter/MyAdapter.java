package com.bw.myapplication.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.myapplication.R;
import com.bw.myapplication.model.bean.JavaBean;

import java.util.List;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:20:11
 *@Description:${DESCRIPTION}
 *
 */public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHadel> {


    private List<JavaBean.DataBean> list;

    public MyAdapter(List<JavaBean.DataBean> data) {

        this.list = data;
    }

    @NonNull
    @Override
    public MyViewHadel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyViewHadel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHadel holder, int position) {
        //绑定
        JavaBean.DataBean dataBean = list.get(position);
        holder.text1.setText(dataBean.getGoods_name());
        holder.text2.setText(dataBean.getCurrency_price() + "");
        Glide.with(holder.image).load(dataBean.getGoods_thumb())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClickListener.onitemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHadel extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text1;
        TextView text2;

        public MyViewHadel(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);

        }
    }


    onitemClickListener onitemClickListener;

    public void setOnitemClickListener(MyAdapter.onitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public interface onitemClickListener {
        void onitemClick(int postion);
    }
}
