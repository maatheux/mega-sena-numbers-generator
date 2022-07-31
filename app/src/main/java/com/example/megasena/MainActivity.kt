package com.example.megasena

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

//        btnGenerate.setOnClickListener(buttonClickListener)

//      3 option
        btnGenerate.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }
    }

    private fun numberGenerator(text: String, txtResult: TextView) {

        when {
            text.isEmpty() -> {
                Toast
                    .makeText(this, "Campo vazio! Insira um valor entre 6 e 15.", Toast.LENGTH_LONG)
                    .show()
            }
            text.toInt() !in 6..15 -> {
                Toast
                    .makeText(this, "Valor inválido! Insira um valor entre 6 e 15.", Toast.LENGTH_LONG)
                    .show()
            }
            else -> {
                val listOfNumbers = mutableSetOf<Int>()
                val randomNumber = Random()

                while (listOfNumbers.size <= text.toInt() && listOfNumbers.size <= 15) {
                    val number = randomNumber.nextInt(60) // um numero de 0 a 59
                    listOfNumbers.add(number + 1)
                }

                txtResult.text = listOfNumbers.joinToString(" - ")

            }
        }
    }

//  2 option - setOnClickListener
//    val buttonClickListener = View.OnClickListener {
//        Log.i("New Number", editText.text.toString())
//    }

//    val buttonClickListener = object: View.OnClickListener {
//        override fun onClick(p0: View?) {
//            Log.i("New Number", editText.text.toString())
//        }
//    }

//    1 option - xml
//    fun buttonClicked(view: View) {
//        val newNumber = editText.text
//        Log.i("New Number", newNumber.toString())
//        txtResult.text = newNumber
//    }
}