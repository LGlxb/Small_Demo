package small.com.small_demo.di.presenter;

import java.util.Map;

import javax.inject.Inject;

import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.compontent.DaggerLoginCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.LoginModel;
import small.com.small_demo.module.LoginModule;

public class LoginPresenter extends BasePresenter<DataCall> {
    @Inject
    LoginModel loginModel;


    public LoginPresenter(DataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerLoginCompontent.builder().loginModule(new LoginModule()).build().loginInject(this);
    }

    public void login(Map<String, String> map) {
        loginModel.login(map, new LoginModel.LoginModelCallBack() {
            @Override
            public void getSuccess(LoginBean loginBean) {
                view.onLogin(loginBean);

            }

            @Override
            public void getFaid(String error) {

                view.onFaild(error);
            }
        });
    }
}