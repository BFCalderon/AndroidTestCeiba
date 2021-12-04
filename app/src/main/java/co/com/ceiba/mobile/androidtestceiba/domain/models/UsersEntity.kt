package co.com.ceiba.mobile.androidtestceiba.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Objeto mediante el cual se hace la definición de la tabla que modela el usuario en la BD
 * @param userId Id del usuario proveído por el servicio
 * @param userName Nombre del usuario
 * @param userEmail Correo del usuario
 */
@Entity(tableName = UsersEntity.TABLE_NAME)
data class UsersEntity (
  @ColumnInfo(name = USER_ID) @PrimaryKey val userId: Int,
  @ColumnInfo(name = USER_NAME) val userName: String,
  @ColumnInfo(name = USER_EMAIL) val userEmail: String
) {
  companion object {
    const val TABLE_NAME = "USERS"
    const val USER_ID = "ID"
    const val USER_NAME = "NAME"
    const val USER_EMAIL = "EMAIL"
  }
}