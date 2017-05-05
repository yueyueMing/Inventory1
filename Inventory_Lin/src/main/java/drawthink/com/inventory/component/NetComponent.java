package drawthink.com.inventory.component;



import javax.inject.Singleton;

import dagger.Subcomponent;
import drawthink.com.inventory.data.remote.OkHttpModule;
import drawthink.com.inventory.data.remote.RetrofitModule;
import drawthink.com.inventory.data.remote.server.inventory.LocalServerModule;
import drawthink.com.inventory.functions.cash.presenter.AddCashPresenter;
import drawthink.com.inventory.functions.cash.presenter.CashPresenter;
import drawthink.com.inventory.functions.login.presenter.LoginPresenter;
import drawthink.com.inventory.functions.main.presenter.MainPresenter;
import drawthink.com.inventory.functions.main.presenter.ShopSelectPresenter;
import drawthink.com.inventory.functions.sell.Presenter.GatherPresenter;
import drawthink.com.inventory.functions.sell.Presenter.SellDetailPresenter;
import drawthink.com.inventory.functions.sell.Presenter.SellPresenter;
import drawthink.com.inventory.functions.splash.presenter.SplashPresenter;
import drawthink.com.inventory.functions.statistics.presenter.StatisticPresenter;
import drawthink.com.inventory.functions.stock.presenter.StockPresenter;

/**
 * <b>类名称：</b> NetComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午3:07<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Subcomponent(modules = {
        RetrofitModule.class,
        OkHttpModule.class,
        LocalServerModule.class})
@Singleton
public interface NetComponent {

    void inject(SplashPresenter splashPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(ShopSelectPresenter shopSelectPresenter);
    void inject(SellPresenter sellPresenter);
    void inject(SellDetailPresenter sellDetailPresenter);
    void inject(CashPresenter cashPresenter);


    void inject(StockPresenter stockPresenter);

    void inject(StatisticPresenter statisticPresenter);

    void inject(AddCashPresenter addCashPresenter);

    void inject(GatherPresenter gatherPresenter);
}
