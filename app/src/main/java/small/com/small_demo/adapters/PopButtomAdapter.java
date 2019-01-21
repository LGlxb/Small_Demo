package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.PopButtomBean;

public class PopButtomAdapter extends BaseQuickAdapter<PopButtomBean.ResultBean,
        BaseViewHolder> {
    public PopButtomAdapter(int layoutResId, @Nullable List<PopButtomBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PopButtomBean.ResultBean item) {
        helper.setText(R.id.home_pop_bottom_tv, item.getName());
        //条目点击
        helper.setOnClickListener(R.id.home_pop_bottom_tv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义接口回调   传索引
                mItemClick.onItem(item.getName());
            }
        });


    }

    //自定义接口
    private ItemClick mItemClick;

    //接口
    public interface ItemClick {
        void onItem(String data);
    }

    //设置外部访问的方法
    public void setitemClick(ItemClick mItemClick) {
        this.mItemClick = mItemClick;
    }
}
