package com.bhx.library.common.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private boolean isShowTitle;//是否显示标题栏
    private boolean isFullScreen;//是否全屏
    private int orientation;// Activity得方向

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (orientation == 0) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (orientation == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(getLayoutId());
        //隐藏标题栏
        if (!isShowTitle && getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initData();
    }

    /**
     * 设置是否显示状态栏
     *
     * @param isShowTitle
     */
    protected void setIsShowTitle(boolean isShowTitle) {
        this.isShowTitle = isShowTitle;
    }

    /**
     * 设置是否全屏展示
     *
     * @param isFullScreen
     */
    protected void setIsFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * 设置方向
     *
     * @param orientation 0 表示竖屏 1表示横屏
     */
    protected void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * 跳转到指定类名得Activity
     *
     * @param clazz 类对象
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
