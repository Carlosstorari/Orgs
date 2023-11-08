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
import com.chscorp.orgs.databinding.FragmentDescricaoBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem
import java.text.NumberFormat
import java.util.Locale

private const val  TAG = "DetalhesProduto"
class DescricaoFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentDescricaoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescricaoBinding
            .inflate(inflater, container, false)
            .apply {
                val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                imagemDescricao.tentaCarregarImagem(arguments?.getString("img"))
                textCardValor.text = formatador.format(arguments?.getString("valor")?.toBigDecimal())
                tituloProduto.text = arguments?.getString("nome")
                descricaoProduto.text = arguments?.getString("descricao")
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, lifecycle.currentState)
    }

    companion object {

        @JvmStatic
        fun newInstance(imagProduto: String?,
                        valorProduto: String,
                        nomeProduto: String,
                        descricaoProduto: String
        ) = DescricaoFragment().apply {
            arguments = Bundle().apply {
                putString("img", imagProduto)
                putString("valor", valorProduto)
                putString("nome", nomeProduto)
                putString("descricao", descricaoProduto)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.menu_detalhes_produto_editar -> {
                Log.i(TAG, "onOptionsItemSelected: Editar")
            }
            R.id.menu_detalhes_produto_remover -> {
                Log.i(TAG, "onOptionsItemSelected: Remover")
            }
        }
        return super.onContextItemSelected(menuItem)
    }

}