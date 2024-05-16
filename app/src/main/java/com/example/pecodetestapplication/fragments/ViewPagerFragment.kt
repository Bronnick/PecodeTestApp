package com.example.pecodetestapplication.fragments

import android.app.Notification
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.notificationButton?.setOnClickListener {
            val notification = Notification.Builder(context, "channel1")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Chat heads active")
                .setContentText("You create notification $pageNumber")
                .build()
            val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(pageNumber, notification)

            Log.d("myLogs", "Notification $pageNumber created")
        }
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