package org.sopt.androidpractice.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.FragmentHomeBinding
import org.sopt.androidpractice.home.adapter.UserAdapter
import org.sopt.androidpractice.remote.ResponseUserDto
import org.sopt.androidpractice.remote.UserServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val userService = UserServicePool.userService
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "There is error in HomeFragment " }

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userService.getUser().enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    userViewModel.userList.addAll(response.body()?.data!!)

                    val adapter = UserAdapter(requireContext())
                    binding.rvRepos.adapter = adapter
                    adapter.setRepoList(userViewModel.userList)
                    Log.e("Response", "onResponse: ${userViewModel.userList}" )

                } else if (response.code() == 404) {
                    Snackbar.make(binding.root, "404 error", Snackbar.LENGTH_LONG)
                        .show()
                } else if (response.code() == 401) {
                    Snackbar.make(binding.root, "401 error", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Snackbar.make(binding.root, "서버 통신 장애가 발생", Snackbar.LENGTH_LONG).show()
            }

        }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}