package com.example.myspeller.ui

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my1.R

import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MultiPlayerActivity : AppCompatActivity() {
//    lateinit var image: ImageView
//    lateinit var bigerbtn: Button
//    lateinit var samlbtn: Button
//    lateinit var backButton: Button
//    private lateinit var pointsTextView: TextView
//    lateinit var recyclerView: RecyclerView
//    private val chosenCards = mutableListOf<CardData>()
//    private var lastCardNumber: Int? = null
//    private var pointsPlayer1 = 0
//    private var pointsPlayer2 = 0
//    lateinit var rootView: ConstraintLayout
//    //var player1Name: String? = null
//    //var player2Name: String? = null
//    private lateinit var playerNames: List<String>
//    private var currentPlayerIndex = 0
//    private var currentPlayer = 1  // 1 for Player 1, 2 for Player 2
//
//    // استخدام ViewModel
//    private val gameViewModel: GameViewModel by viewModels()
//
//
//    private var player1Name: String
//        get() = gameViewModel.player1Name ?: "Player 1"
//        set(value) {
//            gameViewModel.player1Name = value
//        }
//
//    private var player2Name: String
//        get() = gameViewModel.player2Name ?: "Player 2"
//        set(value) {
//            gameViewModel.player2Name = value
//        }
//    // Initialize the adapter once and update its data later
//    private val myAdapter by lazy {
//        MyAdapter(cardlist = gameViewModel.chosenCards)
//    }
//
//
//
//    private val cardImages = listOf(
//        Pair(R.drawable.nine, 9),
//        Pair(R.drawable.thortine, 13),
//        Pair(R.drawable.one, 1),
//        Pair(R.drawable.tewleve, 11),
//        Pair(R.drawable.tow, 2),
//        Pair(R.drawable.seven, 7)
//    )
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_multi_player)
//
//        player1Name = intent.getStringExtra("player1Name") ?: "Player 1"
//        player2Name = intent.getStringExtra("player2Name") ?: "Player 2"
//
//        if (gameViewModel.player1Name == null || gameViewModel.player2Name == null) {
//            gameViewModel.player1Name = intent.getStringExtra("player1Name") ?: "Player 1"
//            gameViewModel.player2Name = intent.getStringExtra("player2Name") ?: "Player 2"
//        }
//
//        //player1Name = gameViewModel.player1Name
//       // player2Name = gameViewModel.player2Name
//
//        image = findViewById(R.id.imagecard1)
//        bigerbtn = findViewById(R.id.bigger)
//        samlbtn = findViewById(R.id.smaller)
//        recyclerView = findViewById(R.id.myrckler)
//        pointsTextView = findViewById(R.id.pointer)
//        backButton = findViewById(R.id.back)
//        rootView = findViewById(R.id.root_layout)
//
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = myAdapter
//
//        samlbtn.setOnClickListener { guess(false) }
//        bigerbtn.setOnClickListener { guess(true) }
//        backButton.setOnClickListener { finish() }
//
//        gameViewModel.currentCardResource?.let {
//            image.setImageResource(it)
//        }
//    }
//
//    private fun guess(isBigger: Boolean) {
//        // اختيار بطاقة عشوائية
//        val newCardIndex = Random.nextInt(cardImages.size)
//        val newCardResource = cardImages[newCardIndex].first
//        val cardNumber = cardImages[newCardIndex].second
//        gameViewModel.currentCardResource = newCardResource
//        // عرض البطاقة الجديدة
//        image.setImageResource(newCardResource)
//
//        // التحقق إذا كانت التخمينات صحيحة
//        val isCorrectGuess = if (gameViewModel.lastCardNumber != null) {
//            if (isBigger) {
//                cardNumber > gameViewModel.lastCardNumber!!
//            } else {
//                cardNumber < gameViewModel.lastCardNumber!!
//            }
//        } else {
//            false  // أول تخمين دائمًا غير صحيح (يمكن تعديله لاحقًا)
//        }
//
//        // إضافة البطاقة المختارة إلى القائمة وتحديث الـ RecyclerView
//        if (gameViewModel.currentPlayer == 1) {
//            gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, player1Name ?: "Player 1"))
//        } else {
//            gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, player2Name ?: "Player 2"))
//        }
//        // إخطار الـ adapter بأن البيانات قد تغيرت
//        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)
//
//        // تحديث النقاط بناءً على صحة التخمين (تجنب التغيير في النقاط أثناء التخمين الأول)
//        if (gameViewModel.lastCardNumber != null) {
//            if (gameViewModel.currentPlayer == 1) {
//                gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
//            } else {
//                gameViewModel.pointsPlayer2 += if (isCorrectGuess) 10 else -10
//            }
//        }
//
//        // منع النقاط من أن تصبح سلبية
//        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)
//        gameViewModel.pointsPlayer2 = maxOf(gameViewModel.pointsPlayer2, 0)
//
//        updatePointsText()
//        checkForWinner()
//
//        // تحديث رقم آخر بطاقة تم لعبها
//        gameViewModel.lastCardNumber = cardNumber
//
//        // تبديل الأدوار بين اللاعبين
//        gameViewModel.currentPlayer = if (gameViewModel.currentPlayer == 1) 2 else 1
//
//        // عرض الدور الحالي للاعب
//        showCurrentPlayerTurn()
//    }
//
//    private fun updatePointsText() {
//      pointsTextView.text = "player1: = ${gameViewModel.player1Name}:  ${gameViewModel.pointsPlayer1}   |   player2: = ${gameViewModel.player2Name}: ${gameViewModel.pointsPlayer2}"
//    }
//    private fun showCurrentPlayerTurn() {
//        val currentPlayerName = if (gameViewModel.currentPlayer == 1) "$player1Name's turn!" else "$player2Name's turn!"
//
//        // إنشاء Snackbar لعرض من هو في الدور
//        val snackbar = Snackbar.make(rootView, currentPlayerName, Snackbar.LENGTH_SHORT)
//
//        // تخصيص محاذاة الـ Snackbar لليمين
//        val snackbarView = snackbar.view
//        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
//        params.gravity = Gravity.BOTTOM // محاذاة الـ Snackbar لليمين
//        snackbarView.layoutParams = params
//
//        snackbar.show() // عرض الـ Snackbar
//    }
//
//
//    private fun checkForWinner() {
//        if (gameViewModel.pointsPlayer1 >= 60 || gameViewModel.pointsPlayer2 >= 60) {
//            val winner = if (gameViewModel.pointsPlayer1 >= 60) (gameViewModel.player1Name ?: "Player 1") else (gameViewModel.player2Name ?: "Player 2")
//            showWinnerDialog(winner)
//        }
//    }
//    private fun showWinnerDialog(winner: String) {
//        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
//        dialogBuilder.setTitle("🎉 Congratulations!")
//        dialogBuilder.setMessage("$winner has won the game!\nDo you want to play again?")
//        dialogBuilder.setPositiveButton("Retry") { _, _ ->
//            resetGame() // إعادة تعيين اللعبة
//        }
//        dialogBuilder.setNegativeButton("Exit") { _, _ ->
//            finish() // إنهاء النشاط
//        }
//        dialogBuilder.setCancelable(false) // منع إغلاق النافذة بالنقر خارجها
//        dialogBuilder.show()
//    }
//
//
//    private fun resetGame() {
//        pointsPlayer1 = 0
//        pointsPlayer2 = 0
//        lastCardNumber = null
//        chosenCards.clear()
//        myAdapter.notifyDataSetChanged()
//        currentPlayer = 1
//        updatePointsText()
//        showCurrentPlayerTurn()
//    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("pointsPlayer1", gameViewModel.pointsPlayer1)
//        outState.putInt("pointsPlayer2", gameViewModel.pointsPlayer2)
//        outState.putString("player1Name", gameViewModel.player1Name)
//        outState.putString("player2Name", gameViewModel.player2Name)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        gameViewModel.pointsPlayer1 = savedInstanceState.getInt("pointsPlayer1")
//        gameViewModel.pointsPlayer2 = savedInstanceState.getInt("pointsPlayer2")
//        gameViewModel.player1Name = savedInstanceState.getString("player1Name")
//        gameViewModel.player2Name = savedInstanceState.getString("player2Name")
//        updatePointsText()  // تحديث النص بعد استعادة البيانات
//    }
//
//



}