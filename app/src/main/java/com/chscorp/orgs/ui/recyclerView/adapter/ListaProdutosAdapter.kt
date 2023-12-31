package com.chscorp.orgs.ui.recyclerView.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.chscorp.orgs.R
import com.chscorp.orgs.databinding.ProdutoItemBinding
import com.chscorp.orgs.extensions.formataParaMoedaBrasileira
import com.chscorp.orgs.extensions.tentaCarregarImagem
import com.chscorp.orgs.model.Produto

private const val  TAG = "DetalhesProduto"
class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {
        private lateinit var produto: Produto
        init {
            itemView.setOnClickListener{
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }
        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.produtoItemName
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = produto.valor
                .formataParaMoedaBrasileira()
            valor.text = valorEmMoeda

                /**Exibe imagem de erro quando conteudo da imagem for nulo,
                 * OUTRA FORMA DA LIDAR COM O ERRO*/
                val visibilidade = if (produto.imagem != null) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                binding.imageView.visibility = visibilidade
                /***********************************************************/

                binding.imageView.tentaCarregarImagem(produto.imagem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

        holder.itemView.setOnLongClickListener {
            PopupMenu(context, holder.itemView).apply {
                menuInflater.inflate(R.menu.menu_detalhes_produto, this.menu)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_detalhes_produto_editar -> {
                            Log.i(TAG, "onOptionsItemSelected: Editar")
                        }
                        R.id.menu_detalhes_produto_remover -> {
                            Log.i(TAG, "onOptionsItemSelected: Remover")
                        }
                    }
                    true
                }
                show()
            }
            return@setOnLongClickListener true
        }
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
