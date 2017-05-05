package drawthink.com.inventory.functions.statistics.adapter;

import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.data.remote.server.inventory.vo.statistics.Statistic;
import drawthink.com.inventory.utils.DateTimeUtils;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> itemStatistic <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月07日 10:00<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class itemStatistic implements AdapterItem<Statistic.StatisticsBean> {
    @BindView(R.id.sellDate)
    TextView sellDate;
    @BindView(R.id.sellName)
    TextView sellName;
    @BindView(R.id.sellId)
    TextView sellId;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.sellNum)
    TextView sellNum;
    @BindView(R.id.sellColor)
    TextView sellColor;
    @BindView(R.id.sellXi)
    TextView sellXi;
    @BindView(R.id.sellSeller)
    TextView sellSeller;
    @BindView(R.id.sellSum)
    TextView sellSum;

    @Override
    public int getLayoutResId() {
        return R.layout.item_statistics;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);

    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(Statistic.StatisticsBean statisticsBean, int i) {

        sellDate.setText(DateTimeUtils.format(new Date(statisticsBean.getSellDate())));
        sellName.setText(statisticsBean.getProductName());
        sellNum.setText(String.format(Locale.getDefault(),"%d", statisticsBean.getNum()));
        sellColor.setText(statisticsBean.getProductColor());
        sellXi.setText(statisticsBean.getProductType());
        sellSeller.setText(statisticsBean.getSeller());
        sellSum.setText(new DecimalFormat("¥#.##").format(statisticsBean.getTotal()));
        sellId.setText(statisticsBean.getProductCode());
        price.setText(new DecimalFormat("¥#.##").format(statisticsBean.getPrice()));


    }


}




