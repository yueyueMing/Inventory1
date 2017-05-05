package drawthink.com.inventory.functions.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.utils.ConvertUtils;
import com.socks.library.KLog;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.main.contract.ShopSelectContract;
import drawthink.com.inventory.functions.main.presenter.ShopSelectPresenter;
import drawthink.com.inventory.utils.DateTimeUtils;

/**
 * <b>类名称：</b> ShopSelectActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月01日 14:31<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class ShopSelectActivity extends BaseActivity implements ShopSelectContract.View {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private ShopSelectPresenter presenter;
    @Inject
    UserSpUtils userSpUtils;
    private List<User.StoresBean> storesBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_select);
        ButterKnife.bind(this);
        presenter.loadUserShops();
        Intent intent = new Intent();
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            int storeId = radioGroup1.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) radioGroup1.findViewById(i);
            for (User.StoresBean storesBean : storesBeanList) {
                if (storesBean.getId() == storeId) {
                    storesBean.setChecked(true);
                    InventoryApplication.setShopId(storeId);
                    InventoryApplication.setShopName(storesBean.getStoreName());
                } else {
                    storesBean.setChecked(false);
                }
            }
            userSpUtils.updateStoreChecked(storesBeanList);
            intent.putExtra("shopNameDate", radioButton.getText());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
        getToolbar().setNavigationIcon(null);
        getToolbar().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.close) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String setTitle() {
        return "请选择店铺";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new ShopSelectPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);

    }


    @Override
    public void showUserStore(List<User.StoresBean> storesBeanList) {
        radioGroup.getShowDividers();
        this.storesBeanList = storesBeanList;
        for (User.StoresBean storesBean : storesBeanList) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(storesBean.getId());
            radioButton.setText(String.format("%s (%s至%s)", storesBean.getStoreName(),
                    DateTimeUtils.format(new Date(storesBean.getStartDate()),DateTimeUtils.FORMAT_SHORT_CN),
                    DateTimeUtils.format(new Date(storesBean.getEndDate())),DateTimeUtils.FORMAT_SHORT_CN));
            radioButton.setChecked(storesBean.isChecked());
            radioButton.setTextSize(20);
            radioButton.setTextColor(Color.parseColor("#333333"));
            radioButton.setPadding(ConvertUtils.dp2px(10),ConvertUtils.dp2px(10),
                    ConvertUtils.dp2px(10),ConvertUtils.dp2px(10));
            radioGroup.addView(radioButton);
        }

    }

    @Override
    protected void onDestroy() {
        radioGroup.removeAllViews();
        super.onDestroy();
    }
}
