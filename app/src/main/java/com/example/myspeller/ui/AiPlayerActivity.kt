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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my1.R

import kotlin.random.Random

class AiPlayerActivity : AppCompatActivity() {
//    lateinit var image: ImageView
//    lateinit var bigerbtn: Button
//    lateinit var samlbtn: Button
//    lateinit var backButton: Button
//    private lateinit var pointsTextView: TextView
//    lateinit var recyclerView: RecyclerView
//    private val chosenCards = mutableListOf<CardData>()
//    private var lastCardNumber: Int? = null
//    private var pointsPlayer1 = 0
//    private var pointsComputer = 0
//    lateinit var rootView: ConstraintLayout
//   // var player1Name: String? = null
//    private val compytorPlay="compyter"
//    //private lateinit var playerNames: List<String>
//    //private var currentPlayerIndex = 0
//    private var currentPlayer = 1  // 1 for Player 1, 2 for Player 2
//
//    private val gameViewModel: GameViewModel by viewModels()
//
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
//    private var player1Name: String
//        get() = gameViewModel.player1Name ?: "Player 1"
//        set(value) {
//            gameViewModel.player1Name = value
//        }
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_multi_player)
//
//        // تهيئة عناصر الواجهة
//        image = findViewById(R.id.imagecard1) // تأكد من تهيئة المتغير قبل استخدامه
//        bigerbtn = findViewById(R.id.bigger)
//        samlbtn = findViewById(R.id.smaller)
//        recyclerView = findViewById(R.id.myrckler)
//        pointsTextView = findViewById(R.id.pointer)
//        backButton = findViewById(R.id.back)
//        rootView = findViewById(R.id.root_layout)
//
//        // تهيئة الـ RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = myAdapter
//
//        // تحقق من وجود أسماء اللاعبين وتحديثها في الـ ViewModel
//        if (gameViewModel.player1Name == null) {
//            gameViewModel.player1Name = intent.getStringExtra("player1Name") ?: "Player 1"
//        }
//
//        // تحديث الصورة الحالية إذا كانت موجودة في الـ ViewModel
//        gameViewModel.currentCardResource?.let {
//            image.setImageResource(it)
//        }
//
//        // إضافة الأحداث للأزرار
//        samlbtn.setOnClickListener { guess(false) }
//        bigerbtn.setOnClickListener { guess(true) }
//        backButton.setOnClickListener { finish() }
//    }
//
//
//
//    private fun guess(isBigger: Boolean) {
//
//
//
//
//        // مراقبة التغييرات في LiveData للنقاط والدور الحالي
//
//        // تعطيل الأزرار أثناء التنفيذ
//        setButtonsEnabled(false)
//
//        // اختيار بطاقة عشوائية
//        val newCardIndex = Random.nextInt(cardImages.size)
//        val newCardResource = cardImages[newCardIndex].first
//        val cardNumber = cardImages[newCardIndex].second
//
//        gameViewModel.currentCardResource = newCardResource
//
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
//            false
//        }
//
//        // إضافة البطاقة المختارة إلى القائمة وتحديث RecyclerView
//        gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, player1Name ?: "Player 1"))
//        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)
//
//        // تحديث النقاط
//        if (gameViewModel.lastCardNumber != null) {
//           gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
//        }
//        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)
//        updatePointsText()
//
//        checkForWinner()
//
//        // تحديث رقم آخر بطاقة
//       gameViewModel.lastCardNumber = cardNumber
//
//        // تبديل الأدوار
//        gameViewModel.currentPlayer = 2
//        showCurrentPlayerTurn()
//
//        // تأخير دور الكمبيوتر لمدة 1.5 ثانية
//        rootView.postDelayed({
//            playComputerTurn()
//        }, 1500)
//    }
//
//    private fun updatePointsText() {
//        pointsTextView.text = "player1 :${gameViewModel.player1Name }${gameViewModel.pointsPlayer1}" +
//                "   | :${gameViewModel.compytorPlay}:" +
//                "${gameViewModel.pointsComputer}"
//    }
//
//
//    private fun decideComputerGuess(): Boolean {
//        // منطق تخمين الكمبيوتر (بسيط)
//        return if (gameViewModel.lastCardNumber != null) {
//            if (gameViewModel.lastCardNumber!! < 7) true else false
//        } else {
//            Random.nextBoolean() // اختيار عشوائي إذا لم يكن هناك بطاقة سابقة
//        }
//    }
//    private fun showCurrentPlayerTurn() {
//        val currentPlayerName = if (currentPlayer == 1) "Player 1$player1Name" else "Player 2$compytorPlay"
//
//        // إنشاء Snackbar لعرض من هو في الدور
//        val snackbar = Snackbar.make(rootView, "$currentPlayerName's turn!", Snackbar.LENGTH_SHORT)
//
//        // تخصيص محاذاة الـ Snackbar لليمين
//        val snackbarView = snackbar.view
//        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
//        params.gravity = Gravity.BOTTOM // محاذاة الـ Snackbar لليمين
//        snackbarView.layoutParams = params
//
//        snackbar.show() // عرض الـ Snackbar
//    }
//    private fun playComputerTurn() {
//        // اختيار بطاقة جديدة
//        val newCardIndex = Random.nextInt(cardImages.size)
//        val newCardResource = cardImages[newCardIndex].first
//        val cardNumber = cardImages[newCardIndex].second
//
//        gameViewModel.currentCardResource = newCardResource
//
//        // اتخاذ قرار الكمبيوتر (أكبر أو أصغر)
//        val isBigger = decideComputerGuess()
//
//        // عرض البطاقة الجديدة
//        image.setImageResource(newCardResource)
//
//        // تحقق من صحة التخمين للكمبيوتر
//        val isCorrectGuess = if (isBigger) cardNumber > gameViewModel.lastCardNumber!! else cardNumber < gameViewModel.lastCardNumber!!
//        gameViewModel.pointsComputer += if (isCorrectGuess) 10 else -10
//        gameViewModel.pointsComputer = maxOf(gameViewModel.pointsComputer, 0)
//
//        // إضافة البطاقة إلى القائمة وتحديث RecyclerView
//        gameViewModel.chosenCards.add(CardData(newCardResource, cardNumber, isCorrectGuess, gameViewModel.compytorPlay))
//        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)
//
//        // تحديث النقاط
//        updatePointsText()
//        checkForWinner()
//
//        // تحديث رقم آخر بطاقة
//        gameViewModel.lastCardNumber = cardNumber
//
//        // إعادة الدور إلى اللاعب
//        gameViewModel.currentPlayer = 1
//        showCurrentPlayerTurn()
//
//        // إعادة تمكين الأزرار
//        setButtonsEnabled(true)
//    }
//    private fun setButtonsEnabled(enabled: Boolean) {
//        bigerbtn.isEnabled = enabled
//        samlbtn.isEnabled = enabled
//    }
//
//    private fun checkForWinner() {
//        if (gameViewModel.pointsPlayer1 >= 60 || gameViewModel.pointsComputer >= 60) {
//            val winner = if (gameViewModel.pointsPlayer1 >= 60) gameViewModel.player1Name ?: "Player 1" else gameViewModel.compytorPlay
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
//        pointsComputer = 0
//        lastCardNumber = null
//        chosenCards.clear()
//        myAdapter.notifyDataSetChanged()
//        currentPlayer = 1
//        updatePointsText()
//        showCurrentPlayerTurn()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("pointsPlayer1", gameViewModel.pointsPlayer1)
//        outState.putInt("pointsComputer", gameViewModel.pointsComputer)
//        outState.putString("player1Name", gameViewModel.player1Name)
//        outState.putString("coumpyterPlay", gameViewModel.compytorPlay)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        gameViewModel.pointsPlayer1 = savedInstanceState.getInt("pointsPlayer1")
//        gameViewModel.pointsComputer = savedInstanceState.getInt("pointsComputer")
//        gameViewModel.player1Name = savedInstanceState.getString("player1Name")
//        updatePointsText()  // تحديث النص بعد استعادة البيانات
//    }



}
