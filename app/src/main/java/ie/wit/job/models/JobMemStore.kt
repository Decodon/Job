package ie.wit.job.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class JobMemStore : JobStore {

    val jobs = ArrayList<JobModel>()

    override fun findAll(): List<JobModel> {
        return jobs
    }

    override fun create(job: JobModel) {
        job.id = getId()
        jobs.add(job)
        logAll()
    }

    override fun update(job: JobModel) {
        var foundJob: JobModel? = jobs.find { j -> j.id == job.id }
        if (foundJob != null) {
            foundJob.title = job.title
            foundJob.description = job.description
            logAll()
        }
    }

    fun logAll() {
        jobs.forEach{ i("${it}") }
    }
}