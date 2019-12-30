package com.bw.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.myapplication.R;
import com.bw.myapplication.base.BaseActivity;
import com.bw.myapplication.contract.IContract;
import com.bw.myapplication.model.bean.JavaBean;
import com.bw.myapplication.presenter.HomPresenter;
import com.bw.myapplication.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomPresenter> implements IContract.IView {


    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void initData() {
        mPresenter.getHomeData();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected HomPresenter providePresenter() {
        return new HomPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(JavaBean javaBean) {
        Log.i("xx",javaBean.getCurrent_page()+"");
        List<JavaBean.DataBean> data = javaBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        //适配器
        MyAdapter myAdapter = new MyAdapter(data);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnitemClickListener(new MyAdapter.onitemClickListener() {
            @Override
            public void onitemClick(int postion) {
                Intent intent = new Intent(MainActivity.this, ScodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
