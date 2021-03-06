package com.goyin.mvp.presenter.login.impl;

import android.os.Handler;
import android.os.Message;

import com.goyin.mvp.base.BasePresenter;
import com.goyin.mvp.presenter.login.interfaces.LoginContract;
import com.goyin.mvp.view.common.activity.MainActivity;
import com.goyin.mvp.view.login.LoginActivity;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/11/9 上午11:19
 **/
public class LoginPresenterImp extends BasePresenter<LoginActivity> implements LoginContract.Presenter {

    private static final int LOGIN_SUCCUSS=200;

    private static final int LOGIN_FAIL=500;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case LOGIN_SUCCUSS:
                    if(mView!=null) {
                        mView.dismiss();
                        mView.showSuccessWithStatus("登录成功");
                        mView.startAct(MainActivity.class, true);
                    }
                    break;
                case LOGIN_FAIL:
                    if(mView!=null) {
                        mView.dismiss();
                        mView.showErrorWithStatus("登录失败!");
                    }
                    break;
            }
        }
    };
    /**
     *  登录业务逻辑
     * @param tel 账号
     * @param pwd 密码
     */
    @Override
    public void login(final String tel, final String pwd) {
        /**
         *   针对数据就行校验
         *       项目中校验数据使用正则表达式
         *   这里进行简单校验
         */
        if(tel.isEmpty())
        {
            mView.showsInfoWithStatus("请填写账号!");
            return;

        }
        else if(pwd.isEmpty())
        {
            mView.showsInfoWithStatus("请填写密码!");
            return;
        }

          mView.showWithProgress("加载中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message=Message.obtain();
                if(tel.toLowerCase().endsWith("123")&&pwd.endsWith("123"))
                {
                    message.what=LOGIN_SUCCUSS;
                    mHandler.sendMessage(message);
                }
                else
                {
                    message.what=LOGIN_FAIL;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }
    @Override
    public void regist() {
       mView.showSuccessWithStatus("注册成功");

    }

    @Override
    public void forgetPwd() {
       mView.showSuccessWithStatus("忘记密码");
    }
}
