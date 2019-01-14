package small.com.small_demo.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import small.com.small_demo.exceptionclass.MyBugly;

public class SmallDemoApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        MyBugly.getMyBugly().init(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
