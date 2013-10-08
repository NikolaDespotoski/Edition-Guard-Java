package com.edition.guard.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import com.edition.guard.model.PackagingResponse;
import com.edition.guard.model.Response;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class EditionGuardDeserializationExclusionStrategy implements ExclusionStrategy{

	private Class<PackagingResponse> mClazz;
	public EditionGuardDeserializationExclusionStrategy(Class<PackagingResponse> class1){
		mClazz = class1;
	}
	
	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		// TODO Auto-generated method stub
		return shouldSkip(mClazz, arg0);
	}
	private boolean shouldSkip(Class<PackagingResponse> mClazz2, FieldAttributes att){
		Field[] fields = mClazz2.getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true);
			//System.out.println("Field Name unprocessed: "+f.getName());
			if(f.getName().equals(att.getName())){
					System.out.println("Field name: "+f.getName());
					return false;
			}
		}
		return true;
		
	}
}
