package com.example.minhassenhas

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minhassenhas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initViews
        binding.apply {
            butaoGerador.setOnClickListener {
                val opcoesEscolhidas = mutableListOf<Char>()
                if (caixaMinuscula.isChecked) {
                    opcoesEscolhidas.addAll('a'..'z')
                }
                if (caixaNumeros.isChecked) {
                    opcoesEscolhidas.addAll('0'..'9')
                }
                if (caixaMaiuscula.isChecked) {
                    opcoesEscolhidas.addAll('A'..'Z')
                }
                if (caixaEspecial.isChecked) {
                    opcoesEscolhidas.addAll("!@#$%^&*()_-[]{}|:;,.<>?".toList())
                }
                if (opcoesEscolhidas.isEmpty()) {
                    // Lidar com scenário onde nenhuma opção fosse selecionada
                    Toast.makeText(this@MainActivity, "Selecione pelo menos uma opção", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val tamanhoSenha = 12 // escolher tamanho da senha
                val senhaAleatoria = buildString {
                    repeat(tamanhoSenha) {
                        val indiceAleatorio = Random.nextInt(0, opcoesEscolhidas.size)
                        append(opcoesEscolhidas[indiceAleatorio])
                    }
                }
                tvSenha.text = senhaAleatoria
            }
            tvSenha.setOnClickListener {
                val areaDeTransferencia = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val recorte = ClipData.newPlainText("TextViewText", tvSenha.text)
                areaDeTransferencia.setPrimaryClip(recorte)
                Toast.makeText(this@MainActivity, "Copiado!" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}