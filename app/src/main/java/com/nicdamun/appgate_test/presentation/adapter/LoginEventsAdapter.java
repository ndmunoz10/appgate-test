package com.nicdamun.appgate_test.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nicdamun.appgate_test.R;
import com.nicdamun.appgate_test.databinding.ItemLoginEventBinding;
import com.nicdamun.appgate_test.domain.models.LoginEventModel;

public class LoginEventsAdapter extends ListAdapter<LoginEventModel, LoginEventsAdapter.LoginEventsViewHolder> {

    public LoginEventsAdapter() {
        super(new UserDiffUtil());
    }

    @NonNull
    @Override
    public LoginEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LoginEventsViewHolder(
            ItemLoginEventBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LoginEventsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class LoginEventsViewHolder extends RecyclerView.ViewHolder {

        private final ItemLoginEventBinding binding;

        public LoginEventsViewHolder(@NonNull ItemLoginEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final LoginEventModel loginEventModel) {
            final boolean isSuccess = loginEventModel.getTimeStamp() != null;
            if (isSuccess) {
                binding.tvLoginEvent.setText(loginEventModel.getTimeStamp().toString());
            }
            final int stringResourceId = isSuccess ? R.string.home_success_event : R.string.home_failure_event;
            binding.tvLoginEventResult.setText(
                binding.getRoot().getContext().getString(stringResourceId)
            );
        }
    }
}
