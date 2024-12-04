package com.example.my1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my1.adpter.MyAdapter
import com.example.my1.data.CardData
import com.example.my1.databinding.FragmentSinglPlayBinding
import com.example.my1.vewnodell.GameViewModel
import kotlin.random.Random

class SinglPlayFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()


    private val myAdapter by lazy {
        MyAdapter(cardlist = gameViewModel.chosenCards)
    }

    var player1Name = arguments?.getString("player1Name")





    private val cardImages = listOf(
        Pair(R.drawable.nine, 9),
        Pair(R.drawable.thortine, 13),
        Pair(R.drawable.one, 1),
        Pair(R.drawable.tewleve, 12),
        Pair(R.drawable.tow, 2),
        Pair(R.drawable.card8, 8),
        Pair(R.drawable.card10, 10),
        Pair(R.drawable.card3, 3),
        Pair(R.drawable.card4, 4),
        Pair(R.drawable.card5, 5),
        Pair(R.drawable.card6, 6),
        Pair(R.drawable.card11, 11),
        Pair(R.drawable.seven, 7)
    )


    lateinit var binding: FragmentSinglPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        player1Name = arguments?.getString("player1Name")
        gameViewModel.currentCardResource?.let {
            binding.imagecard1.setImageResource(it)
        }


        binding.myrckler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.myrckler.adapter = myAdapter


        updatePointsText()


        binding.smaller.setOnClickListener { guess(false) }
        binding.bigger.setOnClickListener { guess(true) }
        binding.back.setOnClickListener { finish() }

        Toast.makeText(context, "مرحبًا $player1Name!", Toast.LENGTH_SHORT).show()
    }

    private fun guess(isBigger: Boolean) {

        val newCardIndex = Random.nextInt(cardImages.size)
        val newCardResource = cardImages[newCardIndex].first
        val cardNumber = cardImages[newCardIndex].second

        gameViewModel.currentCardResource = newCardResource
        binding.imagecard1.setImageResource(newCardResource)


        val isCorrectGuess = if (gameViewModel.lastCardNumber != null) {
            if (isBigger) {
                cardNumber > gameViewModel.lastCardNumber!!
            } else {
                cardNumber < gameViewModel.lastCardNumber!!
            }
        } else {
            false
        }

        gameViewModel.chosenCards.add(
            CardData(
                newCardResource,
                cardNumber,
                isCorrectGuess,
                player1Name.toString()
            )
        )

        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)

        if (gameViewModel.lastCardNumber != null) {
            gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
        }

        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)

        updatePointsText()

        gameViewModel.lastCardNumber = cardNumber
    }

    private fun updatePointsText() {
        binding.pointer.text = "$player1Name: ${gameViewModel.pointsPlayer1}"
    }

    private fun finish() {
        parentFragmentManager.popBackStack()
    }
}
