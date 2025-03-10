package ru.mchernyaev.lab1

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostsAdapter : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback) {
    private val likedPosts = HashMap<Int, Boolean>()

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postImageView: ImageView = itemView.findViewById(R.id.post_image)
        private val postTextView: TextView = itemView.findViewById(R.id.post_text)
        private val commentButton: ImageView = itemView.findViewById(R.id.comment_button)
        private val shareButton: ImageView = itemView.findViewById(R.id.share_button)
        val likeButton: ImageView = itemView.findViewById(R.id.like_button)

        init {
            commentButton.setOnClickListener {
                addOnClickAnimationToButton(commentButton)
            }

            shareButton.setOnClickListener {
                addOnClickAnimationToButton(shareButton)
            }
        }

        fun bind(post: Post, isLiked: Boolean) {
            if (post.text.isEmpty()) {
                postTextView.visibility = View.GONE
            } else {
                postTextView.text = post.text
            }
            if (post.imageURL.isNotBlank()) {
                Glide.with(itemView.context)
                    .load(post.imageURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(postImageView)
                postImageView.contentDescription = post.imageDescription
            }
            updateLikeButton(isLiked)
        }

        fun updateLikeButton(isLiked: Boolean) {
            if (isLiked) {
                likeButton.setImageResource(R.drawable.ic_like_filled)
            } else {
                likeButton.setImageResource(R.drawable.ic_like_empty)
            }
        }

        private fun addOnClickAnimationToButton(button: ImageView) {
            val context = itemView.context
            val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
            val scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down)

            button.startAnimation(scaleUp)

            Handler(Looper.getMainLooper()).postDelayed({
                button.startAnimation(scaleDown)
            }, 100)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        val isLiked: Boolean = likedPosts[post.id] ?: false

        holder.bind(post, isLiked)

        holder.likeButton.setOnClickListener {
            val newIsLiked = !isLiked
            likedPosts[post.id] = newIsLiked
            holder.updateLikeButton(newIsLiked)
            notifyItemChanged(position)
        }
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
