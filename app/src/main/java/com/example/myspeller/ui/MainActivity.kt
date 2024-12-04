package com.example.myspeller.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.my1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // إعداد NavController لفتح LoginFragment


//
//    lateinit var btngo: Button
//    lateinit var gameModeGroup: RadioGroup
//    lateinit var editName1:EditText
//    lateinit var editName2:EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        btngo = findViewById(R.id.buttongo)
//        gameModeGroup = findViewById(R.id.gameModeGroup)
//        editName1 = findViewById(R.id.editname1)
//        editName2 = findViewById(R.id.editname2)
//
//        editName2.visibility = View.GONE
//
//        gameModeGroup.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
//                R.id.singlePlayerRadioButton -> {
//                    editName1.hint = "أدخل اسمك"
//                    editName2.visibility = View.GONE
//                }
//
//                R.id.multiPlayerRadioButton -> {
//                    editName1.hint = "اسم اللاعب الأول"
//                    editName2.visibility = View.VISIBLE
//                }
//
//                R.id.aiPlayerRadioButton -> {
//                    editName1.hint = "أدخل اسمك"
//                    editName2.visibility = View.GONE
//                }
//            }
//        }
//
//        btngo.setOnClickListener {
//            // احصل على الزر المحدد من RadioGroup
//            val selectedModeId = gameModeGroup.checkedRadioButtonId
//
//            if (selectedModeId == -1) {
//                // إذا لم يتم اختيار أي زر
//                Toast.makeText(this, "يرجى اختيار وضع اللعب", Toast.LENGTH_SHORT).show()
//            } else {
//                // احصل على النص المرتبط بالزر
//                val selectedMode = findViewById<RadioButton>(selectedModeId).text.toString()
//
//                // الحصول على أسماء اللاعبين
//                val player1Name = editName1.text.toString().ifEmpty { "Player 1" }
//                val player2Name = editName2.text.toString().ifEmpty { "Player 2" }
//
//                // انتقل إلى النشاط المناسب وأرسل البيانات
//                val intent = when (selectedMode) {
//                    "لعب فردي" -> Intent(this, GameActivity::class.java).apply {
//                        putExtra("player1Name", player1Name) // إرسال اسم اللاعب الأول
//                    }
//
//                    "لعب مع لاعب آخر" -> Intent(this, MultiPlayerActivity::class.java).apply {
//                        putExtra("player1Name", player1Name)
//                        putExtra("player2Name", player2Name) // إرسال اسم اللاعب الثاني
//                    }
//
//                    "لعب مع كمبيوتر" -> Intent(this, AiPlayerActivity::class.java).apply {
//                        putExtra("player1Name", player1Name)
//                    }
//
//                    else -> null
//                }
//
//                if (intent != null) {
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this, "خيار غير معروف", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//
//    }

}
    }
