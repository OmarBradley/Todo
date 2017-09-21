package lee.todo.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_holder_task_item.view.*
import lee.todo.R
import lee.todo.entity.Task
import org.jetbrains.anko.onClick
import java.util.*


class TaskAdapter : RecyclerView.Adapter<TaskItemViewHolder>() {

    val datas: MutableList<TaskItem> = mutableListOf()

    val onClickDeleteButton: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_task_item, parent, false)
        return TaskItemViewHolder(view, onClickDeleteButton)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindView(datas[position])
    }

    override fun getItemCount(): Int =
            datas.size
}

class TaskItemViewHolder(
        itemView: View,
        var onClickDeleteButton: () -> Unit = {}
) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: TaskItem) {
        itemView.textView_taskName.text = item.name
        itemView.textView_taskCreateStamp.text = item.createTimeStamp.toString()
        itemView.button_deleteTask.onClick { onClickDeleteButton() }
    }

}

data class TaskItem(
        var uuid: String = UUID.randomUUID().toString(),
        var name: String = "",
        var isCompleted: Boolean = false,
        var isDeleted: Boolean = false,
        var createTimeStamp: Long = System.currentTimeMillis()
) {
    constructor(task: Task) : this(
            uuid = task.uuid,
            name = task.name,
            isCompleted = task.isCompleted,
            isDeleted = task.isDeleted,
            createTimeStamp = task.createTimeStamp
    )
}



