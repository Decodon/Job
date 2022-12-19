package ie.wit.job.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class CustomerModel(var firstName: String = "",
                    var lastName: String = "",
                    var mobile: String ="",
                    var email : String ="")
