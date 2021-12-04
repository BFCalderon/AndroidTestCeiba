package co.com.ceiba.mobile.androidtestceiba.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Definición de las relaciones y llaves foraneas
 */
@Entity(tableName = PostsEntity.TABLE_NAME,
  foreignKeys = [ForeignKey(
    entity = UsersEntity::class,
    parentColumns = [UsersEntity.USER_ID],
    childColumns = [PostsEntity.USER_ID],
  )]
)
/**
 * Objeto mediante el cual se hace la definición de la tabla que modela los post en la BD
 * @param postId Identificador del post en la BD proveído por el servicio
 * @param userId Id del usuario que sirve como llave forane para relacionarla con [UsersEntity]
 * @param postTitle Titulo del post
 * @param postBody Cuerpo del post
 */
data class PostsEntity (
  @PrimaryKey val postId: Int,
  @ColumnInfo(name = USER_ID) val userId: Int,
  @ColumnInfo(name = POST_TITLE) val postTitle: String,
  @ColumnInfo(name = POST_BODY) val postBody: String
) {
  companion object {
    const val TABLE_NAME = "POSTS"
    const val USER_ID = "USER_ID"
    const val POST_TITLE = "TITLE"
    const val POST_BODY = "BODY"
  }
}