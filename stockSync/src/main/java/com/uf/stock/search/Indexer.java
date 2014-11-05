package com.uf.stock.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
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

import com.uf.stock.bean.CompanyInfo;
import com.uf.stock.dao.CompanyInfoDao;

public class Indexer {
	private String directoryPath;
	public Indexer(String directoryPath){
		this.directoryPath=directoryPath;
	}
	
	public void indexAllContent(){
		try{
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = FSDirectory.open(new File(directoryPath));
			IndexWriterConfig  config=new IndexWriterConfig(Version.LATEST, analyzer);
			IndexWriter iWriter=new IndexWriter(directory,config);
			CompanyInfoDao dao=new CompanyInfoDao();
			List<CompanyInfo> allInfo=dao.findAll();
			for(CompanyInfo info:allInfo){
				Document doc = new Document();
				
				StringField code=new StringField("stockCode", info.getStockCode(), Store.YES);
				doc.add(code);
				StringField name=new StringField("companyName", info.getCompanyName(), Store.YES);
				doc.add(name);
				TextField content=new TextField("businessContent", info.getBusinessContent(), Store.YES);

				doc.add(content);
				iWriter.addDocument(doc);
			}
			iWriter.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void search(String keyword){
		try {
			Directory directory = FSDirectory.open(new File(directoryPath));
			 DirectoryReader ireader = DirectoryReader.open(directory);
			 IndexSearcher isearcher = new IndexSearcher(ireader);
			 Analyzer analyzer = new StandardAnalyzer();
			 QueryParser parser=new QueryParser("businessContent",analyzer);
			 
			 TopDocs docs=isearcher.search(parser.parse(keyword), 10);
			 for(ScoreDoc sdoc:docs.scoreDocs){
				 Document searcheddoc=isearcher.doc(sdoc.doc);
				 System.out.println(searcheddoc.get("stockCode")+":"+searcheddoc.get("companyName")+"-->"+searcheddoc.get("businessContent"));
			 }
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Indexer index=new Indexer("c:/jason/lucene");
		//index.indexAllContent();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		try{
			while((line=reader.readLine())!=null){
				if(line!=null&&!line.trim().equals("")){
					System.out.println(line);
					index.search(line);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
