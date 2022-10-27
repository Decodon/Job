package ie.wit.job.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class JobModel(var id: Long = 0,
                    var title: String = "",
                    var description: String = "") : Parcelable
