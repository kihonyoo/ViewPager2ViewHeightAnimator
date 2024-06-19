package com.example.tablayoutscrolling

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayoutscrolling.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager2ViewHeightAnimator = ViewPager2ViewHeightAnimator()
        viewPager2ViewHeightAnimator.viewPager2 = binding.viewpager

        val title = listOf(
            "單程",
            "來回",
            "多航段",
        )

        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3
            override fun createFragment(position: Int): Fragment = when (position) {
                2 -> LongFragment()
                else -> ShortFragment()
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = title[position]
        }.attach()

    }
}

class ShortFragment : Fragment(R.layout.fragment_short)

class LongFragment : Fragment(R.layout.fragment_long)


