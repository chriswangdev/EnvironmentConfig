package cn.qsign.environconfigview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/12/29 0029.
 */

public class DisplayUtils {

    //public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    public static class SpacesItemDecoration extends DividerItemDecoration {

        /**
         * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
         * {@link LinearLayoutManager}.
         *
         * @param context     Current context, it will be used to access resources.
         * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
         */
        public SpacesItemDecoration(Context context, int orientation) {
            super(context, orientation);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //不是第一个的格子都设一个上边和底部的间距  这些间隔大小可以自行修改
            int pos = parent.getChildLayoutPosition(view);  //当前条目的position
            int itemCount = state.getItemCount() - 1;           //最后一条的postion
            if (pos == itemCount) {   //最后一条
                outRect.bottom = 1;
                //view.setVisibility(View.GONE);
                outRect.top = 1;
            } else {
                if (pos % 2 == 1) {  //下面一行

                    outRect.bottom = 1;
                    outRect.top = 1;
                } else { //上面一行
                    if (pos == 0) {
                        outRect.bottom = 1;
                        outRect.top = 1;
                    } else {
                        outRect.top = 1;
                        outRect.bottom = 1;
                    }


                }
            }

        }
    }
}
