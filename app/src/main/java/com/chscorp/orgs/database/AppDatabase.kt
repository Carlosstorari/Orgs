package com.chscorp.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chscorp.orgs.database.converter.Converters
import com.chscorp.orgs.database.dao.ProdutoDao
import com.chscorp.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)//indica classe usada para conversão do Big Decimal
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun instancia(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
        }
    }
}