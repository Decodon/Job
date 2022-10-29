package ie.wit.job.main

import android.app.Application
import ie.wit.job.models.JobJSONStore
import ie.wit.job.models.JobMemStore
import ie.wit.job.models.JobStore
//import ie.wit.job.models.JobModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val jobs = ArrayList<JobModel>()
    //val jobs = JobMemStore()
    lateinit var jobs : JobStore


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        jobs = JobJSONStore(applicationContext)
        i("Job started")
//        jobs.add(JobModel("One", "About one"))
//        jobs.add(JobModel("Two", "About two"))
//        jobs.add(JobModel("Three", "About three"))
//        jobs.add(JobModel("Four", "About four"))
    }
}