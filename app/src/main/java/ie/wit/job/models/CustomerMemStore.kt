package ie.wit.job.models

import timber.log.Timber.i

var lastIdCustomer = 0L

internal fun getIdCustomer(): Long {
    return lastIdCustomer++
}

class CustomerMemStore : CustomerStore {

    val customers = ArrayList<CustomerModel>()

    override fun findAll(): List<CustomerModel> {
        return customers
    }

    override fun create(customer: CustomerModel) {
        customer.idCustomer = getIdCustomer()
        customers.add(customer)
        logAll()
    }

    fun logAll() {
        customers.forEach { i("${it}") }
    }

    override fun update(customer: CustomerModel) {
        var foundCustomer: CustomerModel? =
            customers.find { p -> p.idCustomer == customer.idCustomer }
        if (foundCustomer != null) {
            foundCustomer.firstName = customer.firstName
            foundCustomer.lastName = customer.lastName
            foundCustomer.mobile = customer.mobile
            foundCustomer.email = customer.email
            logAll()
        }
    }
}