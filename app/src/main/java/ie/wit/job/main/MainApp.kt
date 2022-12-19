package ie.wit.job.main

import android.app.Application
import ie.wit.job.models.*
//import ie.wit.job.models.JobModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val jobs = ArrayList<JobModel>()
    //val jobs = JobMemStore()
    lateinit var jobs : JobStore

    //val customers = ArrayList<CustomerModel>()
    val customers = CustomerMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        jobs = JobJSONStore(applicationContext)
        i("Job started")
//        jobs.add(JobModel("One", "About one"))
//        jobs.add(JobModel("Two", "About two"))
//        jobs.add(JobModel("Three", "About three"))
//        jobs.add(JobModel("Four", "About four"))
//        customers.add(CustomerModel("Declan", "O'Donovan", "123456789", "dec@dec.com"))
//        customers.add(CustomerModel("DA", "O'Donovan", "23456789", "de@dec.com"))
    }
}