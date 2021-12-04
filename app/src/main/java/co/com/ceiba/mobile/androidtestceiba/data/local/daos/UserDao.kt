package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao que gestiona las consultas a la tabla [UsersEntity]
 */
@Dao
interface UserDao {
  /**
   * Función que obtiene todos los usuarios
   */
  @Query("SELECT * FROM ${UsersEntity.TABLE_NAME}")
  fun getAllUsers(): Flow<List<UsersEntity>>

  /**
   * Función que obtiene todos los usuarios
   */
  @Query("SELECT * FROM ${UsersEntity.TABLE_NAME} WHERE ${UsersEntity.USER_NAME} LIKE :filterName")
  fun getFilteredUsers(filterName: String): Flow<List<UsersEntity>>

  /**
   * Función que inserta la lista de usuarios provenientes del servicio
   */
  @Insert
/*(onConflict = OnConflictStrategy.REPLACE)*///TODO BFCalderon: validar si es necesaria esta validacion
  suspend fun insertUsers(users: List<UsersEntity>)

  /**
   * Función que obtiene todos los usuarios
   */
  @Query("SELECT COUNT(*) FROM ${UsersEntity.TABLE_NAME}")//TODO BFCalderon: Validar si se puede reemplazar por otro metodo
  fun tableIsEmpty(): Int



}