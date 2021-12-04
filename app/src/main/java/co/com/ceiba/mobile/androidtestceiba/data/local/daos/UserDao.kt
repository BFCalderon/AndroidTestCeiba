package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Dao
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
  fun getAllUsers(): Flow<List<UsersEntity>>?

}