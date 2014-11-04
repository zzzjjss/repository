package com.uf.stock.search;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	public static void main(String[] args) {
		try {
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = FSDirectory.open(new File("c:/jason/lucene/"));
			IndexWriterConfig  config=new IndexWriterConfig(Version.LATEST, analyzer);
			IndexWriter iWriter=new IndexWriter(directory,config);
			Document doc = new Document();
			doc.add(new Field("id","1",TextField.TYPE_STORED));
			doc.add(new Field("businessContent", " sell  computer",TextField.TYPE_STORED));
			iWriter.addDocument(doc);
			iWriter.close();
			
			
			 DirectoryReader ireader = DirectoryReader.open(directory);
			 IndexSearcher isearcher = new IndexSearcher(ireader);
			 QueryParser parser=new QueryParser("businessContent",analyzer);
			 
			 TopDocs docs=isearcher.search(parser.parse("sell"), 1000);
			 for(ScoreDoc sdoc:docs.scoreDocs){
				 Document searcheddoc=isearcher.doc(sdoc.doc);
				 System.out.println(searcheddoc.get("id"));
			 }
			    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
