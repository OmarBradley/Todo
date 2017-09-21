package lee.todo.presentation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import lee.todo.R
import lee.todo.base.BaseActivity
import lee.todo.presentation.view.TaskAdapter
import org.jetbrains.anko.onClick

class MainActivity : BaseActivity() {

    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    val adapter: TaskAdapter by lazy {
        TaskAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        button_makeTask.onClick {
            viewModel.postTask(editText_taskName.text.toString())
        }

        viewModel.getTaskItemStream().subscribe({
            adapter.datas.add(it)
        }, {
            Toast.makeText(this, "예기치 못한 오류", Toast.LENGTH_SHORT)
        })


    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
