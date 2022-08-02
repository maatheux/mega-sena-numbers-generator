package com.example.megasena

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        preferences = getSharedPreferences("db", Context.MODE_PRIVATE)
        preferences = getPreferences(Context.MODE_PRIVATE) ?: return

        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

//        if (preferences.all.isNotEmpty()) {
//            val prevNumList: String? = preferences.getString("result", null)
//            txtResult.text = "Última aposta: $prevNumList"
//        }

        // alternativa

        val result: String? = preferences.getString("result", null)

        result?.let {
            txtResult.text = "Última aposta: $it"
        } // irá analisar se o valor é null ou nao, se nao for entra no escopo

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

                val stringResult = listOfNumbers.joinToString(" - ")

                txtResult.text = stringResult

//                val editor = preferences.edit() // instanciando o editor
//                editor.putString("result", stringResult) // colocando os dados na estrut de dados, usando uma key e value
//                editor.apply()

                // alternativas

//                preferences.edit().apply {
//                    putString("result", stringResult)
//                    apply()
//                }

                with (preferences.edit()) {
                    putString("result", stringResult)
                    apply()
                }

            }
        }
    }

}