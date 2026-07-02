package com.example.prova_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var quantidadePecas = 0
    var tempoInicio: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etOperador = findViewById<EditText>(R.id.etOperador)
        val etLinha = findViewById<EditText>(R.id.etLinha)
        val tvPecas = findViewById<TextView>(R.id.tvPecas)
        val buttonIniciar = findViewById<Button>(R.id.btnIniciar)
        val buttonRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val buttonFinalizar = findViewById<Button>(R.id.btnFinalizar)

        buttonRegistrar.isEnabled = false

        buttonIniciar.setOnClickListener {
            val operador = etOperador.text.toString()
            val linha = etLinha.text.toString()

            if (operador == "" || linha == "") {
                Toast.makeText(this, "Preencha Operador e Linha", Toast.LENGTH_SHORT).show()
            } else {
                tempoInicio = System.currentTimeMillis()
                quantidadePecas = 0
                tvPecas.text = "Peças: 0"

                etOperador.isEnabled = false
                etLinha.isEnabled = false

                buttonIniciar.isEnabled = false
                buttonRegistrar.isEnabled = true
            }
        }

        buttonRegistrar.setOnClickListener {
            quantidadePecas = quantidadePecas + 1
            tvPecas.text = "Peças: " + quantidadePecas
        }

        buttonFinalizar.setOnClickListener {
            val tempoFim = System.currentTimeMillis()
            val tempoTotal = tempoFim - tempoInicio

            val taktTime: Double
            if (quantidadePecas > 0) {
                taktTime = (tempoTotal / 1000.0) / quantidadePecas
            } else {
                taktTime = 0.0
            }

            val operador = etOperador.text.toString()
            val linha = etLinha.text.toString()

            val intent = Intent(this, ReportActivity::class.java)
            intent.putExtra("operador", operador)
            intent.putExtra("linha", linha)
            intent.putExtra("pecas", quantidadePecas)
            intent.putExtra("tempoTotal", tempoTotal)
            intent.putExtra("taktTime", taktTime)
            startActivity(intent)

            etOperador.setText("")
            etLinha.setText("")
            etOperador.isEnabled = true
            etLinha.isEnabled = true
            buttonIniciar.isEnabled = true
            buttonRegistrar.isEnabled = false
            quantidadePecas = 0
            tvPecas.text = "Peças: 0"
        }
    }
}