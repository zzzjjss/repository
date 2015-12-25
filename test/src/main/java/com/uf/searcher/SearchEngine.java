package com.uf.searcher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.builders.TermQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import com.mysql.fabric.xmlrpc.base.Array;
import com.uf.entity.Product;
import com.uf.util.StringUtil;

public class SearchEngine {
  private Directory directory=null;
  
  public SearchEngine(String indexDir){
    try {
      directory = FSDirectory.open(Paths.get(indexDir));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void addProductInfoToIndex(Product product){
    IndexWriter iWriter=null;
    try{
      Analyzer analyzer = new WhitespaceAnalyzer();
      IndexWriterConfig  config=new IndexWriterConfig(analyzer);
      iWriter=new IndexWriter(directory,config);
      Document doc = new Document();
      StringField idField=new StringField("id", String.valueOf(product.getId()), Store.YES);
      TextField productField=new TextField("product", product.getSearchKeywords(),Store.YES);
      doc.add(idField);
      doc.add(productField);
      iWriter.addDocument(doc);
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      if(iWriter!=null){
        try {
          iWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    
  }
  public List<Integer> searchProductIds(String keyword){
    List<Integer> ids=new ArrayList<Integer>();
    try{
      DirectoryReader ireader = DirectoryReader.open(directory);
      IndexSearcher isearcher = new IndexSearcher(ireader);
     // Analyzer analyzer = new WhitespaceAnalyzer();
     // QueryParser parser=new QueryParser("product",analyzer);
      BooleanQuery.Builder builder=new BooleanQuery.Builder();
      
      if(!StringUtil.isNullOrEmpty(keyword)){
        String keywords[]=StringUtil.splitStringByEmptySpace(keyword);
        for(String key:keywords){
          Query query= new TermQuery(new Term("product",key));
          builder.add(query, Occur.MUST);
        }
        TopDocs  topDocs=isearcher.search(builder.build(), Integer.MAX_VALUE);
        for(ScoreDoc sdoc:topDocs.scoreDocs){
          Document searcheddoc=isearcher.doc(sdoc.doc);
          ids.add(Integer.parseInt(searcheddoc.getField("id").stringValue()));
        }
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return ids;
  }
  public void deleteProductFromIndexById(Integer productId){
    IndexWriter iWriter=null;
    try{
      Analyzer analyzer = new WhitespaceAnalyzer();
      IndexWriterConfig  config=new IndexWriterConfig(analyzer);
      iWriter=new IndexWriter(directory,config);
      iWriter.deleteDocuments(new Term("id",String.valueOf(productId)));;
    }catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException("deleteProductFromIndexById exception");
    }finally{
      if(iWriter!=null){
        try {
          iWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  public void updateProductIndex(Product product){
    IndexWriter iWriter=null;
    try{
      Analyzer analyzer = new WhitespaceAnalyzer();
      IndexWriterConfig  config=new IndexWriterConfig(analyzer);
      iWriter=new IndexWriter(directory,config);
      TextField productField=new TextField("product", product.getSearchKeywords(),Store.YES);
      StringField idField=new StringField("id", String.valueOf(product.getId()), Store.YES);
      List<IndexableField> fields=new ArrayList<IndexableField>();
      fields.add(productField);
      fields.add(idField);
      iWriter.updateDocument(new Term("id",String.valueOf(product.getId())),fields);
    }catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException("updateProductIndex exception");
    }finally{
      if(iWriter!=null){
        try {
          iWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  public  void listAllTerm(){
    try {
      IndexReader indexReader = DirectoryReader.open(directory);
      Fields fields = MultiFields.getFields(indexReader );
      Iterator<String> fieldsIterator = fields.iterator();
      while(fieldsIterator.hasNext()){
          String field = fieldsIterator.next();
          Terms terms = fields.terms(field);
          TermsEnum termsEnums = terms.iterator();
          BytesRef byteRef = null;
          System.out.println("field : "+ field);
          while((byteRef = termsEnums.next()) != null) {
              String term = new String(byteRef.bytes, byteRef.offset, byteRef.length);
              System.out.println("term is : " + term);
          }
      }
      
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
      new SearchEngine("c:/jason/indexDir").listAllTerm();
//      SearchEngine engi=new SearchEngine("c:/jason/indexDir");
//      Product p=new Product();
//      p.setId(5);
//      p.setSearchKeywords("a b");
//      engi.updateProductIndex(p);
      //engi.deleteProductFromIndexById(3);
      //List<Integer> ids=engi.searchProductIds("余仁生");
     // System.out.println(ids.size());
   //new SearchEngine("c:/jason/indexDir").listAllTerm();
  }
}
