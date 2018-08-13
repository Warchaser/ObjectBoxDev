package com.warchaser.objcetboxdev.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.warchaser.objcetboxdev.R;
import com.warchaser.objcetboxdev.app.App;
import com.warchaser.objcetboxdev.app.BaseActivity;
import com.warchaser.objcetboxdev.nosql.dao.BaseDao;
import com.warchaser.objcetboxdev.nosql.entity.ExampleEntity;
import com.warchaser.objcetboxdev.util.Constant;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    private Unbinder mUnBinder = null;

    private BaseDao<ExampleEntity> mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnBinder = ButterKnife.bind(this);
        mDao = new BaseDao<>(ExampleEntity.class);
    }

    private void insert(){
        mDao.insert(new ExampleEntity("Achan", 26));
    }

    private void query(){
        startActivity(new Intent(this, ResultActivity.class));
//        List<ExampleEntity> list = mDao.getAllNow();
    }

    @OnClick({R.id.btn_insert, R.id.btn_remove, R.id.btn_query})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_remove:
                startResultActivity(Constant.MODE_REMOVE);
                break;
            case R.id.btn_query:
                startResultActivity(Constant.MODE_QUERY);
//                query();
                break;
            default:
                break;
        }
    }

    private void startResultActivity(String opMode){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(Constant.OP_MODE, opMode);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null){
            mUnBinder.unbind();
            mUnBinder = null;
        }

        if(mDao != null){
            mDao.close();
            mDao = null;
        }

        App.getInstance().exitApp();

    }
}
