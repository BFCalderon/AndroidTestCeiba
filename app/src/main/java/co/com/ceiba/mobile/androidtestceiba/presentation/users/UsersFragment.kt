package co.com.ceiba.mobile.androidtestceiba.presentation.users

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.androidtestceiba.R
import co.com.ceiba.mobile.androidtestceiba.common.GenericAdapterRecyclerView
import co.com.ceiba.mobile.androidtestceiba.databinding.UserListItemBinding
import co.com.ceiba.mobile.androidtestceiba.databinding.UsersFragmentBinding
import co.com.ceiba.mobile.androidtestceiba.domain.models.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


/**
 * Fragmento encargado de listar y filtrar los usuarios segun
 */
@AndroidEntryPoint
class UsersFragment: Fragment() {

  private val viewModel: UsersViewModel by viewModels()
  private var _binding : UsersFragmentBinding? = null
  private val binding get() = _binding!!
  private var alertDialog: ProgressDialog? = null

  override fun onCreateView(
    inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
  ) : View {
    _binding = UsersFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onAttach(context : Context) {
    super.onAttach(context)
    alertDialog = ProgressDialog.show(this.requireContext(), "",
      resources.getString(R.string.loading), true)
  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initDataObserver()
    setOnTextViewOnDataObserver()
  }

  /**
   * Función que inicia el observador de los datos
   */
  private fun initDataObserver() {
    var snackBar: Snackbar? = null
    viewModel.usersState.observe(viewLifecycleOwner, { dataState->
      dataState?.let { data ->
        data.isLoading.showAlertDialog(data.data?.size ?: 0)
        data.data?.let { userList->
          showUsers(userList)
        }
        if(data.error.isNotEmpty()){
          snackBar = Snackbar.make(
            this@UsersFragment.requireView(), data.error, Snackbar.LENGTH_INDEFINITE
          ).apply {
            setAction(R.string.try_again) { viewModel.getUsers() }
            show()
          }
        } else {
          snackBar?.dismiss()
        }
      }
    })
  }

  /**
   * Funcion que muestra los usuarios en el recycler view
   */
  private fun showUsers(userList: List<User>) {
    if(userList.isEmpty()){
      binding.emptyView.root.visibility = View.VISIBLE
    } else {
      binding.emptyView.root.visibility = View.GONE
    }
    binding.recyclerViewSearchResults.adapter = GenericAdapterRecyclerView(
      dataset = userList,
      itemViewFactory = { UserContainer(this.requireContext()) }
    ) {  itemView, childItem, _ ->
      (itemView as UserContainer).run {
        itemView.binding.name.text = childItem.userName
        itemView.binding.phone.text = childItem.userPhone
        itemView.binding.email.text = childItem.userEmail
        itemView.binding.btnViewPost.setOnClickListener {
          Navigation.findNavController(this@UsersFragment.requireView()).navigate(
            UsersFragmentDirections.actionFirstFragmentToSecondFragment(
              childItem.userId, childItem.userName, childItem.userPhone, childItem.userEmail
            )
          )
        }
      }
    }
    binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this.requireContext())
  }

  /**
   * Funcion que muestra el dialogo de carga
   */
  private fun Boolean.showAlertDialog(usersSize:Int) {
    when (this) {
      true -> alertDialog?.show()
      false -> alertDialog?.cancel()
    }
    if(usersSize>0) alertDialog = null
  }

  /**
   * Instancia de clase que sirve como subItem para generar un nuevo item para la paleta de seleccion
   */
  class UserContainer @JvmOverloads constructor(
    context : Context, attributes : AttributeSet? = null
  ): LinearLayout(context, attributes){
    val binding : UserListItemBinding =
      UserListItemBinding.inflate(LayoutInflater.from(context), this, true)
  }

  /**
   * Funcion que inicializa el observador del editText
   */
  private fun setOnTextViewOnDataObserver() {
    binding.editTextSearch.addTextChangedListener(object : TextWatcher{
      override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {}
      override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
        viewModel.getUsers(p0.toString())
      }
      override fun afterTextChanged(p0 : Editable?) {}
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}