package com.chscorp.orgs.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.chscorp.orgs.R
import com.chscorp.orgs.database.AppDatabase
import com.chscorp.orgs.databinding.FragmentDescricaoBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem
import com.chscorp.orgs.model.Produto
import com.chscorp.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import java.text.NumberFormat
import java.util.Locale

private const val TAG = "DetalhesProduto"
private lateinit var produtoDesc: Produto

class DescricaoFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentDescricaoBinding
    private val adapter = ListaProdutosAdapter(context = requireContext())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescricaoBinding
            .inflate(inflater, container, false)

            .apply {
                carregaInformacoes()
            }
        return binding.root
    }

    private fun FragmentDescricaoBinding.carregaInformacoes() {
        val parcelable = arguments?.getParcelable<Produto>(PARCELABLE_STRING)
        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        imagemDescricao.tentaCarregarImagem(parcelable?.imagem)
        textCardValor.text = formatador.format(parcelable?.valor.toString().toBigDecimal())
        tituloProduto.text = parcelable?.nome
        descricaoProduto.text = parcelable?.descricao
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, lifecycle.currentState)
    }

    companion object {

        const val PARCELABLE_STRING = "produto"

        @JvmStatic
        fun newInstance(
            produto: Produto
        ): Fragment {
            produtoDesc = produto
            return DescricaoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARCELABLE_STRING, produto)
                }
            }
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (::produtoDesc.isInitialized){
            val db = AppDatabase.instancia(requireContext())
            val produtoDao = db.produtoDao()
            when (menuItem.itemId) {
                R.id.menu_detalhes_produto_editar -> {
                }

                R.id.menu_detalhes_produto_remover -> {
                    produtoDao.remove(produtoDesc)

                }
            }
        }
        return super.onContextItemSelected(menuItem)
    }

}