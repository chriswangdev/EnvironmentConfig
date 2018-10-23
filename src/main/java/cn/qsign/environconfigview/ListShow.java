package cn.qsign.environconfigview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15 0015.
 */

public class ListShow {

    private PopupWindow popupWindow;
    private View tvChange;
    private List<EnvironmentBean> listEnvironment;
    private OnMyItemClickListener listener;

    public ListShow(View tvChange, List<EnvironmentBean> listEnvironment, OnMyItemClickListener listener) {
        this.tvChange = tvChange;
        this.listEnvironment = listEnvironment;
        this.listener = listener;
    }

    public void showChangeList(Context context) {
        if (popupWindow == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_popwindow, null);
            RecyclerView recyclerView = view.findViewById(R.id.rv_item_pop);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new EnvironmentAdapter(listEnvironment, new EnvironmentAdapter.OnClickedListener() {
                @Override
                public void onClicked(EnvironmentBean bean) {
                    popupWindow.dismiss();
                    if (listener != null) {
                        listener.onItemClicked(bean);
                    }
                }
            }));
            popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(tvChange, -164, 0);
        } else {
            if (!popupWindow.isShowing()) {
                popupWindow.showAsDropDown(tvChange, -164, 0);
            } else {
                popupWindow.dismiss();
            }
        }
    }

    public interface OnMyItemClickListener {
        void onItemClicked(EnvironmentBean bean);
    }

}
