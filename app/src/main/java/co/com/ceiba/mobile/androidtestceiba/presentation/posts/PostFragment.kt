package co.com.ceiba.mobile.androidtestceiba.presentation.posts

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.androidtestceiba.common.GenericAdapterRecyclerView
import co.com.ceiba.mobile.androidtestceiba.databinding.FragmentPostBinding
import co.com.ceiba.mobile.androidtestceiba.databinding.PostListItemBinding
import co.com.ceiba.mobile.androidtestceiba.domain.models.Post
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragmento que muestra la descripcion de un usuario y los posts asociados
 */
@AndroidEntryPoint
class PostFragment: Fragment() {

  private var _binding : FragmentPostBinding? = null
  private val binding get() = _binding!!
  private val postViewModel: PostsViewModel by viewModels()
  private val args: PostFragmentArgs by navArgs()

  override fun onCreateView(
    inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
  ) : View {
    _binding = FragmentPostBinding.inflate(inflater, container, false)
    postViewModel.getPosts(args.userId)
    return binding.root

  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    chargeUserData()
    initDataObserver()
  }

  /**
   * Función que carga los datos del usuario
   */
  private fun chargeUserData() {
    binding.name.text = args.userName
    binding.phone.text = args.userPhone
    binding.email.text = args.userEmail
  }

  /**
   * Función que inicia el observador de los datos
   */
  private fun initDataObserver() {
    postViewModel.postsState.observe(viewLifecycleOwner, { dataState->
      dataState?.let { data ->
        data.data?.let { userList->
          showUsers(userList)
        }
        if(data.error.isNotEmpty()){
          Toast.makeText(this.context, data.error, Toast.LENGTH_SHORT).show()
        }
      }
    })
  }

  /**
   * Funcion que muestra los post asociados a un usuario en el recycler view
   */
  private fun showUsers(userList: List<Post>) {
    binding.recyclerViewPostsResults.adapter = GenericAdapterRecyclerView(
      dataset = userList,
      itemViewFactory = { PostContainer(this.requireContext()) }
    ) {  itemView, childItem, _ ->
      (itemView as PostContainer).run {
        itemView.binding.title.text = childItem.postTitle
        itemView.binding.body.text = childItem.postBody
      }
    }
    binding.recyclerViewPostsResults.layoutManager = LinearLayoutManager(this.requireContext())
  }

  /**
   * Instancia de clase que sirve como subItem para generar un nuevo item de post
   */
  class PostContainer @JvmOverloads constructor(
    context : Context, attributes : AttributeSet? = null
  ): LinearLayout(context, attributes){
    val binding : PostListItemBinding =
      PostListItemBinding.inflate(LayoutInflater.from(context), this, true)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}