package small.com.small_demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import small.com.small_demo.R;
import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.RegisterBean;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements DataCall {

    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.reg_edit_phone)
    EditText regEditPhone;
    @BindView(R.id.img_verfition_code)
    ImageView imgVerfitionCode;
    @BindView(R.id.reg_edit_verfition_code)
    EditText regEditVerfitionCode;
    @BindView(R.id.reg_txt_get_verfition_code)
    TextView regTxtGetVerfitionCode;
    @BindView(R.id.img_lock)
    ImageView imgLock;
    @BindView(R.id.reg_edit_pwd)
    EditText regEditPwd;
    @BindView(R.id.reg_img_eye)
    ImageView regImgEye;
    @BindView(R.id.reg_txt_go_login)
    TextView regTxtGoLogin;
    @BindView(R.id.btn_reg)
    Button btnReg;
    //实例化P层
    RegisterPresenter registerPresenter = new RegisterPresenter(this);
    private int eyeType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.reg_txt_get_verfition_code, R.id.reg_img_eye, R.id.btn_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_txt_get_verfition_code:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.reg_img_eye:
                if (eyeType == 0) {
                    //点击隐藏密码
                    regEditPwd.setInputType(false ?
                            (InputType.TYPE_CLASS_TEXT | InputType
                                    .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                            (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    eyeType = 1;
                } else {
                    regEditPwd.setInputType(true ?
                            (InputType.TYPE_CLASS_TEXT | InputType
                                    .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                            (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    eyeType = 0;
                }
                break;
            case R.id.btn_reg:
                String regMobile = regEditPhone.getText().toString();
                String regPwd = regEditPwd.getText().toString();
                Map<String, String> registerMap = new HashMap<>();
                registerMap.put("phone", regMobile);
                registerMap.put("pwd", regPwd);
                registerPresenter.register(registerMap);
                break;
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onHotGoods(HomeGoodsBean homeGoodsBean) {

    }

    @Override
    public void onFashion(HomeGoodsBean homeGoodsBean) {

    }

    @Override
    public void onQualityLive(HomeGoodsBean homeGoodsBean) {

    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        Toast.makeText(this, "小主,注册成功~", Toast.LENGTH_SHORT).show();
        if (registerBean.getStatus().toString().equals("0000")) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onBanner(HomeBannerBean homeBannerBean) {

    }

    @Override
    public void onFaild(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context CONTEXT() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
