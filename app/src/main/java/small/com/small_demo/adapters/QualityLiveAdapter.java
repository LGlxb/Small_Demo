package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.HomeGoodsBean;

public class QualityLiveAdapter extends BaseQuickAdapter<HomeGoodsBean.ResultBean.PzshBean
        .CommodityListBeanX,
        BaseViewHolder> {


    public QualityLiveAdapter(int layoutResId, @Nullable List<HomeGoodsBean.ResultBean.PzshBean
            .CommodityListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBean.ResultBean.PzshBean
            .CommodityListBeanX item) {
        helper.setText(R.id.goods_pzsh_title, item.getCommodityName());
        helper.setText(R.id.goods_pzsh_price, "$:"+item.getPrice() + "");
        ((ImageView) helper.getView(R.id.goods_pzsh_img)).setImageURI(Uri.parse(item
                .getMasterPic()));
    }
}
