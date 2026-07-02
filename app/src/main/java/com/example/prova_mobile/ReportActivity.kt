package com.example.prova_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val tvOperador = findViewById<TextView>(R.id.tvOperador)
        val tvLinha = findViewById<TextView>(R.id.tvLinha)
        val tvPecasReport = findViewById<TextView>(R.id.tvPecasReport)
        val tvTempoTotal = findViewById<TextView>(R.id.tvTempoTotal)
        val tvTaktTime = findViewById<TextView>(R.id.tvTaktTime)
        val btnNovaProducao = findViewById<Button>(R.id.btnNovaProducao)

        val operador = intent.getStringExtra("operador")
        val linha = intent.getStringExtra("linha")
        val pecas = intent.getIntExtra("pecas", 0)
        val tempoTotal = intent.getLongExtra("tempoTotal", 0)
        val taktTime = intent.getDoubleExtra("taktTime", 0.0)

        tvOperador.text = "OPERADOR: " + operador?.uppercase()
        tvLinha.text = "LINHA: " + linha?.uppercase()
        tvPecasReport.text = "Peças produzidas: " + Math.abs(pecas)

        val segundosTotais = tempoTotal / 1000
        val minutos = segundosTotais / 60
        val segundos = segundosTotais % 60
        tvTempoTotal.text = "Tempo total: " + minutos + "min " + segundos + "s"

        val taktFormatado = String.format("%.2f", taktTime)
        tvTaktTime.text = "Tempo médio por peça: " + taktFormatado + " s"

        btnNovaProducao.setOnClickListener {
            finish()
        }
    }
}