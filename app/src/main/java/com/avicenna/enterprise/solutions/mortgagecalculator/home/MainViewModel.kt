package com.avicenna.enterprise.solutions.mortgagecalculator.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainViewModel : ViewModel() {

    private var _monthlyRepayment = MutableLiveData<Double>(0.0)
    val monthlyRepayment: LiveData<Double> = _monthlyRepayment

    private var _totalRepayment = MutableLiveData<Double>(0.0)
    val totalRepayment: LiveData<Double> = _totalRepayment


    fun calculateRepayment(amount: Int, years: Int, interest: Double) {
        val months = years * 12
        val monthlyInterest = (interest / 100) / 12

        val monthlyRepay =
            amount * monthlyInterest * (1 + monthlyInterest).pow(months) / ((1 + monthlyInterest).pow(months) - 1)
        _monthlyRepayment.value = monthlyRepay

        _totalRepayment.value =  monthlyRepay * months
    }
}