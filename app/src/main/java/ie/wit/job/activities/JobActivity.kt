package ie.wit.job.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.job.R
import ie.wit.job.databinding.ActivityJobBinding
import ie.wit.job.helpers.showImagePicker
import ie.wit.job.main.MainApp
import ie.wit.job.models.JobModel
import timber.log.Timber.i

class JobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobBinding
    var job = JobModel()
    var edit = false
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        registerImagePickerCallback()

        app = application as MainApp
        i("Job activity has started.......!")

        if (intent.hasExtra("job_edit")) {
            edit = true
            job = intent.extras?.getParcelable("job_edit")!!
            binding.jobTitle.setText(job.title)
            binding.description.setText(job.description)
            binding.btnAdd.setText(R.string.save_job)
            Picasso.get()
                .load(job.image)
                .into(binding.jobImage)
            if (job.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_job_image)
            }
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

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            job.image = result.data!!.data!!
                            Picasso.get()
                                .load(job.image)
                                .into(binding.jobImage)
                            binding.chooseImage.setText(R.string.change_job_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}