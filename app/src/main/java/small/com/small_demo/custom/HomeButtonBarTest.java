package small.com.small_demo.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import small.com.small_demo.R;

public class HomeButtonBarTest extends RelativeLayout implements View.OnClickListener {


    //第一个按钮
    private RadioButton mRadioOne;
    //第二个按钮
    private RadioButton mRadioTwo;
    //中间的按钮
    private ImageView mCenter;
    //第四个按钮
    private RadioButton mRadioThree;
    //第五个按钮
    private RadioButton mRadioFour;
    //不设置默认按钮，即为第一个按钮为默认按钮 记录当前选中按钮
    private Integer mDefaultPage = 0;

    //避免重复点击 默认打开
    private Boolean mRepeated = true;

    //点击回调
    private onRadioClickListener mListener;


    public HomeButtonBarTest(Context context) {
        super(context);
    }

    public HomeButtonBarTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init(Context context) {
        //当前控件依赖布局
        LayoutInflater.from(context).inflate(R.layout.home_buttom_bar_layout, this);
        //获取布局中的控件，实例化到当前对象中
        mRadioOne = findViewById(R.id.radio_home_page_selected_one);
        mRadioTwo = findViewById(R.id.radio_home_page_selected_two);
        mCenter = findViewById(R.id.radio_home_page_selected_center);
        mRadioThree = findViewById(R.id.radio_home_page_selected_three);
        mRadioFour = findViewById(R.id.radio_home_page_selected_four);
        //设置点击方法
        initListener();

        //设置资源图片
        setResourcePictures(defRes);

        //默认页面
        setDefaultPage();

    }

    private void initListener() {
        mRadioOne.setOnClickListener(this);
        mRadioTwo.setOnClickListener(this);
        mCenter.setOnClickListener(this);
        mRadioThree.setOnClickListener(this);
        mRadioFour.setOnClickListener(this);
    }

    //设置点击回调
    public void setOnRadioClickListener(onRadioClickListener onRadioClickListener) {
        mListener = onRadioClickListener;
    }


    //默认的资源文件
    int[] defRes = {R.drawable.bottom_bar_selectone, R.drawable.bottom_bar_selecttwo, R.drawable
            .bottom_bar_selectthree, R.drawable.bottom_bar_selectfour};

    //设置资源图片
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setResourcePictures(int[] drawableTop) {
        if (drawableTop.length != 4) {
            Log.e("setResourcePictures", "MainBottomBar中所提供的图片资源必须为4个: ");
            return;
        }
        mRadioOne.setCompoundDrawablesRelativeWithIntrinsicBounds(null, this.getResources()
                .getDrawable(drawableTop[0]), null, null);
        mRadioTwo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, this.getResources()
                .getDrawable(drawableTop[1]), null, null);
        mRadioThree.setCompoundDrawablesRelativeWithIntrinsicBounds(null, this.getResources()
                .getDrawable(drawableTop[2]), null, null);
        mRadioFour.setCompoundDrawablesRelativeWithIntrinsicBounds(null, this.getResources()
                .getDrawable(drawableTop[3]), null, null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_home_page_selected_one:
                clickInterception(0);
                break;
            case R.id.radio_home_page_selected_two:
                clickInterception(1);
                break;
            case R.id.radio_home_page_selected_center:
                mRadioOne.setChecked(false);
                mRadioTwo.setChecked(false);
                mRadioThree.setChecked(false);
                mRadioFour.setChecked(false);
                clickInterception(2);
                break;
            case R.id.radio_home_page_selected_three:
                clickInterception(3);
                break;
            case R.id.radio_home_page_selected_four:
                clickInterception(4);
                break;
        }

    }


    //点击拦截，过滤重复点击
    private void clickInterception(int page) {
        if (mRepeated && page == mDefaultPage) {
            return;
        }
        //记录新的页码.
        mDefaultPage = page;
        mListener.onClick(page);
    }

    //按钮点击回调
    public interface onRadioClickListener {
        void onClick(int postion);
    }

    //内部使用,默认使用的页面
    private void setDefaultPage() {
        setDefaultPage(mDefaultPage);
    }

    //设置是否过滤重复点击
    public void setRepeated(Boolean repeated) {
        this.mRepeated = repeated;
    }

    //设置默认打开的按钮
    public void setDefaultPage(Integer page) {
        mDefaultPage = page;
        switch (page) {
            case 0:
                mRadioOne.setChecked(true);
                break;
            case 1:
                mRadioTwo.setChecked(true);
                break;
            case 2:
                break;
            case 3:
                mRadioThree.setChecked(true);
                break;
            case 4:
                mRadioFour.setChecked(true);
                break;
        }
    }
}
