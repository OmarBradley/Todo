package lee.todo.application

import android.app.Application
import io.realm.Realm

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}