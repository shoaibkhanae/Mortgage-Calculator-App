package com.avicenna.enterprise.solutions.mortgagecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.avicenna.enterprise.solutions.mortgagecalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.btnCalculate.setOnClickListener { calculateRepayment() }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun calculateRepayment() {

        if (binding.etAmount.text.isNullOrEmpty() &&
            binding.etTerm.text.isNullOrEmpty() &&
            binding.etInterest.text.isNullOrEmpty()
            ) {
            binding.apply {
                etAmount.error = "Please enter amount"
                etTerm.error = "Please enter the term"
                etInterest.error = "Please enter the interest rate"
            }
        } else {
            binding.apply {
                etAmount.error = null
                etTerm.error = null
                etInterest.error = null
            }

            val amount = binding.etAmount.text.toString().toInt()
            val years = binding.etTerm.text.toString().toInt()
            val interest = binding.etInterest.text.toString().toDouble()

            val months = years * 12
            val monthlyInterest = (interest / 100) / 12

            // formula to monthly repayment
            val monthlyRepayment =
                amount * monthlyInterest * (1 + monthlyInterest).pow(months) / ((1 + monthlyInterest).pow(months) - 1)

            binding.tvMonthlyPayment.text = String.format("%.2f", monthlyRepayment)

            // formula to total repayment
            val totalRepayment = monthlyRepayment * months

            binding.tvTotalRepayment.text = String.format("%.2f", totalRepayment)
        }
    }
}