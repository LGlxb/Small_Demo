package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.PopButtomModel;
import small.com.small_demo.removecoupling.compontent.DaggerPopButtomCompontent;
import small.com.small_demo.removecoupling.module.PopButtomModule;

public class PopButtomPresenter extends BasePresenter<HomeDataCall> {
    @Inject
    PopButtomModel popButtomModel;


    public PopButtomPresenter(HomeDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerPopButtomCompontent.builder().popButtomModule(new PopButtomModule()).build()
                .PopButtomInject(this);
    }

    public void popTop(String firstCategoryId) {
        popButtomModel.popTopRecy(firstCategoryId, new PopButtomModel.ButtomModelCallBack() {
            @Override
            public void getSuccess(PopButtomBean popButtomBean) {
                view.onPopRecyTwo(popButtomBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}