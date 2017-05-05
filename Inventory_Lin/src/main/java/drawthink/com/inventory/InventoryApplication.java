package drawthink.com.inventory;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;
import com.socks.library.KLog;

import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.component.DaggerAppComponent;


/**
 * <b>类名称：</b> com.drawthink.hospital.HospitalApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-11-28 上午11:43<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class InventoryApplication extends Application{


    private static AppComponent appComponent;
    private static Context mContext;
    private static int shopId;
    private static String shopName ="测试";
    private static String payImage ;

    public static String getPayImage() {
        return payImage;
    }

    public static void setPayImage(String payImage) {
        InventoryApplication.payImage = payImage;
    }

    public static InventoryApplication get(Context context) {
        return (InventoryApplication) context.getApplicationContext();
    }

    public static InventoryApplication get() {
        return (InventoryApplication) mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initStetho();
        initKLog();
        Utils.init(this);
    }

    private void initKLog() {
        KLog.init(BuildConfig.DEBUG, "hospital");
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public synchronized AppComponent getAppComponent() {
        if (appComponent == null) {
            this.setupAppComponent();
        }
        return appComponent;
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    public static int getShopId() {
        return shopId;
    }

    public static void setShopId(int shopId) {
        InventoryApplication.shopId = shopId;
    }

    public static String getShopName() {
        return shopName;
    }

    public static void setShopName(String shopName) {
        InventoryApplication.shopName = shopName;
    }
}
