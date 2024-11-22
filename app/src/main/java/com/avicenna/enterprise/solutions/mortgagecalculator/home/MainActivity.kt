package com.avicenna.enterprise.solutions.mortgagecalculator.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.avicenna.enterprise.solutions.mortgagecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isAllFieldCheck = false

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.btnCalculate.setOnClickListener { calculateRepayment() }
    }

    private fun setupObservers() {
        mainViewModel.monthlyRepayment.observe(this) {
            binding.tvMonthlyPayment.text = String.format("%.2f", it)
        }
        mainViewModel.totalRepayment.observe(this) {
            binding.tvTotalRepayment.text = String.format("%.2f", it)
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun calculateRepayment() {
        isAllFieldCheck = checkAllFields()

        if (binding.etAmount.text.isNullOrEmpty() &&
            binding.etTerm.text.isNullOrEmpty() &&
            binding.etInterest.text.isNullOrEmpty()
            ) {
            setupError()

        } else if (isAllFieldCheck) {
            val amount = binding.etAmount.text.toString().toInt()
            val years = binding.etTerm.text.toString().toInt()
            val interest = binding.etInterest.text.toString().toDouble()

            mainViewModel.calculateRepayment(amount, years, interest)
        }
    }

    private fun setupError() {
        binding.apply {
            etAmount.error = "Please enter the amount"
            etTerm.error = "Please enter the term"
            etInterest.error = "Please enter the interest"
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.etAmount.text.isNullOrEmpty()) {
            binding.etAmount.error = "Please enter the amount"
            return false
        }
        if (binding.etTerm.text.isNullOrEmpty()) {
            binding.etTerm.error = "Please enter the Term"
            return false
        }
        if (binding.etInterest.text.isNullOrEmpty()) {
            binding.etInterest.error = "Please enter the Interest"
            return false
        }
        return true
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}