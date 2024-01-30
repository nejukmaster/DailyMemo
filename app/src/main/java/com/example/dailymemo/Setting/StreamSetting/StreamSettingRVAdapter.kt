package com.example.dailymemo.Setting.StreamSetting

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dailymemo.DailyBoard.DailyBoardRVAdapter
import com.example.dailymemo.R
import com.example.dailymemo.databinding.ItemDailyBoardBinding
import com.example.dailymemo.databinding.ItemStreamTypeBinding
import java.io.File

class StreamSettingRVAdapter(activity: FragmentActivity) : RecyclerView.Adapter<StreamSettingRVAdapter.ViewHolder>() {

    val act = activity
    val userProfile = getProfieImage()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StreamSettingRVAdapter.ViewHolder {
        val binding: ItemStreamTypeBinding = ItemStreamTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StreamSettingRVAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(val binding: ItemStreamTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos : Int) {
            binding.streamNameTv.text = "일상"

            if(userProfile != null) {
                binding.userProfileIv.setImageDrawable(userProfile)
            }
            else {
                binding.userProfileIv.setImageResource(R.drawable.basic_user_profile)
            }
        }
    }

    private fun getProfieImage() : Drawable? {

        val preferences = act.getPreferences(Context.MODE_PRIVATE)
        val filePath = preferences.getString("user_profile_image_path", "") ?: ""

        if (filePath.isNotEmpty()) {
            // 파일 경로가 비어 있지 않은 경우에만 실행
            val file = File(filePath)
            if (file.exists()) {
                // 파일이 존재하는 경우에만 실행
                val drawable = Drawable.createFromPath(filePath)
                return drawable
            } else {
                return null
            }
        } else {
            return null
        }
    }

    }


