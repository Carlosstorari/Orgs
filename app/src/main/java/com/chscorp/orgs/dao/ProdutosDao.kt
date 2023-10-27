package com.chscorp.orgs.dao

import com.chscorp.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            ),
            Produto(
                nome = "laranja",
                descricao = "varias laranjas",
                valor = BigDecimal("1029"),
                imagem = "https://www.gov.br/agricultura/pt-br/assuntos/camaras-setoriais-tematicas/imagens/frutas.jpg"
            )
        )
    }
}