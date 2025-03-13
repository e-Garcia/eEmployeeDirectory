package com.egarcia.employee.directory.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.egarcia.employee.R
import com.egarcia.employee.databinding.FragmentEmployeeListBinding
import com.egarcia.employee.directory.presentation.viewmodels.EmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint
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
    private lateinit var binding: FragmentEmployeeListBinding
    private lateinit var adapter: EmployeesAdapter
    private val viewModel: EmployeeListViewModel by viewModels()

    //region Lifecycle Methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EmployeesAdapter()
        viewModel.fetchEmployees()
        setUpBindings()
        setObservers()
        handleInsets()
    }

    //endregion

    private fun setUpBindings() {
        with(binding) {
            rvEmployeeList.adapter = adapter

            fabChangeUrl.setOnClickListener {
                viewModel.swapResponse()
            }

            srlEmployeeList.setOnRefreshListener {
                viewModel.fetchEmployees()
            }
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is EmployeeListViewModel.EmployeesListUiState.Loading -> {
                            // Do nothing
                        }

                        is EmployeeListViewModel.EmployeesListUiState.Success -> {
                            if (it.employees.isEmpty()) {
                                showEmptyState()
                            } else {
                                hideEmptyState()
                                adapter.submitList(it.employees)
                            }
                            binding.srlEmployeeList.isRefreshing = false
                        }

                        is EmployeeListViewModel.EmployeesListUiState.Error -> {
                            showErrorState(it)
                            binding.srlEmployeeList.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    /**
     * Edge to edge required configuration to prevent overlapping with the status bar.
     * Required for API 35 and up.
     */
    private fun handleInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Apply padding to the top of the RecyclerView to avoid the status bar
            binding.rvEmployeeList.setPadding(
                binding.rvEmployeeList.paddingLeft,
                insets.top,
                binding.rvEmployeeList.paddingRight,
                insets.bottom
            )

            // Return the insets, so they are dispatched to the children
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun showErrorState(uiErrorState: EmployeeListViewModel.EmployeesListUiState.Error) {
        val errorDescription = getString(R.string.txt_error_employee_list, uiErrorState.exception.message)
        binding.tvEmptyStateDescription.text = errorDescription
        binding.rvEmployeeList.visibility = View.GONE
        binding.tvEmptyStateDescription.visibility = View.VISIBLE
    }

    private fun showEmptyState() {
        binding.tvEmptyStateDescription.text = getString(R.string.txt_empty_employee_list)
        binding.rvEmployeeList.visibility = View.GONE
        binding.tvEmptyStateDescription.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        binding.rvEmployeeList.visibility = View.VISIBLE
        binding.tvEmptyStateDescription.visibility = View.GONE
    }

}