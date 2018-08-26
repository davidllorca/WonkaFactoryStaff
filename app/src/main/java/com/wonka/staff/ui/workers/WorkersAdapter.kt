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

    private var mDataSet: MutableList<Worker> = ArrayList()

    fun appendAll(items: List<Worker>) {
        val initPos = mDataSet.size
        mDataSet.addAll(items)
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
        viewHolder.itemView.setOnClickListener { listener.onClickWorker(worker.id) }
    }

    override fun getItemCount(): Int = mDataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.iv_worker_image
        val name = view.tv_worker_name

    }
}
