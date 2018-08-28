package com.wonka.staff.ui.workers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonka.staff.R
import com.wonka.staff.domain.model.Worker
import com.wonka.staff.ui.base.ImageLoader
import kotlinx.android.synthetic.main.item_worker.view.*

class WorkersAdapter(private val imageLoader: ImageLoader,
                     private val listener: Listener) : RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {

    interface Listener {
        fun onClickWorker(id: Int)
    }

    private var mOriginalDataSet: MutableList<Worker> = ArrayList()
    private var mDataSet: MutableList<Worker> = ArrayList()

    fun appendAll(items: List<Worker>) {
        val initPos = mDataSet.size
        mDataSet.addAll(items)
        mOriginalDataSet = mDataSet.toMutableList()
        notifyItemRangeInserted(initPos, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_worker, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val worker = mDataSet[position]
        imageLoader.loadImage(worker.image, viewHolder.image)
        viewHolder.name.text = "${worker.firstName} ${worker.lastName}"
        viewHolder.gender.text = worker.gender
        viewHolder.profession.text = worker.profession
        viewHolder.itemView.setOnClickListener { listener.onClickWorker(worker.id) }
    }

    override fun getItemCount(): Int = mDataSet.size

    fun applyFilters(filters: FiltersState) {
        // Copy the original set into read mode set
        mDataSet = mOriginalDataSet.toMutableList()

        // If there no active filter it will show original data
        if (filters.areActive()) {
            // If filter of key is not active remove item from visible set
            mOriginalDataSet.forEach {
                if (filters.entries[it.profession] == false) {
                    mDataSet.remove(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.iv_worker_image
        val name = view.tv_worker_name
        val gender = view.tv_worker_gender
        val profession = view.tv_worker_profession

    }

    /**
     * Contains a set of filter values with its current checked/unchecked state.
     */
    data class FiltersState(val entries: Map<String, Boolean>) {

        /**
         * Returns true if there are any entry checked as true.
         */
        fun areActive(): Boolean = entries.values.contains(true)
    }
}
