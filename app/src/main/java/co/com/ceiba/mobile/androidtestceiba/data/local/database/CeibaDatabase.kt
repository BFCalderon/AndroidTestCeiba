package co.com.ceiba.mobile.androidtestceiba.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.PostDao
import co.com.ceiba.mobile.androidtestceiba.data.local.daos.UserDao
import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity

/**
 * Se crea la estructura de la base de datos, se asocian las tablas y se integran los daos
 */
@Database(
  entities = [UsersEntity::class, PostsEntity::class], version = 1
)
abstract class CeibaDatabase: RoomDatabase() {

  abstract val userDao : UserDao
  abstract val postDaoDao : PostDao

  companion object {
    private const val DATABASE_NAME = "CEIBA_BD"

    @Volatile
    private var INSTANCE : CeibaDatabase? = null

    /**
     * Funcion que crea la instancia singleton de la base de datos
     */
    fun getInstance(context : Application) : CeibaDatabase? {
      INSTANCE ?: synchronized(this) {
        INSTANCE = Room.databaseBuilder(
          context, CeibaDatabase::class.java, DATABASE_NAME
        ).build()
      }
      return INSTANCE
    }
  }
}