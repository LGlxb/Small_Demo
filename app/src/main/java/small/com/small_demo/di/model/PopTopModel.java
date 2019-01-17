package small.com.small_demo.di.model;

import com.google.gson.Gson;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.utils.OkHttpUtils;

public class PopTopModel {
    public void popTopRecy(final TopModelCallBack topModelCallBack) {

        String Login_Url = "http://mobile.bwstudent.com/mall/commodity/v1/findFirstCategory";
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doGet(Login_Url, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                topModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                PopTopBean popTopBean = new Gson().fromJson(json, PopTopBean.class);
                if (popTopBean.getStatus().equals("0000")) {
                    topModelCallBack.getSuccess(popTopBean);
                }
            }
        });
    }

    //接口回调
    public interface TopModelCallBack {
        void getSuccess(PopTopBean popTopBean);

        void getFaid(String error);
    }
}
