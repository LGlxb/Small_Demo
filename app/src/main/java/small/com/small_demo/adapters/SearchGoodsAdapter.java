package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import small.com.small_demo.R;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.SearchGoodsBean;

public class SearchGoodsAdapter extends BaseQuickAdapter<SearchGoodsBean.ResultBean,
        BaseViewHolder> {


    public SearchGoodsAdapter(int layoutResId, @Nullable List<SearchGoodsBean.ResultBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsBean.ResultBean item) {
        helper.setText(R.id.goods_search_key_word_title, item.getCommodityName());
        helper.setText(R.id.goods_search_key_word_price, "$:" + item.getPrice() + "");
        String images = item.getMasterPic();
        Log.d(TAG, images + "+++images");
        String[] split = images.split("\\|");
        if (split.length > 0) {
            Uri uri = Uri.parse(split[0]);
            ((ImageView) helper.getView(R.id.goods_search_key_word_img)).setImageURI(Uri.parse
                    (split[0]));
        }
    }
}
