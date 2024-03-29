package co.com.ceiba.mobile.androidtestceiba.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.androidtestceiba.common.Resource
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import co.com.ceiba.mobile.androidtestceiba.domain.use_case.get_posts.GetPostsUseCase
import co.com.ceiba.mobile.androidtestceiba.common.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Viewmodel que se encarga de los datos para los posts
 * @param postsUseCase caso de uso de los posts
 */
@HiltViewModel
class PostsViewModel @Inject constructor(
  private val postsUseCase : GetPostsUseCase
): ViewModel() {

  private val _postsState = MutableLiveData<DataState<List<Post>>>(DataState())
  val postsState: LiveData<DataState<List<Post>>> = _postsState

  /**
   * Funcion que obtiene la lista de usuarios
   */
  fun getPosts(userId: Int) {
    postsUseCase(userId).onEach { resource ->
      when (resource) {
        is Resource.Success -> _postsState.value = DataState(data = resource.data)
        is Resource.Loading -> _postsState.value = DataState(isLoading = true)
        is Resource.Error -> _postsState.value = DataState(error = resource.message ?: "ERROR INESPERADO")
      }
    }.launchIn(viewModelScope)
  }
}