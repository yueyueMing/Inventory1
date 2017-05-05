package drawthink.com.inventory.data.remote;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <b>类名称：</b> RetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年06月20日 上午10:51<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Singleton
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    @Named("local")
    public Retrofit providerLocalNoAuthorRetrofitClient(@Named("local") OkHttpClient okHttpClient) {
        String LOCAL_BASE_URL = "http://121.199.2.174:88/inventory/";
//        String LOCAL_BASE_URL = "http://218.203.104.60:8089/HospitalServer/";
        return new Retrofit.Builder()
                .baseUrl(LOCAL_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }

}
