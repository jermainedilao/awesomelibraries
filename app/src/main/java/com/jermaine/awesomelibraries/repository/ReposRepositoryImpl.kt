package com.jermaine.awesomelibraries.repository


import android.util.Log
import com.jermaine.awesomelibraries.api.ApiService
import com.jermaine.awesomelibraries.datamodel.database.NewAndroidDatabase
import com.jermaine.awesomelibraries.datamodel.repo.Repo
import com.jermaine.awesomelibraries.di.component.AppComponent
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

class ReposRepositoryImpl(appComponent: AppComponent) : ReposRepository {

    val TAG: String = "ReposRepository"

    @Inject
    lateinit var mApiService: ApiService
    @Inject
    lateinit var database: NewAndroidDatabase

    init {
        appComponent.inject(this)
    }

    override fun save(repo: Repo) {
        val executor = Executor {
            Thread(it).start()
        }
        executor.execute({database.repoDao().save(repo)})
    }

    override fun save(repoList: List<Repo>) {
        val executor = Executor {
            Thread(it).start()
        }
        executor.execute({database.repoDao().save(repoList)})
    }

    override fun fetchRepos(callback: OnFetchReposCallback) {
        // local
        val executor = Executor {
            Thread(it).start()
        }
        executor.execute({
            callback.onSuccess(database.repoDao().getRepos())
        })

        // from server
        val call = mApiService.repos
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                // save in db
                save(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.d(TAG, t.message)
            }
        })
    }
}
