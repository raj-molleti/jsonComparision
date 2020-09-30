package com.compare.aci.comparing;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestJsonCompareByFile {
	
	public static void main() throws IOException, ParseException{
		
		JsonParser parser = new JsonParser();
		
		JsonElement obj = parser.parse(new FileReader(""));
		JsonObject jsonObject1 = (JsonObject)obj;
		
		
		JsonParser parserRight = new JsonParser();
		
		JsonElement objRight = parser.parse(new FileReader(""));
		JsonObject jsonObjectRight = (JsonObject)obj;
		
		
		JsonCompare compare = new JsonCompare();
		
		List<String> changeList = compare.twoEntitiesCompare(jsonObject1, jsonObjectRight);
		
		for(String str:changeList){
			String replaceString = str.replace("/elements/", "[");
			String replaceString2 = replaceString.replace("/members/", "]");
			
			System.out.println(replaceString2);
		}
		
	}

}
