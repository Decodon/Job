package ie.wit.job.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.wit.job.R
import ie.wit.job.databinding.ActivityCustomerBinding
import ie.wit.job.main.MainApp
import ie.wit.job.models.CustomerModel
import timber.log.Timber.i

class CustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerBinding
    var customer = CustomerModel()
    //val customers = ArrayList<CustomerModel>()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        binding.btnAddContact.setOnClickListener() {
            customer.firstName = binding.firstName.text.toString()
            customer.lastName = binding.lastName.text.toString()
            customer.mobile = binding.mobileNumber.text.toString()
            customer.email = binding.email.text.toString()
            if (customer.firstName.isNotEmpty()) {
                app.customers.add(customer.copy())
                i("add Button Pressed: ${customer.firstName}")
                for (i in app.customers.indices)
                { i("Customer[$i]:${this.app.customers[i]}") }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, "Please Enter a name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_customer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel_customer -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}