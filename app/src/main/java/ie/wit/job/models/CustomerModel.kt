package ie.wit.job.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomerModel(var idCustomer: Long = 0,
                    var firstName: String = "",
                    var lastName: String = "",
                    var mobile: String ="",
                    var email : String ="") : Parcelable