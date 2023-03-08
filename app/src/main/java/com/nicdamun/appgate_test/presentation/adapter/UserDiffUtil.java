package com.nicdamun.appgate_test.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.nicdamun.appgate_test.domain.models.LoginEventModel;

public class UserDiffUtil extends DiffUtil.ItemCallback<LoginEventModel> {

    @Override
    public boolean areItemsTheSame(@NonNull LoginEventModel oldItem, @NonNull LoginEventModel newItem) {
        return oldItem.getUsername().equals(newItem.getUsername());
    }

    @Override
    public boolean areContentsTheSame(@NonNull LoginEventModel oldItem, @NonNull LoginEventModel newItem) {
        return oldItem.equals(newItem);
    }
}
