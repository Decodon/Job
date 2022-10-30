package ie.wit.job.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.job.R
import ie.wit.job.adapters.JobAdapter
import ie.wit.job.adapters.JobListener
import ie.wit.job.databinding.ActivityJobListBinding
import ie.wit.job.main.MainApp
import ie.wit.job.models.JobModel
import kotlinx.coroutines.Job

class JobListActivity : AppCompatActivity(), JobListener {

    var totalIncome = 0.0

    lateinit var app: MainApp
    private lateinit var binding: ActivityJobListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        //binding.recyclerView.adapter = JobAdapter(app.jobs)
        //binding.recyclerView.adapter = JobAdapter(app.jobs.findAll(),this)
        loadJobs()

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, JobActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onJobClick(job: JobModel) {
        val launcherIntent = Intent(this, JobActivity::class.java)
        launcherIntent.putExtra("job_edit", job)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadJobs() }
    }

    private fun loadJobs(){
        showJobs(app.jobs.findAll())
    }

    fun showJobs(jobs: List<JobModel>){
        binding.recyclerView.adapter = JobAdapter(jobs, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()
        totalIncome = (app.jobs.findAll().sumOf { it.gross }).round(2)
        binding.totalSoFar.text = getString(R.string.totalSoFar,totalIncome)
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }


}
