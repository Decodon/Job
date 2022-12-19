package ie.wit.job.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.job.databinding.CardCustomerBinding
import ie.wit.job.models.CustomerModel

interface CustomerListener {
    fun onCustomerClick(customer: CustomerModel)
}

class CustomerAdapter constructor(private var customers: List<CustomerModel>,
                                  private val listener: CustomerListener) :
    RecyclerView.Adapter<CustomerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCustomerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val customer = customers[holder.adapterPosition]
        holder.bind(customer, listener)
    }

    override fun getItemCount(): Int = customers.size

    class MainHolder(private val binding : CardCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(customer: CustomerModel, listener: CustomerListener) {
            val fullName = customer.firstName + " " + customer.lastName
            binding.name.text = fullName
            binding.email.text = customer.email
            binding.root.setOnClickListener { listener.onCustomerClick(customer) }
        }
    }
}