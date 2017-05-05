package drawthink.com.inventory.functions.sell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.Seller;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductDetail;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.sell.Presenter.SellDetailPresenter;
import drawthink.com.inventory.functions.sell.contract.SellDetailContract;

/**
 * <b>类名称：</b> SellDetailActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月28日 8:42<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class SellDetailActivity extends BaseActivity implements SellDetailContract.View {
    @BindView(R.id.bt_sale)
    LinearLayout btSale;
    @BindView(R.id.banner)
    Banner banner;
    SellDetailPresenter presenter;
    @BindView(R.id.sellPriceTv)
    TextView sellPriceTv;
    @BindView(R.id.stockTv)
    TextView stockTv;

    @BindView(R.id.rg_shopDetailColor)
    RadioGroup rgShopDetailColor;
    @BindView(R.id.rg_shopDetailModel)
    RadioGroup rgShopDetailModel;
    @BindView(R.id.rg_payType)
    RadioGroup rgPayType;
    @BindView(R.id.rg_salesman)
    RadioGroup rgSellers;
    @BindView(R.id.sell_count)
    TextView num;//销售数量
    @BindView(R.id.iv_addNum)
    ImageView ivAddNum;
    @BindView(R.id.iv_reduce)
    ImageView ivReduce;
    @BindView(R.id.tv_calcAll)
    EditText tvCalcAll;
    @BindView(R.id.productNameTv)
    TextView productNameTv;

    @Inject
    UserSpUtils useSp;
    @BindView(R.id.payForMoney)
    RadioButton payForMoney;
    @BindView(R.id.payForWeixin)
    RadioButton payForWeixin;

    private String shopId;
    double price = 0;
    private long count = 1;
    private ProductDetail productDetail;
    private long stockNum;
    private double totalPay;
    private boolean isCashPay = true;
    private PayForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_detail);
        ButterKnife.bind(this);
        this.shopId = String.valueOf(InventoryApplication.getShopId());
        presenter.loadSeller(shopId);
        int productId = getIntent().getIntExtra("productId", 1);
        presenter.loadProductDetails(productId);
        rgPayType.setOnCheckedChangeListener((group, checkedId) -> {
            isCashPay = checkedId == payForMoney.getId();
        });
    }


    private void initBanner(List<String> images) {
        banner.setImageLoader(new GrideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public String setTitle() {
        return "商品详情";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new SellDetailPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);

    }

    @OnClick(R.id.bt_sale)
    public void onClick() {
        bindForm();
        if(valid()){
            if(isCashPay){
                new AlertDialog.Builder(this).setTitle("销售")
                        .setMessage(String.format("是否现金销售该商品,总价 : %s元",
                                new DecimalFormat("¥#.##").format(form.getPrice())))
                        .setNegativeButton("确定", (dialog, which) -> presenter.pay(form))
                        .setPositiveButton("取消",null).show();
            }else{
                GatherActivity.startTo(SellDetailActivity.this,form);
            }
        }
    }

    private boolean valid() {
        boolean isValid = true;
        String tvCalcAllStr = tvCalcAll.getText().toString();
        try {
            double parseDouble = Double.parseDouble(tvCalcAllStr);
            if(parseDouble <= 0 ){
                toast("您输入的合计金额不正确，请重新输入！");
                tvCalcAll.setText(new DecimalFormat("#.##").format(totalPay));
                isValid = false;
            }else{
                form.setPrice(parseDouble);
            }
        }catch (Exception e){
            toast("您输入的合计金额不正确，请重新输入！");
            tvCalcAll.setText(new DecimalFormat("#.##").format(totalPay));
            isValid = false;
        }
        return isValid;
    }

    private void bindForm() {
        form = new PayForm(InventoryApplication.getShopName()
                ,productDetail.getCode()
                ,productDetail.getName()
                ,productDetail.getProductId()
                ,productDetail.getStockId()
                ,useSp.getUserId()
                ,useSp.getUserName()
                ,InventoryApplication.getShopId());
        RadioButton rbPayType = (RadioButton) findViewById(rgPayType.getCheckedRadioButtonId());
        form.setPayType(rbPayType.getText().toString());
        RadioButton rbColor = (RadioButton) rgShopDetailColor.findViewById(rgShopDetailColor.getCheckedRadioButtonId());
        form.setProductColor(rbColor.getText().toString());
        RadioButton rbModel = (RadioButton) rgShopDetailModel.findViewById(rgShopDetailModel.getCheckedRadioButtonId());
        form.setProductType(rbModel.getText().toString());
        form.setProductName(productDetail.getName());
        form.setSellNum(Integer.parseInt(num.getText().toString()));
        RadioButton  sellerRb = (RadioButton) rgSellers.findViewById(rgSellers.getCheckedRadioButtonId());
        form.setSellerId(rgSellers.getCheckedRadioButtonId());
        form.setSellerName(sellerRb.getText().toString());
        form.setPrice(totalPay);
    }

    @OnClick({R.id.iv_addNum, R.id.iv_reduce})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_addNum:
                if (count >= stockNum) {
                    count = stockNum;
                    toast("销售数量不能大于库存０");
                } else {
                    count++;
                }
                break;
            case R.id.iv_reduce:
                if (count <= 0) {
                    count = 0;
                    toast("销售数量不能小于０");
                } else {
                    count--;
                }
                break;
        }
        totalPay = count * price;
        tvCalcAll.setText(new DecimalFormat("#.##").format(totalPay));
        num.setText(String.format("%d", count));

    }

    @Override
    public void showProductDetails(ProductDetail productDetail) {
        if (productDetail != null) {
            this.productDetail = productDetail;
            productNameTv.setText(productDetail.getName());
            List<String> image = productDetail.getImage();
            if (image != null && image.size() > 0) {
                initBanner(image);
            }
            this.price = productDetail.getPrice();
            sellPriceTv.setText(new DecimalFormat("¥#.##").format(productDetail.getPrice()));
            stockNum = productDetail.getStockNum();
            stockTv.setText(String.format("%d", stockNum));
            tvCalcAll.setText(String.valueOf(count * price));
            List<String> colors = productDetail.getColors();
            if (colors != null && colors.size() > 0) {
                for (int i = 0; i < colors.size(); i++) {
                    RadioButton tempButton = new RadioButton(this);
                    tempButton.setGravity(Gravity.CENTER);
                    tempButton.setText(colors.get(i));
                    tempButton.setId(i);
                    tempButton.setTextColor(Color.parseColor("#333333"));
                    tempButton.setBackgroundResource(R.drawable.tab_xuanze);
                    tempButton.setButtonDrawable(android.R.color.transparent);
                    tempButton.setLines(1);
                    tempButton.setPadding(ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10));
                    if (i == 0) {
                        tempButton.setChecked(true);
                    }
                    RadioGroup.LayoutParams lp =
                            new RadioGroup.LayoutParams(ConvertUtils.dp2px(60),
                                    ConvertUtils.dp2px(40));
                    lp.rightMargin = ConvertUtils.dp2px(10);
                    rgShopDetailColor.addView(tempButton, lp);
                }
            }

            List<String> types = productDetail.getTypes();
            if (types != null && types.size() > 0) {
                for (int i = 0; i < types.size(); i++) {
                    RadioButton tempButton = new RadioButton(this);
                    tempButton.setText(types.get(i));
                    tempButton.setGravity(Gravity.CENTER);
                    tempButton.setId(i);
                    tempButton.setTextColor(Color.parseColor("#333333"));
                    tempButton.setBackgroundResource(R.drawable.tab_xuanze);
                    tempButton.setButtonDrawable(android.R.color.transparent);
                    tempButton.setLines(1);
                    tempButton.setPadding(ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10),
                            ConvertUtils.dp2px(10));
                    if (i == 0) {
                        tempButton.setChecked(true);
                    }
                    RadioGroup.LayoutParams lp =
                            new RadioGroup.LayoutParams(ConvertUtils.dp2px(60),
                            ConvertUtils.dp2px(40));
                    lp.rightMargin = ConvertUtils.dp2px(10);
                    rgShopDetailModel.addView(tempButton, lp);
                }
            }
        } else {
            toast("未获取到该商品的详细信息,请稍后重试.....");
        }


    }

    @Override
    public void showSeller(List<Seller> sellers) {
        if (sellers != null && sellers.size() > 0) {
            for (int i = 0; i < sellers.size(); i++) {
                RadioButton tempButton = new RadioButton(this);
                RadioGroup.LayoutParams lp =
                        new RadioGroup.LayoutParams(ConvertUtils.dp2px(60),
                                ConvertUtils.dp2px(40));
                lp.rightMargin = ConvertUtils.dp2px(10);
                tempButton.setPadding(ConvertUtils.dp2px(10),
                        ConvertUtils.dp2px(10),
                        ConvertUtils.dp2px(10),
                        ConvertUtils.dp2px(10));
                tempButton.setId(sellers.get(i).getId());
                tempButton.setText(sellers.get(i).getName());
                tempButton.setBackgroundResource(R.drawable.tab_xuanze);
                tempButton.setButtonDrawable(android.R.color.transparent);
                tempButton.setGravity(Gravity.CENTER);
                tempButton.setLines(1);
                tempButton.setTextColor(Color.parseColor("#333333"));
                if (i == 0) {
                    tempButton.setChecked(true);
                }
                rgSellers.addView(tempButton, lp);
            }
        }
    }

    class GrideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            System.out.println(path);
            Glide.with(SellDetailActivity.this).load(path).into(imageView);
        }
    }
}
