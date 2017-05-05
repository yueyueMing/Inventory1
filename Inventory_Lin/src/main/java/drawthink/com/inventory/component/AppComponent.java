package drawthink.com.inventory.component;


import javax.inject.Singleton;

import dagger.Component;
import drawthink.com.inventory.AppModule;
import drawthink.com.inventory.functions.cash.AddCashActivity;
import drawthink.com.inventory.functions.cash.CashActivity;
import drawthink.com.inventory.functions.login.LoginActivity;
import drawthink.com.inventory.functions.main.MainActivity;
import drawthink.com.inventory.functions.main.ShopSelectActivity;
import drawthink.com.inventory.functions.sell.GatherActivity;
import drawthink.com.inventory.functions.sell.SellActivity;
import drawthink.com.inventory.functions.sell.SellDetailActivity;
import drawthink.com.inventory.functions.splash.SplashActivity;
import drawthink.com.inventory.functions.statistics.StatisticsActivity;
import drawthink.com.inventory.functions.stock.StockActivity;

/**
 * <b>类名称：</b> AppComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午2:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    //addSubComponent
    NetComponent netComponent();

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(StatisticsActivity statisticsActivity);

    void inject(LoginActivity loginActivity);

    void inject(SellActivity sellActivity);
    void  inject(SellDetailActivity sellDetailActivity);

    void inject(CashActivity cashActivity);

    void inject(AddCashActivity addCashActivity);

    void inject(StockActivity stockActivity);

    void inject(ShopSelectActivity shopSelectActivity);

    void inject(GatherActivity gatherActivity);
}
