package com.example.my1.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my1.R
import com.example.my1.adpter.MyAdapter
import com.example.my1.data.CardData
import com.example.my1.vewnodell.GameViewModel
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

//    private lateinit var image: ImageView
//    private lateinit var bigerbtn: Button
//    private lateinit var samlbtn: Button
//    private lateinit var backButton: Button
//    private lateinit var pointsTextView: TextView
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var player1Name: String
//
//    // استخدام ViewModel
//    private val gameViewModel: GameViewModel by viewModels()
//
//    // Adapter يتصل مباشرة مع قائمة ViewModel
//    private val myAdapter by lazy {
//        MyAdapter(cardlist = gameViewModel.chosenCards)
//    }
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
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_game)
//
//        player1Name = intent.getStringExtra("player1Name") ?: "Player 1"
//
//        // ربط عناصر واجهة المستخدم
//        image = findViewById(R.id.imagecard1)
//        bigerbtn = findViewById(R.id.bigger)
//        samlbtn = findViewById(R.id.smaller)
//        recyclerView = findViewById(R.id.myrckler)
//        pointsTextView = findViewById(R.id.pointer)
//        backButton = findViewById(R.id.back)
//
//        gameViewModel.currentCardResource?.let {
//            image.setImageResource(it)
//        }
//
//        // إعداد RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = myAdapter
//
//        // عرض النقاط المحفوظة من ViewModel
//        updatePointsText()
//
//        // التعامل مع الأحداث
//        samlbtn.setOnClickListener { guess(false) }
//        bigerbtn.setOnClickListener { guess(true) }
//        backButton.setOnClickListener { finish() }
//
//        Toast.makeText(this, "مرحبًا $player1Name!", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun guess(isBigger: Boolean) {
//        // اختيار بطاقة عشوائية
//        val newCardIndex = Random.nextInt(cardImages.size)
//        val newCardResource = cardImages[newCardIndex].first
//        val cardNumber = cardImages[newCardIndex].second
//
//        gameViewModel.currentCardResource = newCardResource
//        // عرض البطاقة الجديدة
//     image.setImageResource(newCardResource)
//
//        // تحقق من صحة التخمين
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
//        // إضافة البطاقة المختارة إلى القائمة داخل ViewModel
//        gameViewModel.chosenCards.add(
//            CardData(
//                newCardResource,
//                cardNumber,
//                isCorrectGuess,
//                player1Name
//            )
//        )
//
//        // تحديث RecyclerView
//        myAdapter.notifyItemInserted(gameViewModel.chosenCards.size - 1)
//
//        // تحديث النقاط بناءً على التخمين
//        if (gameViewModel.lastCardNumber != null) {
//            gameViewModel.pointsPlayer1 += if (isCorrectGuess) 10 else -10
//        }
//
//        // منع النقاط من أن تصبح سالبة
//        gameViewModel.pointsPlayer1 = maxOf(gameViewModel.pointsPlayer1, 0)
//
//        // تحديث النصوص
//        updatePointsText()
//
//        // تحديث آخر بطاقة
//        gameViewModel.lastCardNumber = cardNumber
//    }
//
//    private fun updatePointsText() {
//        pointsTextView.text = "$player1Name: ${gameViewModel.pointsPlayer1}"
//    }
}
