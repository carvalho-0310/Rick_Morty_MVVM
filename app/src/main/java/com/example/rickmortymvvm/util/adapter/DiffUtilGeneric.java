package com.example.rickmortymvvm.util.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilGeneric<T extends DiffUtilGeneric.Compare<T>> extends DiffUtil.Callback {
    List<T> oldList;
    List<T> newList;

    public DiffUtilGeneric(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).isEqual(oldList.get(oldItemPosition));
    }

    public interface Compare<T> {
        int getId();
        boolean isEqual(T o);
    }
}
