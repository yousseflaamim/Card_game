package com.example.myspeller.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.data.CardData
import com.example.my1.R


class MyAdapter(private val  cardlist: MutableList<CardData>)
    :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        var iv_image:ImageView=itemView.findViewById(R.id.imageView1)
       // var iv_numbercard:TextView=itemView.findViewById(R.id.nmberId)
        var iv_playername1:TextView=itemView.findViewById(R.id.name_player)
        //var iv_playername2:EditText=itemView.findViewById(R.id.editname2)

        var cardContainer: View = itemView.findViewById(R.id.card_container)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_list,
            parent,false)
       return MyViewHolder(view)
    }

    override fun getItemCount()=cardlist.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = cardlist[position]
        holder.iv_image.setImageResource(card.imageId)
       holder.iv_playername1.text=card.playerName.toString()

        
        //holder.iv_playername2.setText(card.playerName2.toString())
       // holder.iv_numbercard.text = card.nmberCard.toString()  // Convert number to string

        // Change the border color based on whether the guess was correct
        if (card.isGuessCorrect) {
            // Correct guess: Set green border
            holder.cardContainer.setBackgroundResource(R.drawable.border)
        } else {
            // Incorrect guess: Set red border
            holder.cardContainer.setBackgroundResource(R.drawable.border_red)
        }
    }

}