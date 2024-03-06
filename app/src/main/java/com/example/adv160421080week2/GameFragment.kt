package com.example.adv160421080week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflates the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        arguments?.let {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        var no1 = 0
        var no2 = 0

        no1 = (0..100).shuffled().last()
        no2 = (0..100).shuffled().last()

        var txtNo1 = view.findViewById<TextView>(R.id.txtNo1)
        var txtNo2 = view.findViewById<TextView>(R.id.txtNo2)
        var txtInputJawaban = view.findViewById<EditText>(R.id.txtInputJawaban).text

        txtNo1.text = no1.toString()
        txtNo2.text = no2.toString()

        var jawaban = no1 + no2

        var point = 0

        btnSubmit.setOnClickListener {
            var pointKirim = point.toString()
            //Tambahan Pengecheckan jika tidak di isi (biar tidak langsung kalah)
            if(txtInputJawaban.toString() == "") {
                Snackbar.make(view, "Jawaban Tidak Boleh Kosong !!", Snackbar.LENGTH_SHORT).show()
            }
            else{
                if (txtInputJawaban.toString() == jawaban.toString()){
                    point++

                    no1 = (0..100).shuffled().last()
                    no2 = (0..100).shuffled().last()

                    txtNo1.text = no1.toString()
                    txtNo2.text = no2.toString()

                    var jawaban1 = no1 + no2
                    jawaban = jawaban1

                    txtInputJawaban.clear()
                }
                else{
                    val action = GameFragmentDirections.actionResultFragment(pointKirim)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}