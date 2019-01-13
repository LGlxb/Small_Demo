package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.HomeGoodsBean;

public class HotGoodsAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.RxxpBean
        .CommodityListBean,
        BaseViewHolder> {


    public HotGoodsAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.RxxpBean
            .CommodityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.RxxpBean
            .CommodityListBean item) {
        helper.setText(R.id.goods_rxxp_title, item.getCommodityName());
        helper.setText(R.id.goods_rxxp_price, "$:"+item.getPrice() + "");
        ((ImageView) helper.getView(R.id.goods_rxxp_img)).setImageURI(Uri.parse(item
                .getMasterPic()));
    }
}
