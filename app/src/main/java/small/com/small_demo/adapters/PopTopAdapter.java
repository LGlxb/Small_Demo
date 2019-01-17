package small.com.small_demo.adapters;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.PopTopBean;

public class PopTopAdapter extends BaseQuickAdapter<PopTopBean.ResultBean,
        BaseViewHolder> {
    public PopTopAdapter(int layoutResId, @Nullable List<PopTopBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PopTopBean.ResultBean item) {
        helper.setText(R.id.home_pop_top_tv, item.getName());
        //条目点击
        helper.setOnClickListener(R.id.home_pop_top_tv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义接口回调   传索引
                mItemClick.onItem(Integer.valueOf(item.getId()));
            }
        });
    }

    //自定义接口
    private ItemClick mItemClick;

    //接口
    public interface ItemClick {
        void onItem(int data);
    }

    //设置外部访问的方法
    public void setitemClick(ItemClick mItemClick) {
        this.mItemClick = mItemClick;
    }

}
