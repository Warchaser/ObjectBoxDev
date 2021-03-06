package com.warchaser.objcetboxdev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.warchaser.objcetboxdev.R;
import com.warchaser.objcetboxdev.nosql.entity.ExampleEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryAdapter extends BaseAdapter {

    private List<ExampleEntity> mDataList = new ArrayList<>();
    private Context mContext;

    private OnRemoveClicked mOnRemoveClicked;
    private boolean mIsRemoveMode = false;

    public QueryAdapter(Context context){
        this.mContext = context;
    }

    public QueryAdapter(Context context, boolean isRemoveMode){
        this.mContext = context;
        this.mIsRemoveMode = isRemoveMode;
    }

    public void setIsRemoveMode(boolean isRemoveMode){
        this.mIsRemoveMode = isRemoveMode;
        notifyDataSetChanged();
    }

    public void notifyDataSetAllChanged(List<ExampleEntity> list){
        if (mDataList != null){
            mDataList.clear();

            if (list != null){
                mDataList.addAll(list);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh = null;
        final ExampleEntity bean = (ExampleEntity) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_quey, parent, false);
            vh = new VH(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }

        vh.mTvId.setText(String.valueOf(bean.id));
        vh.mTvName.setText(bean.name);
        vh.mTvAge.setText(String.valueOf(bean.age));

        if (mIsRemoveMode){
            vh.mBtnRemove.setVisibility(View.VISIBLE);
            vh.mBtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnRemoveClicked != null){
                        mOnRemoveClicked.onClick(bean.id);
                    }
                }
            });
        } else {
            vh.mBtnRemove.setVisibility(View.GONE);
        }

        return convertView;
    }

    public interface OnRemoveClicked{
        void onClick(long id);
    }

    public void setOnRemoveClickedAction(OnRemoveClicked listener){
        this.mOnRemoveClicked = listener;
    }

    class VH{
        VH(View view){
            ButterKnife.bind(this, view);
        }

        @BindView(R.id.tv_id)
        TextView mTvId;

        @BindView(R.id.tv_name)
        TextView mTvName;

        @BindView(R.id.tv_age)
        TextView mTvAge;

        @BindView(R.id.btn_remove)
        Button mBtnRemove;

    }
}
