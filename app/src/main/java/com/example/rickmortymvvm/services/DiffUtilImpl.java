package com.example.rickmortymvvm.services;

import androidx.recyclerview.widget.DiffUtil;

import com.example.rickmortymvvm.models.Character;

import java.util.List;

public class DiffUtilImpl extends DiffUtil.Callback {
    List<Character> oldList;
    List<Character> newList;

    public DiffUtilImpl(List<Character> oldList, List<Character> newList) {
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
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).equals(oldList.get(oldItemPosition));
    }

}
