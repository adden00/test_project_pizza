package com.test_test.testprojectpizza.screens.fragments

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.test_test.testprojectpizza.R
import com.test_test.testprojectpizza.data.MenuItem
import com.test_test.testprojectpizza.databinding.FragmentMenuBinding
import com.test_test.testprojectpizza.screens.adapters.MenuAdapter
import com.test_test.testprojectpizza.screens.view_models.MenuViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private val curBtnState = mutableListOf(false, false, false, false)
    private lateinit var adapter: MenuAdapter
    private lateinit var viewModel: MenuViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = MenuViewModel()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRcView()
        observer()
        setButtons()
        viewModel.onCreate()
    }


    private fun setButtons() {
        binding.btnPizza.setBackgroundColor(Color.WHITE)
        binding.btnCombo.setBackgroundColor(Color.WHITE)
        binding.btnDeserts.setBackgroundColor(Color.WHITE)
        binding.btnDrinks.setBackgroundColor(Color.WHITE)

        binding.btnCombo.setOnClickListener {
            setColorByClick(it, 0)
        }

        binding.btnPizza.setOnClickListener {
            setColorByClick(it, 1)
        }

        binding.btnDrinks.setOnClickListener {
            setColorByClick(it, 2)
        }

        binding.btnDeserts.setOnClickListener {
            setColorByClick(it, 3)
        }

        binding.imageButtonAds1.setOnClickListener {
            Toast.makeText(binding.root.context, "ads clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonAds2.setOnClickListener {
            Toast.makeText(binding.root.context, "ads clicked!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun initRcView() {
        adapter = MenuAdapter()
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(activity)

    }

    private fun observer() {
        viewModel.menuItemModel.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)

        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                else ->
                    binding.progressBar.visibility = View.GONE

            }

        }
    }


    private fun setColorByClick(it: View, id: Int) {
        if (!curBtnState[id]) {
            it.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.secondary))

            curBtnState[id] = !curBtnState[id]
        } else {
            it.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
            curBtnState[id] = !curBtnState[id]
        }
    }




    companion object {

        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}