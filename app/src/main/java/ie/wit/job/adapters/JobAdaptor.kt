package ie.wit.job.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.job.databinding.CardJobBinding
import ie.wit.job.models.JobModel

class JobAdapter constructor(private var jobs: List<JobModel>) :
    RecyclerView.Adapter<JobAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardJobBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val job = jobs[holder.adapterPosition]
        holder.bind(job)
    }

    override fun getItemCount(): Int = jobs.size

    class MainHolder(private val binding : CardJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobModel) {
            binding.jobTitle.text = job.title
            binding.description.text = job.description
        }
    }
}