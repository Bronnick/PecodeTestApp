package com.example.pecodetestapplication


import android.app.NotificationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.pecodetestapplication.databinding.ActivityMainBinding
import com.example.pecodetestapplication.fragments.ViewPagerAdapter
import com.example.pecodetestapplication.view_models.PagerViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

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

        val viewModel : PagerViewModel by viewModels {PagerViewModel.Factory}

        viewModel.numberOfPages.observe(this) {
            binding?.buttonMinus?.visibility = if(it > 1)  View.VISIBLE else View.INVISIBLE
            adapter?.notifyDataSetChanged()
        }

        adapter = ViewPagerAdapter(
            fragmentActivity = this,
            viewModel = viewModel
        )
        binding?.viewPager?.adapter = adapter
        binding?.viewPager?.registerOnPageChangeCallback (
            object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding?.currentPageTextView?.text = (position + 1).toString()
                }
            }
        )

        setButtonClickListeners(viewModel)
        setInitialPage(viewModel)
    }

    private fun setButtonClickListeners(viewModel: PagerViewModel) {
        binding?.buttonPlus?.setOnClickListener {
            viewModel.addPage()
        }
        binding?.buttonMinus?.setOnClickListener {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            viewModel.deletePage(notificationManager)
        }
    }

    private fun setInitialPage(viewModel: PagerViewModel) {
        lifecycleScope.launch {
            viewModel.setInitialNumberOfPagesJob?.join()
            val currentPage = intent.getIntExtra("current_page", 0)
            binding?.viewPager?.currentItem = currentPage - 1
        }
    }


}