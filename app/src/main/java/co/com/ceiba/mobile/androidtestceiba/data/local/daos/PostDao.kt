package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao que gestiona las consultas a la tabla [PostsEntity]
 */
@Dao
interface PostDao {

  /**
   * Función que obtiene todos los post filtrados por el Id del usuario que le corresponde
   * @param userId Id del usuario que se desea consultar
   */
  @Query("SELECT * FROM ${PostsEntity.TABLE_NAME} WHERE ${PostsEntity.USER_ID} = :userId")
  fun getPostByUserId(userId: Int): Flow<List<PostsEntity>>

  /**
   * Función que inserta la lista de usuarios provenientes del servicio
   */
  @Insert
/*(onConflict = OnConflictStrategy.REPLACE)*///TODO BFCalderon: validar si es necesaria esta validacion
  suspend fun insertPosts(users: List<PostsEntity>)
}