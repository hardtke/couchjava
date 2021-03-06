package com.cloudant.indexers;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.util.*;

public class MyCustomStopWords extends MyCustomSearch {

	private Analyzer analyzer = null;
	private Set<String> stop = null;
	

	public Analyzer getAnalyzer() {
		if (analyzer == null) {
			if (stop != null) {
				return new StandardAnalyzer(Version.LUCENE_30, stop);
			} else {
				return new StandardAnalyzer(Version.LUCENE_30);
				
			}
		} else {
			return analyzer;
		}
	}

	public void setAnalyzer(Analyzer in) {
		analyzer = in;
	}


	public void Configure(String config) {
		try {
			JSONObject cobj = new JSONObject(config);
			JSONArray stopWords = cobj.getJSONArray("stopwords");
			stop = new HashSet<String>();
			if (stopWords != null) {
				for (int i = 0; i < stopWords.length(); i++) {
					try {
						String word = stopWords.getString(i);
						stop.add(word);
					} catch (JSONException je) {
						// this word not a string
					}
				}
			}
		} catch (JSONException e) {
			// what we seek is missing
		}
		super.Configure(config);
		return;
	}

}
