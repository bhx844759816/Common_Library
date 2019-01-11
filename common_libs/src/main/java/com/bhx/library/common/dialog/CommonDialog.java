package com.bhx.library.common.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 标准Dialog
 * 显示
 */
public class CommonDialog extends DialogFragment {

    private String mTitle;// Dialog得标题
    private String mMessage;// Dialog的提示内容
    private int mBgColor; // Dialog得背景颜色
    private int mLineColor;//分割线得颜色
    private int mTitleTextSize;//标题得文本大小
    private int mMessageTextSize;
    private boolean isCancel;
    private OnCommonDialogCallBack mCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public CommonDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * 设置显示得消息体
     *
     * @param message
     * @return
     */
    public CommonDialog setMessage(String message) {
        this.mMessage = message;
        return this;
    }

    /**
     * 设置是否可以关闭对话框
     *
     * @param cancel
     * @return
     */
    public CommonDialog setCancel(boolean cancel) {
        this.isCancel = cancel;
        return this;
    }

    /**
     * 设置点击回调
     *
     * @param callBack
     * @return
     */
    public CommonDialog setOnCommonDialogCallBack(OnCommonDialogCallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }

    /**
     * 实例化Dialog
     *
     * @return
     */
    public static CommonDialog newInstance() {
        Bundle args = new Bundle();
        CommonDialog fragment = new CommonDialog();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 点击Dialog得回调
     */
    public interface OnCommonDialogCallBack {

        void confirm();

        void cancel();
    }
}
