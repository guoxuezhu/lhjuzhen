package com.lh.lhjuzhen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.data.model.VideoIP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IPAdapter extends RecyclerView.Adapter<IPAdapter.IPViewHolder> {

    private Context mContext;
    private List<VideoIP> datas;
    private CallBack mCallBack;

    public IPAdapter(Context context, List<VideoIP> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public IPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ip_item, parent, false);
        return new IPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IPViewHolder holder, int position) {
        VideoIP videoIP = datas.get(position);
        holder.tv_name.setText(videoIP.name);
        holder.tv_out_name.setText(videoIP.outName);
        holder.tv_strIp.setText(videoIP.strIp);

        holder.setItem(videoIP);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<VideoIP> videoIPS) {
        datas = videoIPS;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItem(VideoIP item);
    }

    public class IPViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_out_name)
        TextView tv_out_name;
        @BindView(R.id.tv_strIp)
        TextView tv_strIp;

        private VideoIP item;


        public IPViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(VideoIP data) {
            item = data;
        }

        @OnClick(R.id.tv_strIp)
        public void tv_strIp() {
            mCallBack.onClickItem(item);
        }

    }
}
