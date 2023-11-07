package com.android.course.room.present.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.course.room.R
import com.android.course.room.databinding.MainFragmentBinding
import com.android.course.room.domain.PreviewFilmInfo

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: MainRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createAdapter()
    }

    private fun createAdapter() {
        adapter = MainRecyclerViewAdapter()
        adapter.itemList = listOf(
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
            PreviewFilmInfo("test1", "test 2"),
        )
        binding.mainRecyclerView.adapter = adapter
    }
}