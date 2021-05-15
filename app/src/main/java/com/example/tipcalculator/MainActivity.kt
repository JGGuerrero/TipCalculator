package com.example.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    /**
     * added lateinit var
     */
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * added
         * binding = ActivityMainBinding.inflate(layoutInflater)
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * replaced R.layout.activity_main with
         * binding.root
         */
        setContentView(binding.root)


        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            return
        }
        val selectedID = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedID) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}