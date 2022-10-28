package ie.wit.job.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.job.R
import ie.wit.job.adapters.JobAdapter
import ie.wit.job.adapters.JobListener
import ie.wit.job.databinding.ActivityJobListBinding
import ie.wit.job.main.MainApp
import ie.wit.job.models.JobModel

class JobListActivity : AppCompatActivity(), JobListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityJobListBinding

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
        binding.recyclerView.adapter = JobAdapter(app.jobs.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, JobActivity::class.java)
                startActivityForResult(launcherIntent,1)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onJobClick(job: JobModel) {
        val launcherIntent = Intent(this, JobActivity::class.java)
        launcherIntent.putExtra("job_edit", job)
        startActivityForResult(launcherIntent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
