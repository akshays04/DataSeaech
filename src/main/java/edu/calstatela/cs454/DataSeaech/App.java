package edu.calstatela.cs454.DataSeaech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{   
    
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {
        String word = "watch";
        String jsonPath1 = "C:\\Users\\Gaurav\\CS454_Web_Search_Engine\\Indexer\\target\\indexer.json";
        String jsonPath2 = "C:\\Users\\Gaurav\\CS454_Web_Search_Engine\\Indexer\\target\\ranking.json";
        File jsonFile1 = new File(jsonPath1);
        File jsonFile2 = new File(jsonPath2);
        //System.out.println(jsonFile1);
        JSONParser jsonParser = new JSONParser();
		//JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonFile));
		JSONObject indexObj = (JSONObject) jsonParser.parse(new FileReader(jsonFile1));
		JSONObject rankObj = (JSONObject) jsonParser.parse(new FileReader(jsonFile2));
		
		JSONArray jsonArr = (JSONArray) indexObj.get(word);
		List<JSONObject> docList = new ArrayList<JSONObject>();
		List<ResultBean> results = new ArrayList<ResultBean>();
		
		JSONObject jsonObj;
		ResultBean resultSet;
		String temp;
		for(Object obj : jsonArr){
			jsonObj = (JSONObject) obj;
			resultSet = new ResultBean();
			temp = (String) jsonObj.get("UUId");
			resultSet.setId(temp);
			//System.out.println(temp);
			temp = (String) jsonObj.get("Description");
			resultSet.setDesc(temp);
			temp = (String) jsonObj.get("Count");
			resultSet.setCount(Integer.parseInt(temp));
			temp = (String) jsonObj.get("TitleRank");
			resultSet.setTitleRank(Double.parseDouble(temp));
			temp = (String) jsonObj.get("TermFrequency");
			resultSet.setTermFreq(Double.parseDouble(temp));
			results.add(resultSet);
		}
		
		double temp1;
		for(ResultBean result : results){
			//System.out.println(result.getId());
			if(rankObj.get(result.getId())!=null){
			jsonObj = (JSONObject) rankObj.get(result.getId());
			temp1 = (Double) jsonObj.get("Rank");
			result.setPageRank(temp1);
			temp = (String) jsonObj.get("URL");
			result.setUrl(temp);
			//System.out.println(temp);
			}
		}
		cleanUrl(results);
		sortByTotal(results);
		//sortByPageRank(results);
		
		for(ResultBean result : results){
			System.out.println(result.getUrl());
		}
		
        
    }
    
	public static void sortByPageRank(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getPageRank()<results.get(j+1).getPageRank())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByWordCount(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getCount()<results.get(j+1).getCount())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTitleRank(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getTitleRank()<results.get(j+1).getTitleRank())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTermFreq(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getTermFreq()<results.get(j+1).getTermFreq())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTotal(List<ResultBean> results){
    	double total1 = 0.0;
    	double total2 = 0.0;
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				total1 = (10*results.get(j).getPageRank())+results.get(j).getCount()+results.get(j).getTitleRank()+results.get(j).getTermFreq();
				total2 = (10*results.get(j+1).getPageRank())+results.get(j+1).getCount()+results.get(j+1).getTitleRank()+results.get(j+1).getTermFreq();
				
				System.out.println(total1+" vs "+total2);
				if(total1<total2)
					swap(results,j,j+1);
			}
    }
    
    public static void swap(List<ResultBean> results, int x, int y){
    	ResultBean temp;
		temp = results.get(x);
		results.set(x, results.get(y));
		results.set(y, temp);
    }
    public static List<ResultBean> cleanUrl(List<ResultBean> result)
    {
    	Iterator<ResultBean> iterator = result.iterator();
    	while(iterator.hasNext())
    	{
    		ResultBean resultBean = iterator.next();
    		if(resultBean.getUrl().contains(".rss") || resultBean.getUrl().contains(".css"))
    		{
    			iterator.remove();
    		}
    	}
    	return result;
    }
    
    
}
