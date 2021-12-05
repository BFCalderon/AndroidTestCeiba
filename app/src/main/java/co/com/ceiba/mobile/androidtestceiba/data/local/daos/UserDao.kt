package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.androidtestceiba.domain.models.User

/**
 * Dao que gestiona las consultas a la tabla [User]
 */
@Dao
interface UserDao {
  /**
   * Función que obtiene todos los usuarios
   */
  @Query("SELECT * FROM ${User.TABLE_NAME}")
  suspend fun getAllUsers(): List<User>

  /**
   * Función que obtiene todos los usuarios
   */
  @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.USER_NAME} LIKE '%' || :filterName || '%' ")
  suspend fun getFilteredUsers(filterName: String): List<User>

  /**
   * Función que inserta la lista de usuarios provenientes del servicio
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertUsers(users: List<User>)

  /**
   * Función que obtiene cuantos usuarios existen en la tabla
   */
  @Query("SELECT COUNT(*) FROM ${User.TABLE_NAME}")
  suspend fun tableIsEmpty(): Int

}