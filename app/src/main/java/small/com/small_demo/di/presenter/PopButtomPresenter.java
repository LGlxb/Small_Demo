package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.compontent.DaggerPopButtomCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.PopButtomModel;
import small.com.small_demo.di.model.PopTopModel;
import small.com.small_demo.module.PopButtomModule;
import small.com.small_demo.module.PopTopModule;

public class PopButtomPresenter extends BasePresenter<DataCall> {
    @Inject
    PopButtomModel popButtomModel;


    public PopButtomPresenter(DataCall view) {
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