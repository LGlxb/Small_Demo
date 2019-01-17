package small.com.small_demo.di.presenter;

import java.util.Map;

import javax.inject.Inject;

import dagger.Module;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.compontent.DaggerLoginCompontent;
import small.com.small_demo.compontent.DaggerPopTopCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.LoginModel;
import small.com.small_demo.di.model.PopTopModel;
import small.com.small_demo.module.LoginModule;
import small.com.small_demo.module.PopTopModule;

public class PopTopPresenter extends BasePresenter<DataCall> {
    @Inject
    PopTopModel popTopModel;


    public PopTopPresenter(DataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerPopTopCompontent.builder().popTopModule(new PopTopModule()).build().PopTopInject
                (this);
    }

    public void popTop() {
        popTopModel.popTopRecy(new PopTopModel.TopModelCallBack() {
            @Override
            public void getSuccess(PopTopBean popTopBean) {
                view.onPopRecyOne(popTopBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}