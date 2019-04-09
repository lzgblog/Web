package com.lucene.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class IndexSearch {
	//注意：QueryParser不支持对数字范围的搜索，它支持字符串范围。
	//数字范围搜索建议使用NumericRangeQuery。
	@Test
	public void indexSearch() throws Exception {
		// 索引数据
		// 创建Query对象
		// 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
		// 第一个参数：默认搜索的域的名称
		QueryParser parser = new QueryParser("description", new StandardAnalyzer());
		// 语句查询
		Query query = parser.parse("description:java");
		// 设置索引的地址
		File path = new File("D:\\lucene\\SearchDirectory");
		Directory directory = FSDirectory.open(path);
		IndexReader read = DirectoryReader.open(directory);
		// 创建indexSearcher
		IndexSearcher searcher = new IndexSearcher(read);

		// 第二个参数：指定需要显示的顶部记录的N条
		TopDocs topDocs = searcher.search(query, 10);
		// 查询的总数
		int totalHits = topDocs.totalHits;
		System.out.println(totalHits);

		// 查询匹配的记录条数
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		for (ScoreDoc scoreDoc : scoreDocs) {
			// 获取文档的id
			int docid = scoreDoc.doc;
			// 通过id 获取document
			Document doc = searcher.doc(docid);

			// 查询对应数据
			System.out.println("" + doc.get("id"));
			System.out.println("" + doc.get("name"));
			System.out.println("" + doc.get("price"));
			System.out.println("" + doc.get("pic"));
			System.out.println("-----------------------");
		}
		read.close();
	}

	// 使用TermQuery类索引方式
	@Test
	public void termQuerySearch() throws Exception {
		// 使用此类 不需要写分词器
		TermQuery query = new TermQuery(new Term("description", "java"));
		File path = new File("D:\\lucene\\SearchDirectory");
		Directory directory = FSDirectory.open(path);
		IndexReader read = DirectoryReader.open(directory);
		// 创建IndexSearcher
		IndexSearcher searcher = new IndexSearcher(read);
		TopDocs search = searcher.search(query, 10);
		// 打印获取的数据条数
		int totalHits = search.totalHits;
		System.out.println(totalHits);
		// 打分 查询匹配的记录条数
		ScoreDoc[] scoreDocs = search.scoreDocs;
		// 循环 获取id
		for (ScoreDoc scoreDoc : scoreDocs) {
			// 获取文档id
			int docid = scoreDoc.doc;
			// 根据id获取当前文档 打印
			Document doc = searcher.doc(docid);
			System.out.println("" + doc.get("id"));
			System.out.println("" + doc.get("name"));
			System.out.println("" + doc.get("price"));
			System.out.println("" + doc.get("pic"));
			System.out.println("-----------------------");
		}
		read.close();
	}

	// 使用NumericRangeQuery类

	@Test
	public void numericRangeQuery() {

		// 创建NumericRangeQuery对象
		// 参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
		Query query = NumericRangeQuery.newFloatRange("price", 55f, 60f, true, false);
		doSearch(query);
	}

	private void doSearch(Query query) {
		try {

			File path = new File("D:\\lucene\\SearchDirectory");
			Directory directory = FSDirectory.open(path);
			IndexReader read;

			read = DirectoryReader.open(directory);
			// 创建IndexSearcher
			IndexSearcher searcher = new IndexSearcher(read);
			TopDocs search = searcher.search(query, 10);
			// 打印获取的数据条数
			int totalHits = search.totalHits;
			System.out.println(totalHits);
			// 打分 查询匹配的记录条数
			ScoreDoc[] scoreDocs = search.scoreDocs;
			// 循环 获取id
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 获取文档id
				int docid = scoreDoc.doc;
				// 根据id获取当前文档 打印
				Document doc = searcher.doc(docid);
				System.out.println("" + doc.get("id"));
				System.out.println("" + doc.get("name"));
				System.out.println("" + doc.get("price"));
				System.out.println("" + doc.get("pic"));
				System.out.println("-----------------------");
			}
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void numericRange() {

		// 创建NumericRangeQuery对象
		// 参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
		Query query = NumericRangeQuery.newFloatRange("price", 55f, 60f, true,
				false);

		Search(query);
	}
	
	private void Search(Query query) {
		// 创建IndexSearcher
		// 指定索引库的地址
		try {
			File indexFile = new File("D:\\lucene\\SearchDirectory");
			Directory directory = FSDirectory.open(indexFile);
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			// 通过searcher来搜索索引库
			// 第二个参数：指定需要显示的顶部记录的N条
			TopDocs topDocs = searcher.search(query, 10);

			// 根据查询条件匹配出的记录总数
			int count = topDocs.totalHits;
			System.out.println("匹配出的记录总数:" + count);
			// 根据查询条件匹配出的记录
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;

			for (ScoreDoc scoreDoc : scoreDocs) {
				// 获取文档的ID
				int docId = scoreDoc.doc;

				// 通过ID获取文档
				Document doc = searcher.doc(docId);
				System.out.println("商品ID：" + doc.get("id"));
				System.out.println("商品名称：" + doc.get("name"));
				System.out.println("商品价格：" + doc.get("price"));
				System.out.println("商品图片地址：" + doc.get("pic"));
				System.out.println("==========================");
				// System.out.println("商品描述：" + doc.get("description"));
			}
			// 关闭资源
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//使用MultiFieldQueryParser类
	@Test
	public void multiFieldQueryParser() throws Exception {
		String[] argument= {"name","description"};
		
		//在MultiFieldQueryParser创建时设置boost值
		//搜索时设置boost值   设置相关度排序   哪个在第一个
		Map<String, Float> boost=new HashMap<>();
		boost.put("name", 100f);//设置name=java时  排在最前面
		
		MultiFieldQueryParser parse=new MultiFieldQueryParser(argument, new StandardAnalyzer(),boost);
		Query query = parse.parse("java");//相当于  name:java OR description:java
		doSearch(query);
	}
}
