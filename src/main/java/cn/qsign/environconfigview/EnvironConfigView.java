package cn.qsign.environconfigview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/10/15 0015.
 */

public class EnvironConfigView extends View {

    private static final int DEFAULT_WIDTH = 33;
    private static final int DEFAULT_HEIGHT = 33;
    private static final int DEFAULT_COUNTS = 5;

    private static final int DEFAULT_TIME = 1000;//时间太短次数多容易不触发

    private int counts;// 点击次数
    private int duration;// 规定有效时间
    private long[] mHits;

    //private OnMultiClickedListener listener;

    private View view;
    private Activity activity;
    private List<? extends EnvironBean> listEnvironment = new ArrayList<>();
    private ListShow listShow;

    public EnvironConfigView(Context context) {
        this(context, null);
    }

    public EnvironConfigView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnvironConfigView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EnvironConfigView);
        counts = a.getInt(R.styleable.EnvironConfigView_counts, DEFAULT_COUNTS);
        duration = a.getInt(R.styleable.EnvironConfigView_time, DEFAULT_TIME);
        a.recycle();

        mHits = new long[counts];

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                continuousClick();
            }
        });
        /*listShow = new ListShow<>(view, listEnvironment, new ListShow.OnMyItemClickListener<EnvironBean>() {

            @Override
            public void onItemClicked(EnvironBean environBean) {
                //Toast.makeText(activity, environBean.getName(), Toast.LENGTH_SHORT).show();
                System.out.println("---zzz----------onItemClicked : name = " + environBean.getName());
                if (listener != null) {
                    listener.onMultiShowedClicked(environBean);
                }
            }
        });*/
    }

    private void continuousClick() {
        //System.out.println("---zzz-----s----     ----连续点击----------");
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - duration)) {
            //mHits = new long[counts];//重新初始化数组
            for (int i=0; i < counts; i++) {
                mHits[i] = 0;
            }
            //System.out.println("---zzz-----s--------连续点击了" + counts + "次");
            if (listShow != null) {
                listShow.showChangeList(activity);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            // Mode = UNSPECIFIED,AT_MOST时使用提供的默认大小
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                // Mode = EXACTLY时使用测量的大小
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * 点击后回调
     * @param activity
     * @param listEnvironment
     * @param listener
     */
    public void setMultiClicked(Activity activity, List<? extends EnvironBean> listEnvironment, final OnMultiClickedListener listener) {
        //this.listener = listener;
//        this.view = view;
        this.activity = activity;
        this.listEnvironment = listEnvironment;
        listShow = new ListShow<>(listEnvironment, new ListShow.OnMyItemClickListener<EnvironBean>() {

            @Override
            public void onItemClicked(EnvironBean environBean) {
                //Toast.makeText(activity, environBean.getName(), Toast.LENGTH_SHORT).show();
                System.out.println("---zzz----------onItemClicked : name = " + environBean.getName());
                if (listener != null) {
                    listener.onMultiShowedClicked(environBean);
                }
            }
        });
    }

    /**
     * 点击后回调
     * @param <T> 继承自EnvironBean
     */
    public interface OnMultiClickedListener<T extends EnvironBean> {
        void onMultiShowedClicked(T t);
    }

}
