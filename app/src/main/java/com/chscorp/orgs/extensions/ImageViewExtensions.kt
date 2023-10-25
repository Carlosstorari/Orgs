package com.chscorp.orgs.extensions

import android.widget.ImageView
import coil.load
import com.chscorp.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro) /**Exibe imagem de erro quando conteudo da imagem for nulo*/
        error(R.drawable.erro)/**Exibe imagem de erro quando houver algum erro no carregamento da url*/
        placeholder(R.drawable.placeholder)
    }
}