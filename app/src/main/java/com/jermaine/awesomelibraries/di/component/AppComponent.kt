package com.jermaine.awesomelibraries.di.component

import com.jermaine.awesomelibraries.di.module.AppModule
import com.jermaine.awesomelibraries.repository.ReposRepositoryImpl
import com.jermaine.awesomelibraries.uicontroller.activity.additem.AddRepoViewModel
import com.jermaine.awesomelibraries.uicontroller.activity.repolist.RepoListActivity
import com.jermaine.awesomelibraries.uicontroller.activity.repolist.RepoListViewModel
import com.jermaine.awesomelibraries.uicontroller.activity.viewitem.ViewItemViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(retrofitRepositoryServer: ReposRepositoryImpl)

    fun inject(repoListActivity: RepoListActivity)

    fun inject(repoListViewModel: RepoListViewModel)

    fun inject(viewItemViewModel: ViewItemViewModel)

    fun inject(addRepoViewModel: AddRepoViewModel)
}