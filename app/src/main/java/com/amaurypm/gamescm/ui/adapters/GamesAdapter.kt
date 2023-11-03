package com.amaurypm.gamescm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.gamescm.databinding.GameElementBinding
import com.amaurypm.gamescm.model.Game
import com.bumptech.glide.Glide

/**
 * Creado por Amaury Perea Matsumura el 03/11/23
 */
class GamesAdapter(private var games: ArrayList<Game>, private var onGameClicked: (Game) -> Unit) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    class ViewHolder(private var binding: GameElementBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(game: Game){
            binding.tvTitle.text = game.title
            binding.tvDeveloper.text = "EA Sports"

            Glide.with(itemView.context)
                .load(game.thunmbnail)
                .into(binding.ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val game = games[position]

        holder.bind(game)

        holder.itemView.setOnClickListener {
            onGameClicked(game)
        }

    }

}