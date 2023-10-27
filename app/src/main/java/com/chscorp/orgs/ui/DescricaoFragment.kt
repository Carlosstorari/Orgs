package com.chscorp.orgs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chscorp.orgs.databinding.FragmentDescricaoBinding
import com.chscorp.orgs.extensions.tentaCarregarImagem
import java.text.NumberFormat
import java.util.Locale


class DescricaoFragment : Fragment() {
    private lateinit var binding: FragmentDescricaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity)?.supportActionBar?.hide()
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

}