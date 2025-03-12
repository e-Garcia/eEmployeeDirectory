package com.egarcia.employee.directory.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.egarcia.blockemployeedirectory.databinding.FragmentEmployeeListBinding
import com.egarcia.employee.directory.presentation.viewModels.EmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * [EmployeeListFragment] is a Fragment that displays a list of employees.
 *
 * This fragment is annotated with `@AndroidEntryPoint`, which means it
 * can receive dependencies via constructor injection.
 *
 * The fragment uses a [EmployeeListViewModel] to fetch and manage the list of employees.
 * It also uses a [EmployeesAdapter] to display the employees in a RecyclerView.
 * @see AndroidEntryPoint
 * @see EmployeeListViewModel
 * @see EmployeesAdapter
 **/
@AndroidEntryPoint
class EmployeeListFragment : Fragment() {
    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployeeListViewModel by viewModels()
    private val adapter = EmployeesAdapter()

    //region Lifecycle Methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEmployees()

        setUp()
        setObservers()
        handleInsets()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    private fun setUp() {
        with(binding) {
            recycler.adapter = adapter
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.employees.collectLatest {
                adapter.submitList(it)
            }
            viewModel.employeesError.collectLatest {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Apply padding to the top of the RecyclerView to avoid the status bar
            binding.recycler.setPadding(
                binding.recycler.paddingLeft,
                insets.top,
                binding.recycler.paddingRight,
                insets.bottom
            )

            // Return the insets, so they are dispatched to the children
            WindowInsetsCompat.CONSUMED
        }
    }

}