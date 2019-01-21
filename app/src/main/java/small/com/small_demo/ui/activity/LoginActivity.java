package small.com.small_demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.bean.RegisterBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.presenter.LoginPresenter;
import small.com.small_demo.R;

public class LoginActivity extends AppCompatActivity implements HomeDataCall {

    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.login_edit_phone)
    EditText loginEditPhone;
    @BindView(R.id.img_lock)
    ImageView imgLock;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_img_eye)
    ImageView loginImgEye;
    @BindView(R.id.login_cb_remb_pwd)
    CheckBox loginCbRembPwd;
    @BindView(R.id.login_txt_quick_reg)
    TextView loginTxtQuickReg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;
    private int eyeType = 0;
    private String loginMobile;
    private String loginPwd;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //实例化M层
        loginPresenter = new LoginPresenter(this);
        //创建sp对象
        sharedPreferences = getSharedPreferences("remberPwd", Context
                .MODE_PRIVATE);
        //判断是否记住密码
        rememberPwd();
    }

    private void rememberPwd() {
        //获取传来的账号密码
        String getmoilde = sharedPreferences.getString("putmoilde", "");
        String getpwd = sharedPreferences.getString("putpwd", "");
        boolean getremember = sharedPreferences.getBoolean("putremember", false);
        //判断选中状态
        if (getremember) {
            //选中设置  默认登录显示
            loginEditPhone.setText(getmoilde);
            loginEditPwd.setText(getpwd);
            //设置选中
            loginCbRembPwd.setChecked(getremember);
        } else {
            //未选中为空
            loginEditPhone.setText("");
            loginEditPwd.setText("");
            loginCbRembPwd.setChecked(false);
        }
    }

    @OnClick({R.id.login_cb_remb_pwd, R.id.login_txt_quick_reg, R.id.btn_login, R.id.login_img_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_cb_remb_pwd:
                break;
            case R.id.login_txt_quick_reg:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.login_img_eye:

                if (eyeType == 0) {
                    //点击隐藏密码
                    loginEditPwd.setInputType(false ?
                            (InputType.TYPE_CLASS_TEXT | InputType
                                    .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                            (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    eyeType = 1;
                } else {
                    loginEditPwd.setInputType(true ?
                            (InputType.TYPE_CLASS_TEXT | InputType
                                    .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) :
                            (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    eyeType = 0;
                }
                loginEditPwd.setSelection(loginEditPwd.getText().length());
                break;
            case R.id.btn_login:
                Toast.makeText(this, "点击了登录唉~", Toast.LENGTH_SHORT).show();
                loginMobile = loginEditPhone.getText().toString();
                loginPwd = loginEditPwd.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("phone", loginMobile);
                map.put("pwd", loginPwd);
                loginPresenter.login(map);
                break;
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        Toast.makeText(this, "小主,登录成功~", Toast.LENGTH_SHORT).show();
        Log.d("LoginActivity", "loginBean.getResult().getUserId():" + loginBean.getResult()
                .getUserId() + "+1++++++++u");
        Log.d("LoginActivity", loginBean.getResult().getSessionId() + "++1++++s");
        boolean remember = loginCbRembPwd.isChecked();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        //判断是否记住密码
        if (remember) {//勾选传值
            edit.putString("putmoilde", loginMobile);
            edit.putString("putpwd", loginPwd);
            edit.putBoolean("putremember", true);
        } else {//未勾选 复选框未选中
            edit.putBoolean("putremember", false);
        }
        edit.commit();

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

    }

    @Override
    public void onPopRecyOne(PopTopBean popTopBean) {

    }

    @Override
    public void onPopRecyTwo(PopButtomBean popButtomBean) {

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

    }

    @Override
    public void onBanner(HomeBannerBean homeBannerBean) {

    }

    @Override
    public void onFaild(String error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
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
