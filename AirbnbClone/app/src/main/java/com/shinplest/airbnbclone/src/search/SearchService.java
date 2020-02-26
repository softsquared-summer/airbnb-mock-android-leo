package com.shinplest.airbnbclone.src.search;

import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;

public class SearchService {
    private final SearchActivityView mSearchActivityView;

    public SearchService(SearchActivityView searchActivityView) {
        this.mSearchActivityView = searchActivityView;
    }
}
