
package com.chscorp.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chscorp.orgs.R
import com.chscorp.orgs.dao.ProdutosDao
import com.chscorp.orgs.databinding.ActivityListaProdutosBinding
import com.chscorp.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity: AppCompatActivity() {

    private val binding by lazy { ActivityListaProdutosBinding.inflate(layoutInflater) }
    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(
        context = this,
        produtos = dao.buscaTodos()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        binding.activityListaProdutoFab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraRecyclerView() {
        binding.activityListaProdutoRecyclerview.apply {
            adapter = this@ListaProdutosActivity.adapter
            layoutManager = LinearLayoutManager(this@ListaProdutosActivity)
        }
    }

}