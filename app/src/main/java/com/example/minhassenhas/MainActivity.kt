package com.example.minhassenhas

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minhassenhas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar as views
        binding.apply {
            butaoGerador.setOnClickListener {
                // Criar uma lista de caracteres com todas as opções selecionadas
                val opcoesEscolhidas = mutableListOf<Char>()
                if (caixaMinuscula.isChecked) opcoesEscolhidas.addAll('a'..'z')
                if (caixaNumeros.isChecked) opcoesEscolhidas.addAll('0'..'9')
                if (caixaMaiuscula.isChecked) opcoesEscolhidas.addAll('A'..'Z')
                if (caixaEspecial.isChecked) opcoesEscolhidas.addAll("!@#$%^&*()_-[]{}|:;,.<>?".toList())
                if (opcoesEscolhidas.isEmpty()) {
                    // Exibir mensagem se nenhuma opção for selecionada
                    Toast.makeText(this@MainActivity, "Selecione pelo menos uma opção", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                // Definir tamanho da senha
                val tamanhoSenha = 12
                val senhaAleatoria = buildString {
                    repeat(tamanhoSenha) {
                        val indiceAleatorio = Random.nextInt(0, opcoesEscolhidas.size)
                        append(opcoesEscolhidas[indiceAleatorio])
                    }
                }
                tvSenha.text = senhaAleatoria
            }

            tvSenha.setOnClickListener {
                // Copiar senha para a área de transferência
                val areaDeTransferencia = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val recorte = ClipData.newPlainText("TextViewText", tvSenha.text)
                areaDeTransferencia.setPrimaryClip(recorte)
                Toast.makeText(this@MainActivity, "Copiado!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
