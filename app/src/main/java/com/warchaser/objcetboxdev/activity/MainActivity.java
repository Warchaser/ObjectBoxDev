package com.warchaser.objcetboxdev.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.warchaser.objcetboxdev.R;
import com.warchaser.objcetboxdev.nosql.dao.BaseDao;
import com.warchaser.objcetboxdev.nosql.entity.ExampleEntity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {


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
        List<ExampleEntity> list = mDao.getAllNow();
    }

    @OnClick({R.id.btn_insert, R.id.btn_remove, R.id.btn_query})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_remove:
                break;
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }
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

    }
}
