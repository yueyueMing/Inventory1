package drawthink.com.inventory.functions.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.login.contract.LoginContract;
import drawthink.com.inventory.functions.login.presenter.LoginPresenter;
import drawthink.com.inventory.functions.main.MainActivity;
import drawthink.com.inventory.utils.RxCountDown;
import rx.Subscriber;

/**
 * <b>类名称：</b> LoginActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 8:56<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.et_telNumber)
    EditText etTelNumber;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.loginLayout)
    LinearLayout loginLayout;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.bt_getCode)
    Button btGetCode;
    private String mobileStr;
    private String validCodeStr;

    LoginContract.Presenter presenter;
    @Inject
    UserSpUtils userSpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_login, false);
        ButterKnife.bind(this);
        Utils.init(this);

    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }


    private boolean valid() {
        boolean valid = true;
        if (StringUtils.isSpace(mobileStr)) {
            toast("请输入手机号");
            valid = false;
        } else if (!RegexUtils.isMobileExact(mobileStr)) {
            toast("手机号码格式不正确");
            valid = false;
        } else if (StringUtils.isSpace(validCodeStr)) {
            toast("请输入验证码");
            valid = false;
        }
        return valid;
    }

    private boolean valid1() {
        boolean valid = true;
        if (StringUtils.isSpace(mobileStr)) {
            toast("请输入手机号");
            valid = false;
        } else if (!RegexUtils.isMobileExact(mobileStr)) {
            toast("手机号码格式不正确");
            valid = false;
        }
        return valid;
    }

    private void bindData() {
        mobileStr = etTelNumber.getText().toString();
        validCodeStr = etCode.getText().toString();
    }


    @OnClick({R.id.bt_getCode, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_getCode:
                bindData();
                if (valid1()) {
                    presenter.getValidCode(mobileStr);
                    RxCountDown.countdown(60)
                            .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            //计时完成
                            btGetCode.setText("获取验证码");
                            btGetCode.setEnabled(true);
                        }

                        @Override
                        public void onError(Throwable e) {
                            btGetCode.setEnabled(true);
                        }

                        @Override
                        public void onNext(Integer integer) {
                            //当前的时间
                            btGetCode.setText(String.format("%d秒", integer));
                            btGetCode.setEnabled(false);
                        }
                    });

                }

                break;
            case R.id.bt_login:
                bindData();
                if (valid()) {
                    presenter.login(mobileStr, validCodeStr);
                    System.out.println("电话号码:" + mobileStr + "验证码: " + validCodeStr);
                }
                break;
        }
    }


    @Override
    public void loginComplete() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
