package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.PopTopModel;
import small.com.small_demo.removecoupling.compontent.DaggerPopTopCompontent;
import small.com.small_demo.removecoupling.module.PopTopModule;

public class PopTopPresenter extends BasePresenter<HomeDataCall> {
    @Inject
    PopTopModel popTopModel;


    public PopTopPresenter(HomeDataCall view) {
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