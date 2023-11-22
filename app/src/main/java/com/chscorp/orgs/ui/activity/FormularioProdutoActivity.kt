package com.chscorp.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chscorp.orgs.database.AppDatabase
import com.chscorp.orgs.databinding.ActivityFormularioProdutoBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem
import com.chscorp.orgs.model.Produto
import com.chscorp.orgs.ui.dialog.FormularioImagemDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var produtoId = 0L
    private val produtoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()
        binding.activityProdutoFormularioImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    /**---------------------------------*/
                    url = imagem
                    binding.activityProdutoFormularioImagem.tentaCarregarImagem(url)
                }
        }
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProdutoNoBancoDeDados()
    }

    private fun tentaBuscarProdutoNoBancoDeDados() {
        scope.launch {
            produtoDao.buscaPorId(produtoId)?.let {
                withContext(Dispatchers.Main) {
                    preencheCampos(it)
                }
            }
        }

    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produto: Produto) {
        title = "Alterar produto"
        produtoId = produto.id
        url = produto.imagem
        binding.activityProdutoFormularioImagem.tentaCarregarImagem(produto.imagem)
        binding.activityFormularioNome.setText(produto.nome)
        binding.activityFormularioDescricao.setText(produto.descricao)
        binding.activityFormularioValor.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        binding.activityFormularioProdutoBtnSalvar.setOnClickListener {
            val produto = criaProduto()
//            if (produtoId > 0) {
//                produtoDao.altera(produto)
//            } else {
//                produtoDao.salva(produto)
//            }
            scope.launch {
                produtoDao.salva(produto)
                finish()
            }

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
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
        return produto
    }
}