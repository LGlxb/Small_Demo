package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.HomeGoodsBean;

public class FshionAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.MlssBean
        .CommodityListBeanXX,
        BaseViewHolder> {


    public FshionAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.MlssBean
            .CommodityListBeanXX> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.MlssBean
            .CommodityListBeanXX item) {
        helper.setText(R.id.goods_mlss_title, item.getCommodityName());
        helper.setText(R.id.goods_mlss_price, "$:"+item.getPrice() + "");
        ((ImageView) helper.getView(R.id.goods_mlss_img)).setImageURI(Uri.parse(item
                .getMasterPic()));
    }
}
