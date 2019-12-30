package com.bw.fyc1227;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondeActivity extends AppCompatActivity {

    @BindView(R.id.butt01)
    Button butt01;
    @BindView(R.id.butt02)
    Button butt02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.butt01, R.id.butt02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.butt01:
                EventBus.getDefault().post(new Bean("weng",28));
                break;
            case R.id.butt02:
                EventBus.getDefault().post("ddddd");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void  onGetXxxBean(Bean bean){
        Toast.makeText(this, "接收成功"+bean.getName(), Toast.LENGTH_SHORT).show();
    }
    @Subscribe
    public void onGetString(String string){
        Toast.makeText(this, "接收成功"+string, Toast.LENGTH_SHORT).show();
    }



}
