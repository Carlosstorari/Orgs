package com.chscorp.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.chscorp.orgs.R
import com.chscorp.orgs.dao.ProdutosDao
import com.chscorp.orgs.databinding.ActivityFormularioProdutoBinding
import com.chscorp.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
        binding.activityProdutoFormularioImagem.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.formulario_imagem)
                .setPositiveButton("Confirmar") {_, _ ->

                }
                .setNegativeButton("Cancelar") {_, _ ->

                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        setContentView(binding.root)
        val dao = ProdutosDao()
        binding.activityFormularioProdutoBtnSalvar.setOnClickListener {
            val produto = criaProduto()
            dao.adiciona(produto)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val nome = binding.activityFormularioNome.text.toString()
        val descricao = binding.activityFormularioDescricao.text.toString()
        val valorEmTexto = binding.activityFormularioValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        val produto = Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
        return produto
    }
}