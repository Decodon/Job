package ie.wit.job.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.job.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "jobs.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<JobModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class JobJSONStore(private val context: Context) : JobStore {

    var jobs = mutableListOf<JobModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<JobModel> {
        logAll()
        return jobs
    }

    override fun create(job: JobModel) {
        job.id = generateRandomId()
        jobs.add(job)
        serialize()
    }


    override fun update(job: JobModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(jobs, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        jobs = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        jobs.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}