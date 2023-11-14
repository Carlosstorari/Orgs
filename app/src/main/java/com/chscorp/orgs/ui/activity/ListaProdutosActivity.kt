
package com.chscorp.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chscorp.orgs.database.AppDatabase
import com.chscorp.orgs.databinding.ActivityListaProdutosBinding
import com.chscorp.orgs.ui.recyclerView.adapter.ListaProdutosAdapter

class ListaProdutosActivity: AppCompatActivity() {

    private val binding by lazy { ActivityListaProdutosBinding.inflate(layoutInflater) }
    private val adapter = ListaProdutosAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    private fun configuraFab() {
        binding.activityListaProdutoFab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
            
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutoRecyclerview
            recyclerView.adapter = adapter

        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DescricaoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }

}