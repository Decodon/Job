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

        var edit = false

        if (intent.hasExtra("customer_edit")) {
            edit = true
            customer = intent.extras?.getParcelable("customer_edit")!!
            binding.btnAddContact.setText(R.string.save_customer)
            binding.firstName.setText(customer.firstName)
            binding.lastName.setText(customer.lastName)
            binding.email.setText(customer.email)
            binding.mobileNumber.setText(customer.mobile)
        }

        binding.btnAddContact.setOnClickListener() {
            customer.firstName = binding.firstName.text.toString()
            customer.lastName = binding.lastName.text.toString()
            customer.mobile = binding.mobileNumber.text.toString()
            customer.email = binding.email.text.toString()
            if (customer.firstName.isEmpty()) {
                Snackbar.make(it,R.string.enter_customer_name, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.customers.update(customer.copy())
                } else {
                    app.customers.create(customer.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
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