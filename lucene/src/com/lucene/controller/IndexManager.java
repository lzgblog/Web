package com.lucene.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lucene.dao.UseData;
import com.lucene.dao.UseDataImpl;
import com.lucene.po.Book;

public class IndexManager {
	@Test
	public void indexManager() throws Exception {
		//采集数据库数据
		UseData dao = new UseDataImpl();
		List<Book> books = dao.findBook();
		List<Document> doclist=new ArrayList<Document>();
		//遍历book  并放入document中
		Document document;
		for (Book book:books) {
			//遍历一条数据就是一个document
			document = new Document();
			//把book里的属性放入field域中
			//store：是否存储到文档域中
			Field id=new TextField("id", book.getId().toString(),Store.YES);
			Field name=new TextField("name", book.getName(), Store.YES);
			Field price=new TextField("price", book.getPrice().toString(), Store.YES);
			Field pic=new TextField("pic", book.getPic(), Store.YES);
			Field description=new TextField("description", book.getDescription(), Store.YES);
			//不分词  不索引    但存储的用new StoredField    不存储Store.NO
			
			//创建索引时设置boost值
			//Boost值是设置到Field域上的。设置相关度排序   哪个在第一个
			if(book.getId()==4) {
				description.setBoost(100f);
			}
			
			
			document.add(id);
			document.add(name);
			document.add(price);
			document.add(pic);
			document.add(description);
			
			doclist.add(document);
		}
		//创建标准分词器
		Analyzer analyzer=new StandardAnalyzer();
		
		IndexWriterConfig cfg=new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		//创建索引库 地址
		File path=new File("D:\\lucene\\SearchDirectory");
		Directory diretory=FSDirectory.open(path);
		//创建indexWriter
		IndexWriter write=new IndexWriter(diretory, cfg);
		// 通过IndexWriter对象将Document写入到索引库中
		for (Document doc:doclist) {
			write.addDocument(doc);
			
		}
		//关闭
		write.close();
	}
	
	//删除document中的一条数据
	@Test
	public void deleteData() throws Exception {
	
		//文档域中已经有数据时  只需要IndexWriter
		Analyzer analyzer=new StandardAnalyzer();
		IndexWriterConfig cfg=new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		File path=new File("D:\\lucene\\SearchDirectory");
		Directory diretory=FSDirectory.open(path);
		IndexWriter write=new IndexWriter(diretory,cfg);
		write.deleteDocuments(new Term("id","1"));
		write.close();
	}
	
	//删除document中所有的数据
	@Test
	public void deleteAllData() throws Exception {
		Analyzer Analyzer=new StandardAnalyzer();
		IndexWriterConfig cfg=new IndexWriterConfig(Version.LUCENE_4_10_3,Analyzer);
		File path=new File("D:\\lucene\\SearchDirectory");
		Directory Directory = FSDirectory.open(path);
		//文档域中已经有数据时  只需要IndexWriter
		IndexWriter write=new IndexWriter(Directory,cfg);
		write.deleteAll();
		write.close();
	}
	
	//修改文档中的数据
	@Test
	public void updateData() throws Exception {
		File path=new File("D:\\lucene\\SearchDirectory");
		Directory Directory=FSDirectory.open(path);
		Analyzer Analyzer=new StandardAnalyzer();
		IndexWriterConfig cfg=new IndexWriterConfig(Version.LUCENE_4_10_3,Analyzer);
		IndexWriter write=new IndexWriter(Directory,cfg);
		Document doc=new Document();
		doc.add(new TextField("name", "solr", Store.YES));
		//不能缺少new Term();
		write.updateDocument(new Term("name","lucene"), doc);
		write.close();
	}
	
	//使用IK-analyzer中文分词器
	@Test
	public void ikanalylzer() throws Exception {
		//获取数据、
		UseData dao = new UseDataImpl();
		List<Book> books = dao.findBook();
		List<Document> doclist=new ArrayList<>();
		Document document;
		//遍历   并把数据放入field域中   存入文档域
		for (Book book : books) {
			document  = new Document();
			
			Field id=new TextField("id", book.getId().toString(), Store.YES);
			Field name=new TextField("name",book.getName(),Store.YES);
			Field price=new FloatField("price",book.getPrice(),Store.YES);
			
			//不分词   不索引   存储到文档域
			Field pic=new StoredField("pic", book.getPic());
			
			// 分词   索引   不存储
			Field description=new TextField("description", book.getDescription(), Store.NO);
			
			//设置相关度
			if(book.getId()==3) {
				description.setBoost(100f);
			}
			
			document.add(id);
			document.add(name);
			document.add(price);
			document.add(pic);
			document.add(description);
			
			doclist.add(document);
					
		}
		
		//创建IndexWriter
		//创建中文分词器IKAnalyzer
		Analyzer analyzer=new IKAnalyzer();
		IndexWriterConfig cfg=new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		File path=new File("D:\\lucene\\SearchDirectory");
		Directory Diretory=FSDirectory.open(path);
		IndexWriter write=new IndexWriter(Diretory,cfg);
		for (Document doc : doclist) {
			write.addDocument(doc);
		}
		write.close();
	}
}
