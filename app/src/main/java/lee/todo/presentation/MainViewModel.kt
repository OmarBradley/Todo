package lee.todo.presentation

import com.todait.android.application.nextodait.models.realm.config.realmInstance
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import lee.todo.base.BaseViewModel
import lee.todo.data.TaskModelRepository
import lee.todo.domain.PatchTaskDeleteUseCase
import lee.todo.domain.PostTaskUseCase
import lee.todo.presentation.view.TaskItem

class MainViewModel : BaseViewModel() {

    private val postTask: PostTaskUseCase by lazy {
        PostTaskUseCase(TaskModelRepository(realmInstance))
    }

    private val deleteTask: PatchTaskDeleteUseCase by lazy {
        PatchTaskDeleteUseCase(TaskModelRepository(realmInstance))
    }

    private val taskItemStream: BehaviorSubject<TaskItem> = BehaviorSubject.create()

    fun postTask(name: String) {
        postTask.excute(PostTaskUseCase.RequestValue(name))
                .subscribe({ (task) ->
                    taskItemStream.onNext(TaskItem(task))
                }, { e ->
                    taskItemStream.onError(e)
                })
    }

    fun getTaskItemStream(): Observable<TaskItem> = taskItemStream

    fun deleteTask(uuid: String) {
        deleteTask.excute(PatchTaskDeleteUseCase.RequestValue(uuid = uuid, isDeleted = true))
                .subscribe({
                }, {

                })
    }

}