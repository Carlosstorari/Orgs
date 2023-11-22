package com.chscorp.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chscorp.orgs.R
import com.chscorp.orgs.database.AppDatabase
import com.chscorp.orgs.databinding.ActivityDescricaoBinding
import com.chscorp.orgs.extensions.formataParaMoedaBrasileira
import com.chscorp.orgs.extensions.tentaCarregarImagem
import com.chscorp.orgs.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var produtoDesc: Produto? = null

class DescricaoActivity : AppCompatActivity() {
    private var produtoId: Long = 0L
    private val binding by lazy {
        ActivityDescricaoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }
    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        scope.launch {
            produtoDesc = produtoDao.buscaPorId(produtoId)
            withContext(Dispatchers.Main) {
                produtoDesc?.let {
                    preencheCampos(it)
                } ?: finish()
            }
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            imagemDescricao.tentaCarregarImagem(produtoCarregado.imagem)
            tituloProduto.text = produtoCarregado.nome
            descricaoProduto.text = produtoCarregado.descricao
            textCardValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
        produtoDesc = produtoCarregado
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_remover -> {
                scope.launch {
                    produtoDesc?.let { produtoDao.remove(it) }
                    finish()
                }
            }

            R.id.menu_detalhes_produto_editar -> {
                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}