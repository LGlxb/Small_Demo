package small.com.small_demo.di.model;

import com.google.gson.Gson;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.utils.OkHttpUtils;

public class QualityLiveModel {
    public void qualityLive(final QualityLiveModelCallBack qualityLiveModelCallBack) {

        String Login_Url = "http://172.17.8.100/small/commodity/v1/commodityList";
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doGet(Login_Url, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                qualityLiveModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                HomeGoodsBean homeGoodsBean = new Gson().fromJson(json, HomeGoodsBean.class);
                if (homeGoodsBean.getStatus().equals("0000")) {
                    qualityLiveModelCallBack.getSuccess(homeGoodsBean);
                }
            }
        });
    }

    //接口回调
    public interface QualityLiveModelCallBack {
        void getSuccess(HomeGoodsBean homeGoodsBean);

        void getFaid(String error);
    }
}
