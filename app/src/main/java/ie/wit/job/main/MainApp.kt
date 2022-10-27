package ie.wit.job.main

import android.app.Application
import ie.wit.job.models.JobModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val jobs = ArrayList<JobModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Job started")
//        jobs.add(JobModel("One", "About one"))
//        jobs.add(JobModel("Two", "About two"))
//        jobs.add(JobModel("Three", "About three"))
//        jobs.add(JobModel("Four", "About four"))
    }
}