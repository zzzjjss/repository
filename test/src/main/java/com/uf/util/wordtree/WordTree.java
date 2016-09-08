package com.uf.util.wordtree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.uf.util.StringUtil;

public class WordTree {
  private CharacterNode rootNode=new CharacterNode();
  public static void main(String[] args) {
    WordTree tree=new WordTree();
    //tree.printAllWord();
    try {
      List<String> lines=Files.readLines(new File("C:\\jason\\temp\\main_back.dic"),Charset.forName("UTF-8") );
      for(String line:lines){
        String[] words=line.split(" ");
        for(String word:words){
          if(!Strings.isNullOrEmpty(word)){
            tree.addWord(word.trim());
          }
        }
      }
    
      //tree.printAllWord();
      long begin=System.currentTimeMillis();
      String st="IK Analyzer 是一个开源的，基于java语言开发的轻量级的中文分词工具包。" +
          "从2006年12月推出1.0版开始， IKAnalyzer已经推出了4个大版本。" +
          "最初，它是以开源项目Luence为应用主体的，" +
          "结合词典分词和文法分析算法的中文分词组件。从3.0版本开始，" +
          "IK发展为面向Java的公用分词组件，独立于Lucene项目，" +
          "同时提供了对Lucene的默认优化实现。在2012版本中，" +
          "IK实现了简单的分词歧义排除算法，" +
          "标志着IK分词器从单纯的词典分词向模拟黄道益语义分词衍化龟背。";
      List<String> result= tree.parseWords(st);
      System.out.println((System.currentTimeMillis()-begin));
      for(String r:result){
        System.out.println(r);
      }
      Thread.sleep(1000000l);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
   
    
  }
  public void addWord(String word){
    CharacterNode node=rootNode;
    for(int i=0;i<word.length();i++){
      char c=word.charAt(i);
      CharacterNode childNode=lookforChildNode(node,c);
      if(childNode==null){
        CharacterNode charNode=new CharacterNode();
        Character charObj=new Character(c);
        charNode.setCharacter(charObj);
        charNode.setParentNode(node);
        if(i==(word.length()-1)){
          charNode.setWordEnd(true);
        }
        node.getChilderNodes().put(charObj, charNode);
        node=charNode;
      }else{
        if(i==(word.length()-1)){
          childNode.setWordEnd(true);
        }
        node=childNode;
      }
    }
  }
  public List<String> parseWords(String input){
    List<String>  results=new ArrayList<String>();
   if(!StringUtil.isNullOrEmpty(input)){
     for(int i=0;i<input.length();i++){
       String subInput=input.substring(i);
       List<String> result=parseOnePathWords(subInput);
       results.addAll(result);
     }
   }
   return results;
  }
  
  private List<String> parseOnePathWords(String input){
    List<String> result=new ArrayList<String>();
    if(input!=null&&!input.isEmpty()){
      char[] chars=input.toCharArray();
      String word="";
      Map<Character,CharacterNode> childs=rootNode.getChilderNodes();
      for(char c:chars){
        CharacterNode node=childs.get(new Character(c));
        if(node!=null){
          word=word+c;
          if(node.isWordEnd()){
            result.add(word);
            if(node.getChilderNodes().size()<=0){
              return result;
            }
          }
          childs=node.getChilderNodes();
        }else{
          return result;
        }
      }
    }
    return result;
  }
  
  public void printAllWord(){
    printWord(rootNode,"");
  }
  private  void printWord(CharacterNode  node,String preChars){
    Map<Character,CharacterNode> childs=node.getChilderNodes();
    if(node.isWordEnd()){
      System.out.println(preChars);
    }
    if(childs!=null&&childs.size()>0){
      for(CharacterNode child:childs.values()){
        printWord(child,preChars+child.getCharacter().charValue());
      }
    }
  }
  
  private CharacterNode lookforChildNode(CharacterNode node,char c){
    if(node==null)
      return null;
    Map<Character,CharacterNode> childerNodes=node.getChilderNodes();
    if(childerNodes==null||childerNodes.size()<=0){
      return null;
    }
    return childerNodes.get(new Character(c));
  }
  
  
}
