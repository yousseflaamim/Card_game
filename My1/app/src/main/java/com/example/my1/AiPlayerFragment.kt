package com.example.my1



import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my1.adpter.MyAdapter
import com.example.my1.data.CardData
import com.example.my1.databinding.FragmentAiPlayerBinding
import com.example.my1.vewnodell.GameViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class AiPlayerFragment : Fragment() {

    private var pointsPlayer1 = 0
    private var pointsComputer = 0
    private var currentPlayer = 1
    private val chosenCards = mutableListOf<CardData>()
    private var lastCardNumber: Int? = null
    var player1Name = arguments?.getString("player1Name")


    private lateinit var binding: FragmentAiPlayerBinding
    private val gameViewModel: GameViewModel by viewModels()
   // private val args: AiPlayerFragmentArgs by navArgs()
    private val myAdapter by lazy { MyAdapter(cardlist = gameViewModel.chosenCards) }

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAiPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player1Name = arguments?.getString("player1Name")


        binding.myrckler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.myrckler.adapter = myAdapter

        // updet cart
        gameViewModel.currentCardResource?.let { binding.imagecard1.setImageResource(it) }

        // sting button
        binding.smaller.setOnClickListener { guess(isBigger = false) }
        binding.bigger.setOnClickListener { guess(isBigger = true) }
        binding.back.setOnClickListener { requireActivity().finish() }

        // updet point
        updatePointsText()
        showCurrentPlayerTurn()
    }

    private fun guess(isBigger: Boolean) {
        setButtonsEnabled(false)

        val newCardIndex = Random.nextInt(cardImages.size)
        val newCardResource = cardImages[newCardIndex].first
        val cardNumber = cardImages[newCardIndex].second

        gameViewModel.currentCardResource = newCardResource
        binding.imagecard1.setImageResource(newCardResource)

        val isCorrectGuess = gameViewModel.lastCardNumber?.let {
            if (isBigger) cardNumber > it else cardNumber < it
        } ?: false

        // updet point
        gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)

        // إadd cardث RecyclerView
        gameViewModel.chosenCards.add(
            CardData(
                newCardResource,
                cardNumber,
                isCorrectGuess,
                gameViewModel.player1Name ?: "Player 1"
            )
        )
        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)

        updatePointsText()
        checkForWinner()

        gameViewModel.lastCardNumber = cardNumber
        gameViewModel.currentPlayer = 2
        showCurrentPlayerTurn()

        binding.root.postDelayed({ playComputerTurn() }, 1500)
    }

    private fun playComputerTurn() {
        val newCardIndex = Random.nextInt(cardImages.size)
        val newCardResource = cardImages[newCardIndex].first
        val cardNumber = cardImages[newCardIndex].second

        gameViewModel.currentCardResource = newCardResource
        binding.imagecard1.setImageResource(newCardResource)

        val isBigger = decideComputerGuess()
        val isCorrectGuess = gameViewModel.lastCardNumber?.let {
            if (isBigger) cardNumber > it else cardNumber < it
        } ?: false

        gameViewModel.pointsComputer += if (isCorrectGuess) 10 else -10
        gameViewModel.pointsComputer = maxOf(gameViewModel.pointsComputer, 0)

        gameViewModel.chosenCards.add(
            CardData(
                newCardResource,
                cardNumber,
                isCorrectGuess,
                "Computer"
            )
        )
        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)

        updatePointsText()
        checkForWinner()

        gameViewModel.lastCardNumber = cardNumber
        gameViewModel.currentPlayer = 1
        showCurrentPlayerTurn()
        setButtonsEnabled(true)
    }

    private fun decideComputerGuess(): Boolean {
        return gameViewModel.lastCardNumber?.let { it < 7 } ?: Random.nextBoolean()
    }

    private fun checkForWinner() {
        if (gameViewModel.pointsPlayer1 >= 60 || gameViewModel.pointsComputer >= 60) {
            val winner = if (gameViewModel.pointsPlayer1 >= 60) gameViewModel.player1Name
                ?: "Player 1" else "Computer"
            showWinnerDialog(winner)
        }
    }

    private fun showWinnerDialog(winner: String) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("🎉 Congratulations!")
            .setMessage("$winner has won the game! Do you want to play again?")
            .setPositiveButton("Retry") { _, _ -> resetGame() }
            .setNegativeButton("Exit") { _, _ -> requireActivity().finish() }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    private fun resetGame() {
        pointsPlayer1 = 0
        pointsComputer = 0
        lastCardNumber = null
        chosenCards.clear()
        myAdapter.notifyDataSetChanged()
        currentPlayer = 1
        updatePointsText()
        showCurrentPlayerTurn()
    }

    private fun updatePointsText() {
        binding.pointer.text =
            "$player1Name: ${gameViewModel.pointsPlayer1}    vs   Computer: ${gameViewModel.pointsComputer}"
    }

    private fun showCurrentPlayerTurn() {
        val currentPlayerName = if (gameViewModel.currentPlayer == 1) gameViewModel.player1Name
            ?: "Player 1" else "Computer"
        Snackbar.make(binding.rootLayout, "$currentPlayerName's turn!", Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.bigger.isEnabled = enabled
        binding.smaller.isEnabled = enabled
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //save point and name
        outState.putInt("pointsPlayer1", gameViewModel.pointsPlayer1)
        outState.putInt("pointsComputer", gameViewModel.pointsComputer)
        outState.putString("player1Name", gameViewModel.player1Name)
        outState.putString("coumpyterPlay", gameViewModel.compytorPlay)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //back data
        savedInstanceState?.let {
            gameViewModel.pointsPlayer1 = savedInstanceState.getInt("pointsPlayer1")
            gameViewModel.pointsComputer = savedInstanceState.getInt("pointsComputer")
            gameViewModel.player1Name = savedInstanceState.getString("player1Name")
            updatePointsText()
        }
    }
}