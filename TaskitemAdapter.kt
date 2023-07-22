package com.example.simpletodo

/**
 * A bridge that tells the recyclerview how to display the data we give it
 */

class TaskItemAdapter: recyclerView.Adapter<TaskitemAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO
    }

}