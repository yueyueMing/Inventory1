package drawthink.com.inventory.functions.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.cash.CashActivity;
import drawthink.com.inventory.functions.login.LoginActivity;
import drawthink.com.inventory.functions.main.contract.MainContract;
import drawthink.com.inventory.functions.main.presenter.MainPresenter;
import drawthink.com.inventory.functions.sell.SellActivity;
import drawthink.com.inventory.functions.statistics.StatisticsActivity;
import drawthink.com.inventory.functions.stock.StockActivity;
import drawthink.com.inventory.utils.DateTimeUtils;
import drawthink.com.inventory.utils.JumpUtil;

public class MainActivity
        extends BaseActivity
        implements MainContract.View {

    private static final int REQ_SELECT_SHOP = 0x1001;
    @BindView(R.id.bt_exit)
    Button btExit;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    @BindView(R.id.tv_statistics)
    TextView tvStatistics;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_cash)
    TextView tvCash;
    @BindView(R.id.bt_selectshop)
    Button btSelectshop;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_shopTitle)
    TextView tvShopTitle;
    @BindView(R.id.tv_userMobile)
    TextView tvUserMobile;
    private MainContract.Presenter presenter;
    private JumpUtil jumpUtil;


    @Inject
    UserSpUtils spUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, false);
        ButterKnife.bind(this);
        presenter.loadUser();

    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new MainPresenter(this);
        return presenter;


    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }


    @OnClick({R.id.bt_exit, R.id.tv_sell, R.id.tv_statistics, R.id.tv_stock, R.id.tv_cash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_exit:
                exit_Dialog();
                break;
            case R.id.tv_sell:
                jumpUtil = new JumpUtil(this, SellActivity.class);
                jumpUtil.start();
                break;
            case R.id.tv_statistics:
                jumpUtil = new JumpUtil(this, StatisticsActivity.class);
                jumpUtil.start();
                break;
            case R.id.tv_stock:
                jumpUtil = new JumpUtil(this, StockActivity.class);
                jumpUtil.start();
                break;
            case R.id.tv_cash:
                jumpUtil = new JumpUtil(this, CashActivity.class);
                jumpUtil.start();

                break;
        }
    }

    private void exit_Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("系统提示").setMessage("你确定要退出当前账号吗?")
                .setPositiveButton("确定", (dialog, which) -> {
                    jumpUtil = new JumpUtil(MainActivity.this, LoginActivity.class);
                    jumpUtil.start();
                    finish();
                    spUtils.clear();
                }).setNegativeButton("返回", (dialog, which) -> {
                }).setCancelable(false).show();
    }

    @OnClick(R.id.bt_selectshop)
    public void onClick() {

        startActivityForResult(new Intent(this, ShopSelectActivity.class), REQ_SELECT_SHOP);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SELECT_SHOP && resultCode == RESULT_OK) {
            String shopNameDate = data.getStringExtra("shopNameDate");
            int shopId = InventoryApplication.getShopId();
            presenter.loadSellerByShop(shopId);
            tvShopTitle.setText(shopNameDate);
        }

    }

    @Override
    public void showUser(String userName, String userId, String userMobile, String storeName, long startDate, long endDate) {
        tvUsername.setText(userName);
        tvUserMobile.setText(userMobile);
        tvShopTitle.setText(String.format("%s (%s至%s)", storeName,
                DateTimeUtils.format(new Date(startDate), DateTimeUtils.FORMAT_SHORT_CN),
                DateTimeUtils.format(new Date(endDate), DateTimeUtils.FORMAT_SHORT_CN)));
    }


}
