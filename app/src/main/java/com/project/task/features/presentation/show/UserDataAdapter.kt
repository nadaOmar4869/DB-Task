package com.project.task.features.presentation.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.task.databinding.CardUserDataBinding
import com.project.task.features.data.local.entities.UserEntity

class UserDataAdapter :
    ListAdapter<UserEntity, BaseHolder<UserEntity>>(object : DiffUtil.ItemCallback<UserEntity>() {

        override fun areItemsTheSame(
            oldItem: UserEntity, newItem: UserEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserEntity, newItem: UserEntity
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    inner class SampleViewHolder(
        private val binding: CardUserDataBinding,
    ) : BaseHolder<UserEntity>(binding) {

        override fun bind(value: UserEntity, position: Int) {
            binding.user = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<UserEntity> {
        return SampleViewHolder(
            CardUserDataBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseHolder<UserEntity>, position: Int) {
        holder.bind(getItem(position)!!, position)
    }

}

abstract class BaseHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(value: T, position: Int)
}