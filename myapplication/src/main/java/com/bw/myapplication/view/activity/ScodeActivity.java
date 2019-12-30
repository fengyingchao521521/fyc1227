package com.bw.myapplication.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.myapplication.R;
import com.bw.myapplication.model.bean.Bean;
import com.bw.myapplication.model.bean.JavaBean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScodeActivity extends AppCompatActivity {

    @BindView(R.id.tv_qr_content)
    TextView tvQrContent;
    @BindView(R.id.btn_qr_open_image)
    Button btnQrOpenImage;
    @BindView(R.id.iv_qr_picture)
    ImageView ivQrPicture;
    @BindView(R.id.butt01)
    Button butt01;
    @BindView(R.id.butt02)
    Button butt02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scode);
        ButterKnife.bind(this);
        CodeUtils.init(this);

        ivQrPicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(ivQrPicture, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(ScodeActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(ScodeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });


                return true;
            }
        });

    }

    @OnClick({R.id.butt01, R.id.butt02, R.id.btn_qr_open_image, R.id.iv_qr_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_qr_open_image:
                String content = tvQrContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    Bitmap image = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    ivQrPicture.setImageBitmap(image);
                }


                break;
            case R.id.iv_qr_picture:


                break;


            case R.id.butt01:
                EventBus.getDefault().post(new Bean("weng", 28));

                break;
            case R.id.butt02:
                EventBus.getDefault().post("aaaaa");

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
    public void onGetXXXBean(Bean bean) {
        Toast.makeText(this, "接收成功"+bean.getName(), Toast.LENGTH_SHORT).show();
    }
    @Subscribe
    public void onGetString(String  string) {
        Toast.makeText(this, "接收失败"+string, Toast.LENGTH_SHORT).show();
    }

}
