package small.com.small_demo.di.presenter;

import java.util.Map;

import javax.inject.Inject;

import small.com.small_demo.bean.RegisterBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.RegisterModel;
import small.com.small_demo.removecoupling.compontent.DaggerRegisterCompontent;
import small.com.small_demo.removecoupling.module.RegisterModule;

public class RegisterPresenter extends BasePresenter<HomeDataCall> {
    @Inject
    RegisterModel registerModel;
    public RegisterPresenter(HomeDataCall view) {
        super(view);
    }
    @Override
    protected void initModel() {
        //耦合性处理
        DaggerRegisterCompontent.builder().registerModule(new RegisterModule()).build()
                .RegisterInject(this);
    }

    public void register(Map<String, String> map) {
        registerModel.register(map, new RegisterModel.RegisterModelCallBack() {
            @Override
            public void getSuccess(RegisterBean registerBean) {
                view.onRegister(registerBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}