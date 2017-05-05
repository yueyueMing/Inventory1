package drawthink.com.inventory.data.remote.server.inventory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import drawthink.com.inventory.data.remote.server.inventory.service.CashService;
import drawthink.com.inventory.data.remote.server.inventory.service.OtherService;
import drawthink.com.inventory.data.remote.server.inventory.service.PayService;
import drawthink.com.inventory.data.remote.server.inventory.service.SellerService;
import drawthink.com.inventory.data.remote.server.inventory.service.ProductService;
import drawthink.com.inventory.data.remote.server.inventory.service.ShopService;
import drawthink.com.inventory.data.remote.server.inventory.service.StockService;
import drawthink.com.inventory.data.remote.server.inventory.service.UserService;
import retrofit2.Retrofit;

/**
 * Created by zhangzhiliang on 16/12/9.
 */

@Module
public class LocalServerModule {
    @Provides
    @Singleton
    public UserService providerUserServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(UserService.class);
    }

    @Provides
    @Singleton
    public OtherService providerOtherServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(OtherService.class);
    }

    @Provides
    @Singleton
    public ProductService providerProductServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(ProductService.class);
    }

    @Provides
    @Singleton
    public StockService providerStockServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(StockService.class);
    }

    @Provides
    @Singleton
    public CashService providerCashServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(CashService.class);
    }
    @Provides
    @Singleton
    public PayService providerPayServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(PayService.class);
    }
    @Provides
    @Singleton
    public ShopService provideShopServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(ShopService.class);
    }
    @Provides
    @Singleton
    public SellerService provideSellerServiceModule(@Named("local") Retrofit retrofitClient) {
        return retrofitClient.create(SellerService.class);
    }


}