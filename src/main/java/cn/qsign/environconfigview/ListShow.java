package cn.qsign.environconfigview;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15 0015.
 */

public class ListShow<T extends EnvironBean> {

    private AlertDialog alertDialog;
    private List<T> listEnvironment;
    private OnMyItemClickListener listener;

    public ListShow(List<T> listEnvironment, OnMyItemClickListener listener) {
        this.listEnvironment = listEnvironment;
        this.listener = listener;
    }

    public void showChangeList(final Activity activity) {
        if (alertDialog == null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_popwindow_environ, null);
            RecyclerView recyclerView = view.findViewById(R.id.environ_view_rv_item_pop);
            TextView tvCancel = view.findViewById(R.id.btn_cancel);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
            //dividerItemDecoration.setDrawable(activity.getDrawable(R.drawable.shape_decoration));
            dividerItemDecoration.setDrawable(activity.getResources().getDrawable(R.drawable.shape_decoration));
            recyclerView.addItemDecoration(dividerItemDecoration);
            //recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
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
                    alertDialog.dismiss();
                    setBackgroundAlpha(activity, 1f);
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);//, R.style.dialog);
            alertDialog = builder.create();
            //final AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
//            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    setBackgroundAlpha(activity, 1f);
//                }
//            });

            setBackgroundAlpha(activity, 0.8f);
            alertDialog.show();
            //alertDialog.getWindow().setContentView(view);
            Window window = alertDialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable());
                window.setWindowAnimations(R.style.dialogAnimation);
                window.setContentView(view);
            }
        } else {
            if (!alertDialog.isShowing()) {
                setBackgroundAlpha(activity, 0.8f);
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
