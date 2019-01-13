package small.com.small_demo.di.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.RegisterBean;
import small.com.small_demo.utils.OkHttpUtils;

public class RegisterModel {

    private String phone;
    private String pwd;

    public void register(Map<String, String> map, final RegisterModelCallBack
            registerModelCallBack) {
        String phone = map.get("phone");
        String pwd = map.get("pwd");
        String Register_Url = "http://mobile.bwstudent.com/small/user/v1/register?phone=" + phone +
                "&pwd=" +
                pwd;
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doPost(Register_Url, map, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                registerModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                RegisterBean registerBean = new Gson().fromJson(json, RegisterBean.class);
                if (registerBean.getStatus().equals("0000")) {
                    registerModelCallBack.getSuccess(registerBean);
                }
            }
        });
    }

    //接口回调
    public interface RegisterModelCallBack {
        void getSuccess(RegisterBean registerBean);

        void getFaid(String error);
    }
}
