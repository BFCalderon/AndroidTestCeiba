package co.com.ceiba.mobile.androidtestceiba.presentation.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.com.ceiba.mobile.androidtestceiba.R
import co.com.ceiba.mobile.androidtestceiba.databinding.FragmentPostBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PostFragment: Fragment() {

  private var _binding : FragmentPostBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
  ) : View {

    _binding = FragmentPostBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}