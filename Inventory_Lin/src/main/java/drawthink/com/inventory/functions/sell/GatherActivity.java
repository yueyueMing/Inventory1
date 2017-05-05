package drawthink.com.inventory.functions.sell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.sell.Presenter.GatherPresenter;
import drawthink.com.inventory.functions.sell.contract.GatherContract;

/**
 * <b>类名称：</b> GatherActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月28日 15:02<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class GatherActivity extends BaseActivity implements GatherContract.View {

    @Inject
    UserSpUtils userSp;
//    @BindView(R.id.image_code)
//    ImageView imageView;
    @BindView(R.id.pay_failed)
    Button payFailed;
    @BindView(R.id.pay_successful)
    Button paySuccessful;

    private PayForm payForm;
    private GatherPresenter presenter;
    private ImageView imageCodeview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather, true);
        ButterKnife.bind(this);
        imageCodeview = (ImageView) findViewById(R.id.image_code);
        payForm = (PayForm) getIntent().getSerializableExtra("payform");
        Glide.with(this).load(InventoryApplication.getPayImage()).into(imageCodeview);
        getToolbar().setNavigationIcon(null);
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
        return "我要收款";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new GatherPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @OnClick({R.id.pay_successful,R.id.pay_failed})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.pay_failed:
                finish();
                break;
            case R.id.pay_successful:
                presenter.pay(payForm);
                break;
        }
    }

    public static void startTo(Context context, PayForm form) {
        Intent intent = new Intent(context,GatherActivity.class);
        intent.putExtra("payform",form);
        context.startActivity(intent);
    }
}
