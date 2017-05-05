package drawthink.com.inventory.functions.cash.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.data.remote.server.inventory.vo.cash.CashDate;
import drawthink.com.inventory.utils.DateTimeUtils;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> CashItem <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 11:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class CashItem implements AdapterItem<CashDate.StatisticsBean> {

    @BindView(R.id.payDat)
    TextView payDat;
    @BindView(R.id.payState)
    TextView payState;
    @BindView(R.id.paySeller)
    TextView paySeller;
    @BindView(R.id.paySum)
    TextView paySum;
    @BindView(R.id.payCac)
    TextView payCac;

    @BindArray(R.array.item_pay)
    String[] payType;

    @Override
    public int getLayoutResId() {
        return R.layout.item_cash;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(CashDate.StatisticsBean statisticsBean, int i) {
        if(statisticsBean.getPayType() == 0){
            payState.setTextColor(Color.parseColor("#2dcc70"));
        }else{
            payState.setTextColor(Color.parseColor("#ff3636"));
        }
        payState.setText(payType[statisticsBean.getPayType()]);
        payDat.setText(DateTimeUtils.format(new Date(statisticsBean.getDate())));
        paySeller.setText(statisticsBean.getSellName());
        paySum.setText(new DecimalFormat("¥#.##").format(statisticsBean.getCash()));
        payCac.setText(new DecimalFormat("¥#.##").format(statisticsBean.getSell()));
    }


}
