package com.example.myspeller

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.data.CardData
import com.example.my1.R

import com.example.my1.databinding.FragmentMultiPlayersBinding
import com.example.myspeller.adpter.MyAdapter
import com.example.myspeller.vewnodell.GameViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MultiPlayersFragment : Fragment() {

    private lateinit var binding: FragmentMultiPlayersBinding
    private var lastCardNumber: Int? = null
    private var pointsPlayer1 = 0
    private var pointsPlayer2 = 0

    private var currentPlayer = 1
    private val chosenCards = mutableListOf<CardData>()


    private val gameViewModel: GameViewModel by viewModels()


    var player1Name = arguments?.getString("player1Name")
    var player2Name = arguments?.getString("player2Name")



    private val myAdapter by lazy {
        MyAdapter(cardlist = gameViewModel.chosenCards)

    }


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
        binding = FragmentMultiPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         player1Name = arguments?.getString("player1Name")
         player2Name = arguments?.getString("player2Name")

       binding.myrckler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.myrckler.adapter = myAdapter

        binding.smaller.setOnClickListener { guess(false) }
        binding.bigger.setOnClickListener { guess(true) }
        binding.back.setOnClickListener { finish() }

        gameViewModel.currentCardResource?.let {
            binding.imagecard1.setImageResource(it)
        }
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
            false  // أول تخمين دائمًا غير صحيح (يمكن تعديله لاحقًا)
        }

        // إضافة البطاقة المختارة إلى القائمة وتحديث الـ RecyclerView
        if (gameViewModel.currentPlayer == 1) {
            gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, player1Name.toString() ?: "Player 1"))
        } else {
            gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, player2Name.toString() ?: "Player 2"))
        }
        // إخطار الـ adapter بأن البيانات قد تغيرت
        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)

        // تحديث النقاط بناءً على صحة التخمين (تجنب التغيير في النقاط أثناء التخمين الأول)
        if (gameViewModel.lastCardNumber != null) {
            if (gameViewModel.currentPlayer == 1) {
                gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
            } else {
                gameViewModel.pointsPlayer2 += if (isCorrectGuess) 10 else -10
            }
        }

        // منع النقاط من أن تصبح سلبية
        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)
        gameViewModel.pointsPlayer2 = maxOf(gameViewModel.pointsPlayer2, 0)



        // تحديث رقم آخر بطاقة تم لعبها
        gameViewModel.lastCardNumber = cardNumber

        // تبديل الأدوار بين اللاعبين
        gameViewModel.currentPlayer = if (gameViewModel.currentPlayer == 1) 2 else 1

        updatePointsText()
        checkForWinner()

        // عرض الدور الحالي للاعب
        showCurrentPlayerTurn()
    }

    private fun updatePointsText() {
        binding.pointer.text = "   $player1Name: ${gameViewModel.pointsPlayer1}   vs  $player2Name: ${gameViewModel.pointsPlayer2} "
    }
    private fun showCurrentPlayerTurn() {
        val currentPlayerName = if (gameViewModel.currentPlayer == 1) "$player1Name's turn!" else "$player2Name's turn!"

        // إنشاء Snackbar لعرض من هو في الدور
        val snackbar = Snackbar.make(binding.rootLayout, currentPlayerName, Snackbar.LENGTH_SHORT)

        // تخصيص محاذاة الـ Snackbar لليمين
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM // محاذاة الـ Snackbar لليمين
        snackbarView.layoutParams = params

        snackbar.show() // عرض الـ Snackbar
    }


    private fun checkForWinner() {
        if (gameViewModel.pointsPlayer1 >= 60 || gameViewModel.pointsPlayer2 >= 60) {
            val winner = if (gameViewModel.pointsPlayer1 >= 60) (gameViewModel.player1Name ?: "Player 1") else (gameViewModel.player2Name ?: "Player 2")
            showWinnerDialog(winner)
        }
    }
    private fun showWinnerDialog(winner: String) {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("🎉 Congratulations! ")
        dialogBuilder.setMessage("$winner has won the game!\nDo you want to play again?")
        dialogBuilder.setPositiveButton("Retry") { _, _ ->
            resetGame() // إعادة تعيين اللعبة
        }
        dialogBuilder.setNegativeButton("Exit") { _, _ ->
            finish() // إنهاء النشاط
        }
        dialogBuilder.setCancelable(false) // منع إغلاق النافذة بالنقر خارجها
        dialogBuilder.show()
    }


    private fun resetGame() {
        pointsPlayer1 = 0
        pointsPlayer2 = 0
        lastCardNumber = null
        chosenCards.clear()
        myAdapter.notifyDataSetChanged()
        currentPlayer = 1
        updatePointsText()
        showCurrentPlayerTurn()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // حفظ البيانات إلى الحزمة
        outState.putInt("pointsPlayer1", gameViewModel.pointsPlayer1)
        outState.putInt("pointsPlayer2", gameViewModel.pointsPlayer2)
        outState.putString("player1Name", gameViewModel.player1Name)
        outState.putString("player2Name", gameViewModel.player2Name)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // استعادة البيانات من الحزمة
        savedInstanceState?.let {
            gameViewModel.pointsPlayer1 = it.getInt("pointsPlayer1", 0)
            gameViewModel.pointsPlayer2 = it.getInt("pointsPlayer2", 0)
            gameViewModel.player1Name = it.getString("player1Name", "Player 1")
            gameViewModel.player2Name = it.getString("player2Name", "Player 2")
        }

        // تحديث النصوص بناءً على البيانات المستعادة
        updatePointsText()
    }
    private fun finish() {
        parentFragmentManager.popBackStack()
    }



}



