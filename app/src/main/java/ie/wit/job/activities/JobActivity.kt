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
    var edit = false
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Job activity has started.......!")

        if (intent.hasExtra("job_edit")) {
            edit = true
            job = intent.extras?.getParcelable("job_edit")!!
            binding.jobTitle.setText(job.title)
            binding.description.setText(job.description)
            binding.btnAdd.setText(R.string.save_job)
        }

        binding.btnAdd.setOnClickListener() {
            job.title = binding.jobTitle.text.toString()
            job.description = binding.description.text.toString()
            if (job.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_job_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.jobs.update(job.copy())
                } else {
                    app.jobs.create(job.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
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