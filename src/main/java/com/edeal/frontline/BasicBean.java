package com.edeal.frontline;

import org.json.JSONObject;

public interface BasicBean {

	public abstract void populateFromJson(JSONObject json, boolean save) throws AccessDeniedException;



}
