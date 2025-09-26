package ru.mchernyaev.lab1

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HeaderAdapter(
    private val header: Header, private var postsCount: Int = 0
) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coverImageView: ImageView = itemView.findViewById(R.id.cover)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatar)
        private val usernameTextView: TextView = itemView.findViewById(R.id.username)
        private val subscribersNumberTextView: TextView =
            itemView.findViewById(R.id.subscribers_number)
        private val subscriptionsNumberTextView: TextView =
            itemView.findViewById(R.id.subscriptions_number)
        private val postsNumberTextView: TextView = itemView.findViewById(R.id.posts_number)
        private val subscribeButton: Button = itemView.findViewById(R.id.subscribe_button)
        private var isSubscribed: Boolean = false // TODO: перенести в header

        init {
            subscribeButton.setOnClickListener {
                isSubscribed = !isSubscribed
                updateViewOnSubscribe()
            }
        }

        fun bind(header: Header, postCount: Int) {
            usernameTextView.text = header.username
            subscribersNumberTextView.text = header.subscribersNumber.toString()
            subscriptionsNumberTextView.text = header.subscriptionsNumber.toString()
            postsNumberTextView.text = postCount.toString()

            if (header.coverURL.isNotBlank()) {
                Glide.with(itemView.context)
                    .load(header.coverURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(coverImageView)
            }

            // TODO: добавить else

            if (header.avatarURL.isNotBlank()) {
                Glide.with(itemView.context)
                    .load(header.avatarURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(avatarImageView)
            }

            // TODO: добавить else
        }

        private fun updateViewOnSubscribe() {
            val context = itemView.context
            if (isSubscribed) {
                subscribeButton.text = context.getString(R.string.unsubscribe_button_text)
                subscribeButton.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorPrimaryVariant))
                updateSubscribersNumber(+1)
            } else {
                subscribeButton.text = context.getString(R.string.subscribe_button_text)
                subscribeButton.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.colorPrimary))
                updateSubscribersNumber(-1)
            }
        }

        private fun updateSubscribersNumber(delta: Int) {
            subscribersNumberTextView.let {
                val curSubsNumber = it.text.toString().toInt()
                it.text = (curSubsNumber + delta).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(header, postsCount)
    }

    override fun getItemCount(): Int {
        return 1
    }
}
