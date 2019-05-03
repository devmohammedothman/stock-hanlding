package com.commercetools.stockhandling.utils;

import java.util.Collection;
import java.util.stream.Collectors;

public class SnippetUtils {
	
	public static String join( Collection<?> collection, String delimiter )
	{
		String concatList = (String) collection.stream().map(Object::toString).collect(Collectors.joining(delimiter));
		
	    return concatList;
	            
	}
}

