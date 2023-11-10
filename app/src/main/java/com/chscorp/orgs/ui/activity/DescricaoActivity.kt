package com.chscorp.orgs.ui.activity

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

private const val TAG = "DetalhesProduto"
private lateinit var produtoDesc: Produto
class DescricaoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDescricaoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
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
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        when(item.itemId){
            R.id.menu_detalhes_produto_remover -> {
                produtoDao.remove(produtoDesc)
            }
            R.id.menu_detalhes_produto_editar -> {
                Log.i(TAG, "onOptionsItemSelected: editar")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}