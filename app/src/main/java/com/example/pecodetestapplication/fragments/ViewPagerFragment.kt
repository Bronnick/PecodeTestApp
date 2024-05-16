package com.example.pecodetestapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pecodetestapplication.R
import com.example.pecodetestapplication.databinding.ViewPagerFragmentBinding

class ViewPagerFragment : Fragment(R.layout.view_pager_fragment) {

    private var binding: ViewPagerFragmentBinding? = null
    private var pageNumber: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt("page_number") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewPagerFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {

        fun newInstance(pageNumber: Int): ViewPagerFragment {
            val args = Bundle().apply {
                putInt("page_number", pageNumber)
            }
            return ViewPagerFragment().apply {
                arguments = args
            }
        }
    }
}