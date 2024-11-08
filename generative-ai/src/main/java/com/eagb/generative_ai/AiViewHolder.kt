package com.eagb.generative_ai

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

open class AiViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    class AiInputViewHolder(
        itemView: View
    ) : AiViewHolder(itemView) {
        private val txtInput: TextView = itemView.findViewById(R.id.txt_input)
        fun bind(text: String) {
            txtInput.text = text
        }
    }

    class AiOutputViewHolder(
        itemView: View
    ) : AiViewHolder(itemView) {
        private val txtOutput: TextView = itemView.findViewById(R.id.txt_output)
        fun bind(text: String) {
            txtOutput.text = text
        }
    }
}