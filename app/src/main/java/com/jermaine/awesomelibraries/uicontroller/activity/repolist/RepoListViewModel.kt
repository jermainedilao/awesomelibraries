package com.jermaine.awesomelibraries.uicontroller.activity.repolist

import android.arch.lifecycle.ViewModel
import com.jermaine.awesomelibraries.di.component.AppComponent
import com.jermaine.awesomelibraries.repository.ReposRepositoryImpl
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback
import javax.inject.Inject


class RepoListViewModel : ViewModel() {

    @Inject
    lateinit var reposRepository: ReposRepositoryImpl

    fun init(appComponent: AppComponent) {
        appComponent.inject(this)


    }

    fun fetchRepos(callback: OnFetchReposCallback) {
        reposRepository.fetchRepos(callback)
    }
}