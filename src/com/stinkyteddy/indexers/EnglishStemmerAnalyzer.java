package com.stinkyteddy.indexers;

import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;

public class EnglishStemmerAnalyzer extends SnowballAnalyzer {
    public EnglishStemmerAnalyzer() {
	super(Version.LUCENE_30, "English");
    }
}
