package co.com.ceiba.mobile.androidtestceiba.data.remote

import co.com.ceiba.mobile.androidtestceiba.common.EndPoints.GET_POST_USER
import co.com.ceiba.mobile.androidtestceiba.common.EndPoints.GET_USERS
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.PostDto
import co.com.ceiba.mobile.androidtestceiba.data.remote.dto.UserDto
import retrofit2.http.GET

/**
 * Interfaz mediante la cual se definen los metodos de consulta al API
 */
interface CeibaApi {

  /**
   * Función que trae la lista de usuarios
   */
  @GET(GET_USERS)
  suspend fun getUsers(): List<UserDto>

  /**
   * Funcion mediante la cual se consulta a traves del Api de Mercado libre los paises a los cuales tiene alcanse
   */
  @GET(GET_POST_USER)
  suspend fun getAllPost(): List<PostDto>

}