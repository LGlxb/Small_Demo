package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.QualityLiveModel;
import small.com.small_demo.removecoupling.compontent.DaggerQualityLiveCompontent;
import small.com.small_demo.removecoupling.module.QualityLiveModule;

public class QualityLivePresenter extends BasePresenter<HomeDataCall> {

    @Inject
    QualityLiveModel qualityLiveModel;

    public QualityLivePresenter(HomeDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerQualityLiveCompontent.builder().qualityLiveModule(new QualityLiveModule()).build()
                .QualityLiveInject(this);

    }

    public void qualityLive() {
        qualityLiveModel.qualityLive(new QualityLiveModel.QualityLiveModelCallBack() {
            @Override
            public void getSuccess(HomeGoodsBean homeGoodsBean) {

                view.onQualityLive(homeGoodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}