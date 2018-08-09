package com.warchaser.objcetboxdev.nosql.dao;

import com.warchaser.objcetboxdev.app.App;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class BaseDao<T> {

    private Box<T> mBox;

    private BoxStore mBoxSore;

    public BaseDao(Class entityClass){
        mBoxSore = App.getInstance().getBoxStore();
        mBox = mBoxSore.boxFor(entityClass);
    }

    public long insert(T t){
        return mBox.put(t);
    }

    public void insertS(T ... t){
        mBox.put(t);
    }

    public void insertList(List<T> list){
        mBox.put(list);
    }

    public void remove(long id){
        mBox.remove(id);
    }

    public void removeS(long ... ids){
        mBox.remove(ids);
    }

    public void removeList(List<T> list){
        mBox.remove(list);
    }

    public List<T> getAllNow(){
        return mBox.query().build().find();
    }

    public void close(){
        mBox.closeThreadResources();
    }

}
