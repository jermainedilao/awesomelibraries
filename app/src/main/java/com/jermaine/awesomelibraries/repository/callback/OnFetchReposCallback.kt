package com.jermaine.awesomelibraries.repository.callback


import android.arch.lifecycle.LiveData

import com.jermaine.awesomelibraries.datamodel.repo.Repo

interface OnFetchReposCallback {
    fun onSuccess(repoList: LiveData<List<Repo>>)

    fun onError()
}
