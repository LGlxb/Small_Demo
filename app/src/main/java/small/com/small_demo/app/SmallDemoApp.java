package small.com.small_demo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class SmallDemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
