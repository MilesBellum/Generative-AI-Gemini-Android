package com.eagb.generative_ai

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AiAdapter(
    private val promptList: MutableList<String>,
) : RecyclerView.Adapter<AiViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AiViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_ai_prompt_input,
                parent,
                false,
            )
            AiViewHolder.AiInputViewHolder(view)
        } else {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_ai_prompt_output,
                parent,
                false,
            )
            AiViewHolder.AiOutputViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: AiViewHolder,
        position: Int,
    ) {
        when (holder) {
            is AiViewHolder.AiInputViewHolder -> holder.bind(promptList[position])
            is AiViewHolder.AiOutputViewHolder -> holder.bind(promptList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2 // Toggles between 0 and 1
    }

    override fun getItemCount(): Int = promptList.size
}
