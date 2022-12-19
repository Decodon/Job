package ie.wit.job.models

interface CustomerStore {
    fun findAll(): List<CustomerModel>
    fun create(customer: CustomerModel)
    fun update(customer: CustomerModel)
}