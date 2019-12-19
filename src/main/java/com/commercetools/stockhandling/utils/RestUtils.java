/**
 * 
 */
package com.commercetools.stockhandling.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This Class provided Generic implementation for Response Entity which will be returned to the user
 * @author M.Othman
 *
 * @param <T> It will inject any Response type 
 */
public class RestUtils<T> {
	
	public ResponseEntity<T> addObj(T t) {
		try {
			if (t != null) {
				return new ResponseEntity<T>(t, HttpStatus.OK);
			} else {
				
				return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<T> getObj(T t) {
		try {
			if (t != null) {
				return new ResponseEntity<T>(t, HttpStatus.OK);
			} else 
			{
				return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<T>> getObjList(List<T> list) {
		try {
			return new ResponseEntity<List<T>>(list, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<List<T>>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> getStringObj(String t) {
		try {
			return new ResponseEntity<String>(t, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}

