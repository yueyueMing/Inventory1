package drawthink.com.inventory.functions.cash;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.blankj.utilcode.utils.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.remote.server.inventory.service.form.CashForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.cash.contract.AddCashContract;
import drawthink.com.inventory.functions.cash.presenter.AddCashPresenter;
import drawthink.com.inventory.utils.DateTimeUtils;
import kale.adapter.CommonAdapter;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> AddCashActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月28日 9:15<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class AddCashActivity extends BaseActivity implements AddCashContract.View {
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_sellAccout)
    TextView tvSellAccout;
    @BindView(R.id.et_acturalAccount)
    EditText etActuralAccount;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.bt_yes)
    Button btYes;
    @BindView(R.id.bt_no)
    Button btNo;
    AddCashContract.Presenter presenter;
    @BindView(R.id.payWay)
    Spinner payWay;
    @BindView(R.id.seller)
    Spinner seller;

    private CommonAdapter<ShopSeller> adapter;
    private CashForm cashForm;
    private int shopSellerId;
    private String shopSellerName;
    private String payType;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addcash, true);
        ButterKnife.bind(this);
        initData();
        presenter.loadSeller(InventoryApplication.getShopId());
        getToolbar().setNavigationIcon(null);


    }


    private void initData() {
        adapter = new CommonAdapter<ShopSeller>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new SellerItem();
            }
        };
        /*
        销售员监听
         */
        seller.setAdapter(adapter);
        seller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //有问题
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShopSeller shopSeller = adapter.getData().get(i);
                shopSellerId = shopSeller.getId();
                shopSellerName = shopSeller.getName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*
        收入支出监听
         */
        payWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                payType = String.valueOf(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public String setTitle() {
        return "添加现金";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new AddCashPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);

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


    @OnClick({R.id.tv_data, R.id.bt_yes, R.id.bt_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            tvData.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                            presenter.loadMoney(tvData.getText().toString());
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
            case R.id.bt_yes:

                if (validData()) {
                    presenter.addCash(cashForm);

                }

                break;
            case R.id.bt_no:
                finish();
                break;
        }
    }

    private boolean validData() {
        bindData();
        boolean isValid = true;
        if (StringUtils.isSpace(cashForm.getDate())) {
            isValid = false;
            toast("请选择日期");
        }
        return isValid;
    }

    private void bindData() {
        cashForm = new CashForm();
        cashForm.setShopId(InventoryApplication.getShopId());
        cashForm.setDate(DateTimeUtils.getNow("yyyy-MM-dd"));
        String acturalAccount = etActuralAccount.getText().toString();
        cashForm.setCash(acturalAccount.isEmpty() ? 0 : Integer.parseInt(acturalAccount));
        cashForm.setMessage(etRemark.getText().toString());
        cashForm.setPayType(payType);//替换
        cashForm.setSalerId(shopSellerId);
        cashForm.setShopName(InventoryApplication.getShopName());
        cashForm.setSalerName(shopSellerName);
    }

    @Override
    public void showSaler(List<ShopSeller> ShopSellers) {
        adapter.setData(ShopSellers);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSellMoney(Double calcCash) {
        tvSellAccout.setText("¥:" + calcCash);
    }
}
