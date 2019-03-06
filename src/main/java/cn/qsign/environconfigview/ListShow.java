package cn.qsign.environconfigview;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15 0015.
 */

public class ListShow<T extends EnvironBean> {

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private View tvChange;
    private List<T> listEnvironment;
    private OnMyItemClickListener listener;

    public ListShow(View tvChange, List<T> listEnvironment, OnMyItemClickListener listener) {
        this.tvChange = tvChange;
        this.listEnvironment = listEnvironment;
        this.listener = listener;
    }

    public void showChangeList(final Activity activity) {
        if (listEnvironment.size() == 0) {
            Toast.makeText(activity, "地址列表为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (alertDialog == null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_popwindow_environ, null);
            RecyclerView recyclerView = view.findViewById(R.id.environ_view_rv_item_pop);
            TextView tvCancel = view.findViewById(R.id.btn_cancel);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
            //recyclerView.addItemDecoration(new DisplayUtils.SpacesItemDecoration(activity, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new EnvironmentAdapter<>(listEnvironment, new EnvironmentAdapter.OnClickedListener() {

                @Override
                public void onClicked(EnvironBean bean) {
                    alertDialog.dismiss();
                    if (listener != null) {
                        listener.onItemClicked(bean);
                    }
                }

            }));
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setBackgroundAlpha(activity, 1f);
                    alertDialog.dismiss();
                }
            });
            builder = new AlertDialog.Builder(activity, R.style.dialog);
            alertDialog = builder.create();
            //final AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            setBackgroundAlpha(activity, 0.9f);
            alertDialog.show();
            alertDialog.getWindow().setContentView(view);

//            popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            popupWindow.setBackgroundDrawable(new BitmapDrawable());
//            popupWindow.setOutsideTouchable(false);
//            //popupWindow.showAtLocation();
//            popupWindow.showAsDropDown(tvChange, -164, 0);
        } else {
            if (!alertDialog.isShowing()) {
                //alertDialog.showAsDropDown(tvChange, -164, 0);
                alertDialog.show();
            }
        }
    }

    protected void setBackgroundAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = alpha;
        activity.getWindow().setAttributes(layoutParams);
    }

    public interface OnMyItemClickListener<T extends EnvironBean> {
        void onItemClicked(T t);
    }



}
//public class ListShow<T extends EnvironBean> {
//
//    private PopupWindow popupWindow;
//    private View tvChange;
//    private List<T> listEnvironment;
//    private OnMyItemClickListener listener;
//
//    public ListShow(View tvChange, List<T> listEnvironment, OnMyItemClickListener listener) {
//        this.tvChange = tvChange;
//        this.listEnvironment = listEnvironment;
//        this.listener = listener;
//    }
//
//    public void showChangeList(Context context) {
//        if (popupWindow == null) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_popwindow_environ, null);
//            RecyclerView recyclerView = view.findViewById(R.id.environ_view_rv_item_pop);
//            TextView tvCancel = view.findViewById(R.id.btn_cancel);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//            linearLayoutManager.setAutoMeasureEnabled(true);
//            recyclerView.setLayoutManager(linearLayoutManager);
//            //recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
//            recyclerView.addItemDecoration(new DisplayUtils.SpacesItemDecoration(context, DividerItemDecoration.VERTICAL));
//            recyclerView.setAdapter(new EnvironmentAdapter<>(listEnvironment, new EnvironmentAdapter.OnClickedListener() {
//
//                @Override
//                public void onClicked(EnvironBean bean) {
//                    popupWindow.dismiss();
//                    if (listener != null) {
//                        listener.onItemClicked(bean);
//                    }
//                }
//
//            }));
//            tvCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow.dismiss();
//                }
//            });
//            popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            popupWindow.setBackgroundDrawable(new BitmapDrawable());
//            popupWindow.setOutsideTouchable(false);
//            //popupWindow.showAtLocation();
//            popupWindow.showAsDropDown(tvChange, -164, 0);
//        } else {
//            if (!popupWindow.isShowing()) {
//                popupWindow.showAsDropDown(tvChange, -164, 0);
//            }
//        }
//    }
//
//    public interface OnMyItemClickListener<T extends EnvironBean> {
//        void onItemClicked(T t);
//    }
//
//
//
//}
