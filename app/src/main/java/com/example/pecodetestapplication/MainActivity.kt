package com.example.pecodetestapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pecodetestapplication.databinding.ActivityMainBinding
import com.example.pecodetestapplication.fragments.ViewPagerAdapter
import com.example.pecodetestapplication.view_models.PagerViewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: PagerViewModel by viewModels {PagerViewModel.Factory}

    private var adapter: FragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.numberOfPages.observe(this) {
            adapter?.notifyDataSetChanged()
        }

        adapter = ViewPagerAdapter(
            fragmentActivity = this,
            viewModel = viewModel
        )
        binding?.viewPager?.adapter = adapter
        setButtonClickListeners()
    }

    private fun setButtonClickListeners() {
        binding?.buttonPlus?.setOnClickListener {
            viewModel.addPage()
        }
        binding?.buttonMinus?.setOnClickListener {
            viewModel.deletePage()
        }
    }
}