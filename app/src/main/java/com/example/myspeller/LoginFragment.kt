package com.example.myspeller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.my1.LoginFragmentDirections
import com.example.my1.R
import com.example.my1.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editname2.visibility = View.GONE

        binding.gameModeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.singlePlayerRadioButton -> {
                    binding.editname1.hint = "أدخل اسمك"
                    binding.editname2.visibility = View.GONE
                }
                R.id.multiPlayerRadioButton -> {
                    binding.editname1.hint = "اسم اللاعب الأول"
                    binding.editname2.visibility = View.VISIBLE
                }
                R.id.aiPlayerRadioButton -> {
                    binding.editname1.hint = "أدخل اسمك"
                    binding.editname2.visibility = View.GONE
                }
            }
        }

        binding.buttongo.setOnClickListener {
            val selectedModeId = binding.gameModeGroup.checkedRadioButtonId

            if (selectedModeId == -1) {
                Toast.makeText(context, "يرجى اختيار وضع اللعب", Toast.LENGTH_SHORT).show()
            } else {
                var player1Name = binding.editname1.text.toString().ifEmpty { "Player 1" }
                var player2Name = binding.editname2.text.toString().ifEmpty { "Player 2" }

                when (selectedModeId) {
                    R.id.singlePlayerRadioButton -> {
                        val action1 = LoginFragmentDirections.actionLoginFragmentToSinglPlayFragment(player1Name)
                        findNavController().navigate(action1)
                    }
                    R.id.aiPlayerRadioButton -> {
                        val action2 = LoginFragmentDirections.actionLoginFragmentToAiPlayerFragment(player1Name)
                        findNavController().navigate(action2)
                    }
                    R.id.multiPlayerRadioButton -> {
                        val action3 = LoginFragmentDirections.actionLoginFragmentToMultiPlayersFragment(player1Name, player2Name)
                        findNavController().navigate(action3)
                    }
                }
            }
        }
    }
}






