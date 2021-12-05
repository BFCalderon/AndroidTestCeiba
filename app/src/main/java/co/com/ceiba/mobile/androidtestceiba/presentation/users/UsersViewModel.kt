package co.com.ceiba.mobile.androidtestceiba.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.domain.models.UsersEntity
import co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_users.GetUsersUseCase
import co.com.ceiba.mobile.androidtestceiba.common.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * ViewModel para la aduisición de datos de los usuarios
 * @param usersUseCase caso de uso de los usuarios
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
  private val usersUseCase : GetUsersUseCase
): ViewModel(){

  private val _usersState = MutableLiveData<DataState<List<UsersEntity>>>(DataState())
  val usersState: LiveData<DataState<List<UsersEntity>>> = _usersState

  init {
    getPosts()
  }

  /**
   * Funcion que obtiene la lista de usuarios
   * @param userName valor para filtrar los usuarios, si es nulo los trae todos
   */
  fun getPosts(userName: String? = null) {
    usersUseCase(userName).onEach { resource ->
      when (resource) {
        is Resource.Success -> _usersState.value = DataState(posts = resource.data)
        is Resource.Loading -> _usersState.value = DataState(isLoading = true)
        is Resource.Error -> _usersState.value = DataState(error = resource.message ?: "ERROR INESPERADO")
      }
    }.launchIn(viewModelScope)
  }
}