package com.hernandez.mickael.mynews.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

@SerializedName("response")
@Expose
private SearchSubResponse mSearchSubResponse;

public SearchSubResponse getSearchSubResponse() {
return mSearchSubResponse;
}

public void setSearchSubResponse(SearchSubResponse searchSubResponse) {
this.mSearchSubResponse = searchSubResponse;
}

}