package ie.wit.job.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.wit.job.databinding.ActivityJobBinding
import ie.wit.job.models.JobModel
import timber.log.Timber
import timber.log.Timber.i

class JobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobBinding
    var job = JobModel()
    val jobs = ArrayList<JobModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Job Activity started...")

        binding.btnAdd.setOnClickListener() {
            job.title = binding.jobTitle.text.toString()
            job.description = binding.description.text.toString()
            if (job.title.isNotEmpty()) {
                jobs.add(job.copy())
                i("add Button Pressed: $job")
                for (i in jobs.indices) {
                    i("Job[$i]:${this.jobs[i]}")
                }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}