package ie.wit.job.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.wit.job.R
import ie.wit.job.databinding.ActivityJobBinding
import ie.wit.job.main.MainApp
import ie.wit.job.models.JobModel
import timber.log.Timber.i

class JobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobBinding
    var job = JobModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Job activity has started.......!")

        binding.btnAdd.setOnClickListener() {
            job.title = binding.jobTitle.text.toString()
            job.description = binding.description.text.toString()
            if (job.title.isNotEmpty()) {
                app.jobs.add(job.copy())
                i("add Button Pressed: $job")
                for (i in app.jobs.indices) {
                    i("Job[$i]:${this.app.jobs[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_job, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}