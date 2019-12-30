package com.bw.fyc1227;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 权限和动态权限库中已经都加了，不需要再加
 * <p>
 * 必须初始化
 * CodeUtils.init(this);
 * <p>
 * 用法1、 根据文字生成二维码
 * Bitmap qrBitmap = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
 * <p>
 * 用法2、 相机扫一扫识别二维码
 * CodeUtils.analyzeByCamera(this);   重写 onActivityResult方法，调用 CodeUtils.onActivityResult 接受结果
 * <p>
 * 用法3、 打开相册选择二维码图片识别二维码
 * CodeUtils.analyzeByPhotos(this);   重写 onActivityResult方法，调用 CodeUtils.onActivityResult 接受结果
 * <p>
 * 用法4、 长按或者点击二维码图片，识别二维码
 * CodeUtils.analyzeByImageView(imageView, AnalyzeCallback)
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_qr_content)
    TextView tvQrContent;
    @BindView(R.id.btn_qr_scan)
    Button btnQrScan;
    @BindView(R.id.btn_qr_open_image)
    Button btnQrOpenImage;
    @BindView(R.id.iv_qr_picture)
    ImageView ivQrPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // TODO: 2019/12/27 必须初始化
        CodeUtils.init(this);

//        NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/student/clazzstudent.json", new NetUtil.MyCallback() {
//            @Override
//            public void ongetJson(String json) {
//                Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//            }
//        });
        Map<String, String> map =new HashMap<>();
        map.put("phone","13774402024");
        map.put("pwd","123456");
        NetUtil.getInstance().getJsonPost("http://172.17.8.100/small/user/v1/login", map ,new NetUtil.MyCallback() {
            @Override
            public void ongetJson(String json) {
                Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });





        ivQrPicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO: 2019/12/27 用法4、 长按或者点击二维码图片，识别二维码
                CodeUtils.analyzeByImageView(ivQrPicture, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });

    }

    @OnClick({R.id.tv_qr_content, R.id.btn_qr_scan, R.id.btn_qr_open_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // TODO: 2019/12/27 点击生成二维码
            case R.id.tv_qr_content:
                // TODO: 2019/12/27  用法1、 根据文字生成二维码
                String content = tvQrContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    // TODO: 2019/12/27  生成不带头像的二维码
                    Bitmap image = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    ivQrPicture.setImageBitmap(image);
                }

                break;
            case R.id.btn_qr_scan:
                CodeUtils.analyzeByCamera(this);

                break;
            case R.id.btn_qr_open_image:
                // TODO: 2019/12/27 用法2、 相机扫一扫识别二维码
                CodeUtils.analyzeByPhotos(this);
                break;
        }
    }

    // TODO: 2019/12/28 带值返回 (如果只是长按就不需要这个方法)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
