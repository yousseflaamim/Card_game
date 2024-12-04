package com.example.my1.vewnodell

import androidx.lifecycle.ViewModel
import com.example.my1.data.CardData

class GameViewModel : ViewModel() {
    var player1Name: String? = null
    var player2Name: String? = null
    var pointsPlayer1 = 0
    var pointsPlayer2 = 0
    var pointsComputer = 0
    var currentPlayer = 1  // 1 for Player 1, 2 for Player 2
    var lastCardNumber: Int? = null
    var currentCardResource: Int? = null
    val chosenCards = mutableListOf<CardData>()
    var compytorPlay : String ="compytorPlay"

}

