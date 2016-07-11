package com.cnhubei.update;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cnhubei.systemupdate.VersionBean;
import com.cnhubei.systemupdate.VersionManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickUpdate(View view){
        VersionBean bean = new VersionBean("8.9.9", "http://10.99.101.4/1616-v3.0.2xin.apk ", "app测试2", 0, 90000000);
        bean.setCheckUpdater(false);
        VersionManager.VersionCheck(MainActivity.this, bean);
    }
}
