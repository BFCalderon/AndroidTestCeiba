package co.com.ceiba.mobile.androidtestceiba.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.com.ceiba.mobile.androidtestceiba.R
import co.com.ceiba.mobile.androidtestceiba.databinding.UsersFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UsersFragment: Fragment() {

  private var _binding : UsersFragmentBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
  ) : View {

    _binding = UsersFragmentBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.content.setOnClickListener {
      findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}