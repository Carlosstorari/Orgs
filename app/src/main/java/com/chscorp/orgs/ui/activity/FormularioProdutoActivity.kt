package com.chscorp.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.chscorp.orgs.database.AppDatabase
import com.chscorp.orgs.databinding.ActivityFormularioProdutoBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem
import com.chscorp.orgs.model.Produto
import com.chscorp.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()
        binding.activityProdutoFormularioImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->/**---------------------------------*/
                    url = imagem
                    binding.activityProdutoFormularioImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configuraBotaoSalvar() {
        binding.activityFormularioProdutoBtnSalvar.setOnClickListener {
            val db = AppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            val produto = criaProduto()
            produtoDao.salva(produto)
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
            valor = valor,
            imagem = url
        )
        return produto
    }
}