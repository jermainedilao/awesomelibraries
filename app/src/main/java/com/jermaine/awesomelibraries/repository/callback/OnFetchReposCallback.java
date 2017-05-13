package com.jermaine.awesomelibraries.repository.callback;


import com.jermaine.awesomelibraries.api.response.Repo;

import java.util.List;

public interface OnFetchReposCallback {
    void onFetch(List<Repo> responseList);

    void onError();
}
