package ie.wit.job.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.wit.job.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_job_image.toString())
    intentLauncher.launch(chooseFile)
}