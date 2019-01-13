package small.com.small_demo.di.model;

import com.google.gson.Gson;

import java.util.Map;

import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.utils.OkHttpUtils;

public class HomeBannerModel {

    public void homeBanner( final BannerModelCallBack bannerModelCallBack) {

        String Banner_Url = "http://172.17.8.100/small/commodity/v1/bannerShow";
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doGet(Banner_Url, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                bannerModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                HomeBannerBean homeBannerBean = new Gson().fromJson(json, HomeBannerBean.class);
                if (homeBannerBean.getStatus().equals("0000")) {
                    bannerModelCallBack.getSuccess(homeBannerBean);
                } else {
                    bannerModelCallBack.getFaid("网络异常");
                }
            }
        });

    }

    //接口回调
    public interface BannerModelCallBack {
        void getSuccess(HomeBannerBean homeBannerBean);

        void getFaid(String error);
    }
}
