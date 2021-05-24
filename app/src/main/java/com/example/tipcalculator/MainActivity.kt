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
    private lateinit var binding: ActivityMainBinding

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

        /** Sets onClick event on the calculate button, calls calculateTip Function */
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    /** calculates the tip */
    private fun calculateTip(){
        /** Grabs the text in the costOfService editable field and assigns it to cost as a doubleOrNull */
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        /** if there is nothing in the costOfService field, the tip will display $0.0 and a message will appear prompting the user to input a number */
        if (cost == null){
            displayTip(0.0)
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            return
        }
        /** Checks which radioGroup button is checked and assigns percent amount to tipPercentage accordingly */
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        /** Calculates the tip */
        var tip = cost * tipPercentage

        /** Checks if the roundUpSwitch is checked and will apply ceil() to tip if it is checked */
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        /** Displays the tip */
        displayTip(tip)

    }

    /** Function to display the tip in the TextView
     * @param tip the tip amount to be formatted and displayed
     */
    private fun displayTip(tip : Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}