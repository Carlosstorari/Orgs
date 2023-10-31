package com.chscorp.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chscorp.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert
    fun salva(vararg produto: Produto)
}