package com.apporio.ERetailing;


import android.app.Application;

import com.apporio.ERetailing.type.Food;
import com.apporio.ERetailing.type.FoodProvider;
import com.apporio.ERetailing.type.Good;
import com.apporio.ERetailing.type.GoodProvider;
import com.apporio.ERetailing.type.ImageItem;
import com.apporio.ERetailing.type.ImageItemProvider;
import com.apporio.ERetailing.type.RichItem;
import com.apporio.ERetailing.type.RichItemProvider;
import com.apporio.ERetailing.type.StringProvider;
import com.shizhefei.view.multitype.ItemBinderFactory;
import com.squareup.leakcanary.LeakCanary;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        // This code can be placed in the application initialization , static registration Provider
        // Each new ItemBinderFactory () instance shared these registered Provider
        ItemBinderFactory.registerStaticProvider(ImageItem.class, new ImageItemProvider());
        ItemBinderFactory.registerStaticProvider(RichItem.class, new RichItemProvider());
        ItemBinderFactory.registerStaticProvider(Food.class, new FoodProvider());
        ItemBinderFactory.registerStaticProvider(Good.class, new GoodProvider());
        ItemBinderFactory.registerStaticProvider(String.class, new StringProvider());
    }
}