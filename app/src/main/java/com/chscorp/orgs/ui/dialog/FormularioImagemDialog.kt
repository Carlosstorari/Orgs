package com.chscorp.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.chscorp.orgs.databinding.FormularioImagemBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(quandoImagemCarregada: (imagem: String) -> Unit) {
        val binding = FormularioImagemBinding
            .inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}