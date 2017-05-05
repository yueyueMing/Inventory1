package drawthink.com.inventory.functions.cash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.blankj.utilcode.utils.ScreenUtils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.remote.server.inventory.vo.cash.CashDate;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.cash.adapter.CashItem;
import drawthink.com.inventory.functions.cash.contract.CashContract;
import drawthink.com.inventory.functions.cash.presenter.CashPresenter;
import drawthink.com.inventory.utils.JumpUtil;
import kale.adapter.CommonAdapter;
import kale.adapter.item.AdapterItem;


public class CashActivity extends BaseActivity implements CashContract.View {

    @BindView(R.id.tv_start_data)
    TextView tvStartData;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    @BindView(R.id.sp_pay_state)
    Spinner spPayState;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.lv_recode)
    ListView lvRecode;
    @BindView(R.id.allMoney)
    TextView allMoney;
    @BindView(R.id.getAll)
    TextView getAll;
    @BindView(R.id.sp_seller)
    Spinner spSeller;
    private CommonAdapter<CashDate.StatisticsBean> adapter;
    CashContract.Presenter presenter;

    private String startDate;
    private int sellerId = 0;
    private String endDate;
    private String payType;
    private CommonAdapter<ShopSeller> spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash, true);
        ButterKnife.bind(this);

        initListView();
        initSpanner();
        presenter.loadCashInfo(InventoryApplication.getShopId() + "", startDate, endDate, sellerId+"", payType);
        presenter.loadSaller(InventoryApplication.getShopId());
    }

    private void initSpanner() {
        spAdapter = new CommonAdapter<ShopSeller>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new SellerItem();
            }
        };
        spSeller.setAdapter(spAdapter);
        spSeller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShopSeller shopSeller = spAdapter.getData().get(i);
                sellerId = shopSeller.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spPayState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                payType = i + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void initListView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_cash, lvRecode, false);
        TextView payDat = (TextView) headerView.findViewById(R.id.payDat);
        TextView paySeller = (TextView) headerView.findViewById(R.id.paySeller);
        TextView payState = (TextView) headerView.findViewById(R.id.payState);
        TextView paySum = (TextView) headerView.findViewById(R.id.paySum);
        TextView payCac = (TextView) headerView.findViewById(R.id.payCac);
        headerView.setBackgroundColor(Color.parseColor("#f6f8f7"));
        payDat.setText("日期");
        payDat.setTextColor(Color.parseColor("#333333"));

        payState.setText("支付类型");
        payState.setTextColor(Color.parseColor("#333333"));

        paySeller.setText("销售员");
        paySeller.setTextColor(Color.parseColor("#333333"));

        paySum.setText("销售总额");
        paySum.setTextColor(Color.parseColor("#333333"));

        payCac.setText("实收合计");
        payCac.setTextColor(Color.parseColor("#333333"));


        adapter = new CommonAdapter<CashDate.StatisticsBean>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new CashItem();
            }
        };
        lvRecode.addHeaderView(headerView);
        lvRecode.setAdapter(adapter);

    }


    @Override
    public String setTitle() {
        return "现金";
    }

//    private void initDatePicker() {
//        SimpleDateFormat dateFormater = new SimpleDateFormat(
//                "yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.getTime();
//        tvStartData.setText(dateFormater.format(cal.getTime()) + "");
//
//        cal.set(Calendar.DAY_OF_MONTH,
//                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        tvEndTime.setText(dateFormater.format(cal.getTime()));
//
//    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new CashPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);

    }

    @OnClick({R.id.tv_start_data, R.id.tv_end_time, R.id.iv_search, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_data:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            tvStartData.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");


                break;
            case R.id.tv_end_time:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            tvEndTime.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
            case R.id.iv_search:
                if(tvStartData.getText().toString().contains("请选择")){
                    startDate = "";
                }
                if(tvEndTime.getText().toString().contains("请选择")){
                    endDate = "";
                }
                presenter.loadCashInfo(InventoryApplication.getShopId() + "",
                        startDate,
                        endDate,
                        sellerId + "", payType);
                break;
            case R.id.iv_add:

                JumpUtil jumpUtil = new JumpUtil(this, AddCashActivity.class);
                jumpUtil.start();


                break;
        }
    }

    @Override
    public void showCashInfo(CashDate cashDate) {

        adapter.setData(cashDate.getStatistics());
        allMoney.setText(new DecimalFormat("¥#.##").format(cashDate.getCashTotal()));
        getAll.setText(new DecimalFormat("¥#.##").format(cashDate.getSellTotal()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSaler(List<ShopSeller> ShopSellers) {
        spAdapter.setData(ShopSellers);
        spAdapter.notifyDataSetChanged();
    }
}
