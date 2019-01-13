package small.com.small_demo.di.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.utils.OkHttpUtils;

public class LoginModel {

    private String phone;
    private String pwd;

    public void login(Map<String, String> map, final LoginModelCallBack loginModelCallBack) {
        String phone = map.get("phone");
        String pwd = map.get("pwd");
        String Login_Url = "http://mobile.bwstudent.com/small/user/v1/login?phone=" + phone +
                "&pwd=" +
                pwd;
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doPost(Login_Url, map, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                loginModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                if (loginBean.getStatus().equals("0000")) {
                    loginModelCallBack.getSuccess(loginBean);
                }
            }
        });
    }

    //接口回调
    public interface LoginModelCallBack {
        void getSuccess(LoginBean loginBean);

        void getFaid(String error);
    }
}
