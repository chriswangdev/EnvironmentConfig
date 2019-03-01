package cn.qsign.environconfigview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Administrator on 2018/10/8 0008.
 */

public class EnvironmentAdapter<T extends EnvironBean> extends RecyclerView.Adapter<EnvironmentAdapter.ViewHolder> {

    private List<T> list;
    private OnClickedListener listener;

    public EnvironmentAdapter(List<T> list, OnClickedListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public EnvironmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_adapter, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EnvironmentAdapter.ViewHolder holder, int position) {
        final EnvironBean bean = list.get(position);
        holder.textView.setText(bean.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClicked(bean);
            }
        });
//        if (position == list.size() - 1){
//            holder.viewLine.setVisibility(View.GONE);
//        } else {
//            holder.viewLine.setVisibility(View.VISIBLE);
//        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        //View viewLine;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.environ_view_tv_item_pop_adapter);
            //viewLine = itemView.findViewById(R.id.environ_view_line);
        }
    }

    public interface OnClickedListener {
        void onClicked(EnvironBean bean);
    }

}
//public class EnvironmentAdapter<T> extends RecyclerView.Adapter<EnvironmentAdapter.ViewHolder> {
//
//    private List<T> list;
//    private OnClickedListener listener;
//
//    public EnvironmentAdapter(List<T> list, OnClickedListener listener) {
//        this.list = list;
//        this.listener = listener;
//    }
//
//    @Override
//    public EnvironmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_adapter, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(EnvironmentAdapter.ViewHolder holder, int position) {
//        final EnvironmentBean bean = list.get(position);
//        holder.textView.setText(bean.getName());
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null)
//                    listener.onClicked(bean);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//        ViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.tv_item_pop_adapter);
//        }
//    }
//
//    public interface OnClickedListener {
//        void onClicked(EnvironmentBean bean);
//    }
//
//}
