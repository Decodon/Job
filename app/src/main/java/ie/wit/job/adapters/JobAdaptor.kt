package ie.wit.job.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.job.databinding.CardJobBinding
import ie.wit.job.models.JobModel

interface JobListener {
    fun onJobClick(job: JobModel)
}

class JobAdapter constructor(private var jobs: List<JobModel>,
                             private val listener: JobListener) :
    RecyclerView.Adapter<JobAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardJobBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val job = jobs[holder.adapterPosition]
        holder.bind(job, listener)
    }

    override fun getItemCount(): Int = jobs.size

    class MainHolder(private val binding : CardJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobModel, listener: JobListener) {
            binding.jobTitle.text = job.title
            binding.description.text = job.description
            binding.root.setOnClickListener { listener.onJobClick(job) }
        }
    }
}