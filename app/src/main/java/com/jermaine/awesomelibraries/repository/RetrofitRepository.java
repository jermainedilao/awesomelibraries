package com.jermaine.awesomelibraries.repository;


import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;

import java.util.List;

public interface RetrofitRepository {
    void save(Repo repo);

    void save(List<Repo> repoList);

    void fetchRepos(OnFetchReposCallback onFetchReposCallback);
}
