package drawthink.com.inventory;

import android.app.Application;

import com.blankj.utilcode.utils.SPUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;

/**
 * <b>类名称：</b> AppModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午2:20<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    protected Application providerApplication(){
        return application;
    }

    @Provides
    @Singleton
    @Named("app")
    protected SPUtils providerAppSharedPreference() {
        return new SPUtils("application");
    }

    @Provides
    @Singleton
    @Named("user")
    protected SPUtils providerUserSharedPreference() {
        return new SPUtils("user");
    }

    @Provides
    @Singleton
    protected UserSpUtils providerUserSpUtils(@Named("user") SPUtils spUtils){
        return new UserSpUtils(spUtils);
    }

}
