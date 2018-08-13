package com.warchaser.objcetboxdev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.warchaser.objcetboxdev.R;
import com.warchaser.objcetboxdev.adapter.QueryAdapter;
import com.warchaser.objcetboxdev.app.BaseActivity;
import com.warchaser.objcetboxdev.nosql.dao.BaseDao;
import com.warchaser.objcetboxdev.nosql.entity.ExampleEntity;
import com.warchaser.objcetboxdev.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ResultActivity extends BaseActivity {

    @BindView(R.id.list_view)
    ListView mListView;

    private Unbinder mUnBinder;

    private BaseDao<ExampleEntity> mDao;

    private QueryAdapter mAdapter;

    private String mMode = Constant.MODE_REMOVE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mUnBinder = ButterKnife.bind(this);
        mDao = new BaseDao<>(ExampleEntity.class);

        initialize();
    }

    private void initialize(){
        List<ExampleEntity> list = mDao.getAllNow();

        Intent intent = getIntent();
        if(intent != null){
            mMode = intent.getStringExtra(Constant.OP_MODE);
        }

        if(Constant.MODE_QUERY.equals(mMode)){
            mAdapter = new QueryAdapter(this);
        } else {
            mAdapter = new QueryAdapter(this, true);
            mAdapter.setOnRemoveClickedAction(new QueryAdapter.OnRemoveClicked() {
                @Override
                public void onClick(long id) {
                    mDao.remove(id);
                    mAdapter.notifyDataSetAllChanged(mDao.getAllNow());
                }
            });
        }

        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetAllChanged(list);
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

        if (mAdapter != null){
            mAdapter.notifyDataSetAllChanged(null);
            mAdapter = null;
        }
    }
}
