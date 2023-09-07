package com.example.set10chiajiali

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.set10chiajiali.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.spinnerCitizen.onItemSelectedListener = this
        ArrayAdapter.createFromResource(this, R.array.citizen_selection, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCitizen.adapter = adapter
        }

        binding.buttonSubmit.setOnClickListener{
            val citizen = binding.spinnerCitizen.selectedItem.toString()
            var result = ""

            if(!binding.editName.text.isNullOrEmpty()){
                result += "Hi, " + binding.editName.text.toString() + ". "
            }

            result += "Your fee to make or renew Malaysia passport for 5 years is RM"

            when (citizen) {
                "Senior citizens", "Children below 12", "Haj pilgrims", "Students below 21 and studying abroad"
                    -> result += "100"
                "Disabled"
                    -> result += "0"
                "Others"
                    -> result += "200"
            }

            binding.textResult.text = result + "."
        }

        binding.buttonEmail.setOnClickListener { sendEmail() }

        binding.buttonCall.setOnClickListener { makePhoneCall() }
    }

    private fun makePhoneCall(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:03-44445555")
        startActivity(intent)
    }

    private fun sendEmail(){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:jpn@malaysia.gov.my")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Passport Inquiry")
        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}