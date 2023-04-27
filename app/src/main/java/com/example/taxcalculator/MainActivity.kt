package com.example.taxcalculator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.slider.Slider
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var billAmountEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var taxRateSlider: Slider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // привязка с view
        billAmountEditText = findViewById(R.id.edit_text)
        taxRateSlider = findViewById(R.id.slider)
        resultTextView = findViewById(R.id.text_view)


        billAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateResultTextView()
            }
            override fun afterTextChanged(s: Editable?) {
                // Not used
            }
        })
        taxRateSlider.addOnChangeListener(object : Slider.OnChangeListener {
            @SuppressLint("RestrictedApi")
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                updateResultTextView()
            }
        })
    }

    private fun updateResultTextView() {
        val billAmount = billAmountEditText.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
        val taxRate = taxRateSlider.value
        val taxAmount = billAmount.multiply(BigDecimal.valueOf(taxRate.toDouble() / 100))
        //val resultText = "Bill value: ${"%.2f".format(billAmount)}$, tip percentage: $taxRate%"
        val resultText = "Tip amount: ${"%.2f".format(taxAmount)}$"
        resultTextView.text = if (billAmountEditText.text.isEmpty()) "" else resultText

    }
}