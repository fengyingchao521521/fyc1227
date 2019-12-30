package com.bw.myapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:03
 *@Description:${DESCRIPTION}
 *
 */public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), layoutId(), null);
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.actach(this);
        }
        initView(inflate);
        return inflate;

    }

    protected abstract void initView(View inflate);

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.datach();
        }
    }
}
