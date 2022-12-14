package org.sopt.androidpractice.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.androidpractice.databinding.FragmentGalleryBinding
import org.sopt.androidpractice.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    private var _binding:FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binding) { "There is error in SearchFragment" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}