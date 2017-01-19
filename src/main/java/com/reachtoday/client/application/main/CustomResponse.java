package com.reachtoday.client.application.main;

import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
/**
 * Wrapper class for the Response
 * 
 * @author Debasmita
 */
public class CustomResponse{
	
	private Response newResponse;
	private int localStatus=0;
	private String localDataValue;
	
	/**
	 * Setter method to set the response
	 * 
	 * @param response
	 */
	public void setResponse(Response response)
    {
        this.newResponse=response;
    }
	
	/**
	 * Setter method to set the value of "localStatus"
	 * 
	 * @param status is an integer
	 */
	public void setLocalStatusCode(int status)
	{
		this.localStatus=status;
	}
	
	/**
	 * Setter method to set the value of "localDataValue"
	 * 
	 * @param data is a string
	 */
	public void setLocalDataValue(String data)
	{
		this.localDataValue=data;
	}
	
	/**
	 * Getter method to get the value of "localStatus"
	 * 
	 * @return the value of localStatus
	 */
	public int getLocalStatusCode()
	{
		return this.localStatus;
	}
	
	/**
	 * Getter method to get the value of "localDataValue"
	 * 
	 * @return the value of localDataValue
	 */
	public String getLocalDataValue()
	{
		return this.localDataValue;
	}
	
	/**
	 * Getter method to get the response
	 * 
	 * @return the Response
	 */
	public Response getResponse()
	{
      return newResponse;
    }
    
	/**
	   * Returns the value of the requested header or null if the header was not
	   * specified.
	   * 
	   * @param header the header to query for
	   * @return the value of response header
	   * 
	   * @throws IllegalArgumentException if the header name is empty
	   * @throws NullPointerException if the header name is null
	   */
    public String getHeader(String header) {
        return newResponse.getHeader(header);
    }

    /**
     * Returns an array of HTTP headers associated with this response.
     * 
     * @return array of HTTP headers; returns zero length array if there are no
     *         headers
     */
    public Header[] getHeaders() {
        return newResponse.getHeaders();
    }

    /**
     * Returns all headers as a single string.
     * 
     * @return all headers as a single string
     */
    public String getHeadersAsString() {
        return newResponse.getHeadersAsString();
    }

    /**
     * Returns the HTTP status code that is part of this response.
     * 
     * @return the HTTP status code
     */
    public int getStatusCode() {
        return newResponse.getStatusCode();
    }

    /**
     * Returns the HTTP status message text.
     * 
     * @return the HTTP status message text
     */
    public String getStatusText() {
        return newResponse.getStatusText();
    }

    /**
     * Returns the text associated with the response.
     * 
     * @return the response text
     */
    public String getText() {
        return newResponse.getText();
    }

    /**
     * Returns the value of the key "Login" associated with the response
     * 
     * @return value of the key "Login"
     */
    public Boolean getLoginValue()
    { 
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("Login");
        return Boolean.parseBoolean(jsonDataValue.isString().stringValue());   	
    }
    
    /**
     * Returns the value of the key "Success" associated with the response
     * 
     * @return value of the key "Success"
     */
    public Boolean getSuccessValue()
    { 
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("Success");
        return Boolean.parseBoolean(jsonDataValue.isString().stringValue());    	
    }    
    
    /**
     * Returns the value of the key "Data" associated with the response
     * 
     * @return value of the key "Data" as a string
     */
    public String getDataValue()
    { if(this.localStatus==200){
    	return localDataValue;
    }
    else{
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("Data");
        return jsonDataValue.toString();	
    }
    }
    
    /**
     * Returns the value of the key "Data" associated with the response
     * 
     * @return value of the key "Data" as a JSONObject
     */
    public JSONObject getDataValueObject()
    {
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("Data");
        return jsonDataValue.isObject();
    }
    
    /**
     * Returns the value of the key "Data" associated with the response
     * 
     * @return value of the key "Data" as a JSONArray
     */
    public JSONArray getDataValueArray()
    {
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("Data");
        return jsonDataValue.isArray();
    }
    
    /**
     * Returns the value of the key "Errorcode" associated with the response
     * 
     * @return value of the key "Errorcode" as a string
     */
    public String getErrorCode()
    {
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("ErrorCode");
        return jsonDataValue.toString();
    }
    
    /**
     * Returns the value of the key "isAuthorizationPresent" associated with the response
     * 
     * @return value of the key "isAuthorizationPresent"
     */
    public Boolean getIsAuthorizationPresent()
    {
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("isAuthorizationPresent");
        return Boolean.parseBoolean(jsonDataValue.isString().stringValue());
    }
    
    /**
     * Returns the value of the key "canUserAccess" associated with the response
     * 
     * @return value of the key "canUserAccess"
     */
    public Boolean getCanUserAccess()
    {
		JSONValue jsonValue=JSONParser.parse(newResponse.getText());
        JSONObject jsonObject=jsonValue.isObject();
        JSONValue jsonDataValue=jsonObject.get("canUserAccess");
        return Boolean.parseBoolean(jsonDataValue.isString().stringValue());
    }
}
