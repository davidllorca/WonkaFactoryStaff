package com.wonka.staff.ui.workers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonka.staff.R
import com.wonka.staff.ui.base.ImageLoader
import kotlinx.android.synthetic.main.item_worker.view.*

typealias ViewItem = WorkersContract.WorkerViewObject

class WorkersAdapter(private val imageLoader: ImageLoader,
                     private val listener: Listener) : RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {

    interface Listener {
        fun onClickWorker(id: Int)
    }

    /**
     * List of all original data.
     */
    private var mOriginalDataSet: MutableList<ViewItem> = ArrayList()

    /**
     * List of data showed at UI by the adapter.
     */
    private var mDataSet: MutableList<ViewItem> = ArrayList()

    /**
     * True is there is any filtered active search, false otherwise.
     */
    var isFiltered: Boolean = false

    /**
     * Set true at construction time.
     */
    var isInitialized: Boolean = true

    fun appendAll(items: List<ViewItem>) {
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
        imageLoader.loadImage(worker.avatarUrl, viewHolder.image)
        viewHolder.name.text = worker.name
        viewHolder.gender.text =
            viewHolder.itemView.context.getText(
                    if (worker.gender == "F") R.string.label_filter_female
                    else R.string.label_filter_male)
        viewHolder.profession.text = worker.profession
        viewHolder.itemView.setOnClickListener { listener.onClickWorker(worker.id) }
    }

    override fun getItemCount(): Int = mDataSet.size

    fun applyFilters(filters: FiltersState) {
        // Copy the original set into read mode set
        mDataSet = mOriginalDataSet.toMutableList()

        // If there no active filter it will show original data
        if (filters.areAnyActive()) {
            // If filter of key is not active remove item from visible set
            mOriginalDataSet.forEach {
                // If no one key of group is selected it consider full join query
                if (filters.areGenderFilterActive() && filters.genderFilter[it.gender] == false) {
                    mDataSet.remove(it)
                    return@forEach
                }

                if (filters.areProfessionFilterActive() && filters.professionFilter[it.profession] == false) {
                    mDataSet.remove(it)
                    return@forEach
                }
            }
        }
        // Set filtered flag
        isFiltered = filters.areAnyActive()

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
    data class FiltersState(var genderFilter: Map<String, Boolean>,
                            var professionFilter: Map<String, Boolean>) {

        /**
         * Returns true if there are any entry checked as true.
         */
        fun areAnyActive(): Boolean = areGenderFilterActive() || areProfessionFilterActive()

        /**
         * Returns true if there are any entry of checked of Gender options as true, false otherwise.
         */
        fun areGenderFilterActive(): Boolean = genderFilter.values.contains(true)

        /**
         * Returns true if there are any entry checked of Profession options as true, false otherwise.
         */
        fun areProfessionFilterActive(): Boolean = professionFilter.values.contains(true)

    }
}
