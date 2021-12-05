package co.com.ceiba.mobile.androidtestceiba.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.PostDao
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.UserDao
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.models.User

/**
 * Se crea la estructura de la base de datos, se asocian las tablas y se integran los daos
 */
@Database(
  entities = [User::class, Post::class], version = 1
)
abstract class CeibaDatabase: RoomDatabase() {

  abstract val userDao : UserDao
  abstract val postDaoDao : PostDao

  companion object {
    const val DATABASE_NAME = "CEIBA_BD"
  }
}