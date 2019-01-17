package small.com.small_demo.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
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
    protected void convert(BaseViewHolder helper, PopButtomBean.ResultBean item) {
        helper.setText(R.id.home_pop_bottom_tv,item.getName() );
//        String images = item.get;
//        Log.d(TAG, images + "+++images");
//        String[] split = images.split("\\|");
//        if (split.length > 0) {
//            Uri uri = Uri.parse(split[0]);
//            ((ImageView) helper.getView(R.id.imagethree)).setImageURI(Uri.parse(split[0]));
//            Log.d(TAG, split[0] + "++++split[0]");
//        }

    }
}
