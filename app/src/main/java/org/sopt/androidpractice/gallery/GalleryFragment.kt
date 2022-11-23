package org.sopt.androidpractice.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.androidpractice.databinding.FragmentGalleryBinding
import org.sopt.androidpractice.home.HomeFragment

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = requireNotNull(_binding) { "There is error in GalleryFragment" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
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