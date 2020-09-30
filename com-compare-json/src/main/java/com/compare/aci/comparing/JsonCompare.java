package com.compare.aci.comparing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.ValueChange;

import com.google.gson.JsonObject;

public class JsonCompare {
	
	public List<String> twoEntitiesCompare(JsonObject portFoiloDummy,JsonObject newFoiloDummy){
		
		LocalDateTime date1 = LocalDateTime.now();
		
		Javers javers = JaversBuilder.javers()
		        .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
		        .build();
		  
		Diff diff = javers.compare(portFoiloDummy, newFoiloDummy);
		
		List<ValueChange> changes = diff.getChanges().getChangesByType(ValueChange.class);
		
		List<String> changeList = new ArrayList<String>();
		
		for(ValueChange change:changes){
			changeList.add("Path::"+change.getPropertyNameWithPath()+"  Left/Old Value::"+change.getLeft()+" Right/New Value::"+change.getRight());
		}
		
		LocalDateTime date2 = LocalDateTime.now();
		
		Duration dua = Duration.between(date2, date1);
		
		System.out.println("====Milli seconds==="+Math.abs(dua.toMillis()));
		System.out.println("====seconds==="+Math.abs(dua.getSeconds()));
		return changeList;
	}

}
