package com.jermaine.awesomelibraries.repository


import com.jermaine.awesomelibraries.datamodel.repo.Repo
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback

interface ReposRepository {
    fun save(repo: Repo)

    fun save(repoList: List<Repo>)

    fun fetchRepos(callback: OnFetchReposCallback)
}
