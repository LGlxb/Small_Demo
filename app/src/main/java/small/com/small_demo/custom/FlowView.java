package small.com.small_demo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import small.com.small_demo.R;

public class FlowView extends FrameLayout {
    int txt_size;
    int txt_color;

    public FlowView(Context context) {
        super(context);
    }

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.flow);
        txt_color = array.getInt(R.styleable.flow_text_color, 0xFF0000FF);
        txt_size = array.getInt(R.styleable.flow_text_size, 0);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //获得控件宽度
        int width = getWidth();
        //定义常量行数
        int row = 0;
        //子控件左边的坐标
        int disWidth = 18;
        for (int i = 0; i < getChildCount(); i++) {
            //子控件视图
            View view = getChildAt(i);
            //子控件宽高
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            //判断子控件宽度+距左侧距离大于控件宽度
            if (disWidth + viewWidth > width) {
                row++;//换行
                disWidth = 18;//距离左侧距离
            }
            view.layout(disWidth, row * viewHeight, viewWidth + disWidth, viewHeight * (row + 1));
            disWidth += viewWidth;
        }
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
