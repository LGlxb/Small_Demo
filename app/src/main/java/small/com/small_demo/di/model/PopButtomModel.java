package small.com.small_demo.di.model;

import com.google.gson.Gson;

import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.utils.OkHttpUtils;

public class PopButtomModel {
    public void popTopRecy(String firstCategoryId, final ButtomModelCallBack buttomModelCallBack) {

        String Login_Url = "http://mobile.bwstudent.com/small/commodity/v1/findSecondCategory" +
                "?firstCategoryId=" + firstCategoryId;
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doGet(Login_Url, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                buttomModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                PopButtomBean popButtomBean = new Gson().fromJson(json, PopButtomBean.class);
                if (popButtomBean.getStatus().equals("0000")) {
                    buttomModelCallBack.getSuccess(popButtomBean);
                }
            }
        });
    }

    //接口回调
    public interface ButtomModelCallBack {
        void getSuccess(PopButtomBean popButtomBean);

        void getFaid(String error);
    }
}
