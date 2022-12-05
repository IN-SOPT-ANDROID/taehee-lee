package org.sopt.androidpractice.gallery

import android.Manifest
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.fragment.app.Fragment
import coil.load
import org.sopt.androidpractice.databinding.FragmentGalleryBinding
import org.sopt.androidpractice.home.HomeFragment
import java.net.URI

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = requireNotNull(_binding) { "There is error in GalleryFragment" }

//    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
//        binding.imgSample.load(it)
//    }

//    private val launcher2 = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
//        binding.imgSample.load(it)
//    }

    private val launcher3 = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(2)){
        uris ->
        binding.imgSample2.load(uris[0])
        binding.imgSample.load(uris[1])
    }

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
        //manifest의 permission접근
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        binding.btnImage.setOnClickListener{
            launcher3.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}