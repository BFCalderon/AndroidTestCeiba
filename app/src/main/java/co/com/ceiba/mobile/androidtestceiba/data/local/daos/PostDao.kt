package co.com.ceiba.mobile.androidtestceiba.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post

/**
 * Dao que gestiona las consultas a la tabla [Post]
 */
@Dao
interface PostDao {

  /**
   * Función que obtiene todos los post filtrados por el Id del usuario que le corresponde
   * @param userId Id del usuario que se desea consultar
   */
  @Query("SELECT * FROM ${Post.TABLE_NAME} WHERE ${Post.USER_ID} = :userId")
  suspend fun getPostByUserId(userId: Int): List<Post>

  /**
   * Función que inserta la lista de usuarios provenientes del servicio
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertPosts(users: List<Post>)

  /**
   * Función que obtiene la cantidad de posts existen en la tabla
   */
  @Query("SELECT COUNT(*) FROM ${Post.TABLE_NAME}")
  suspend fun getPostsQuantity(): Int
}