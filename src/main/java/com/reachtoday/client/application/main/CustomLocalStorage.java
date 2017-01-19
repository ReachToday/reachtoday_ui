package com.reachtoday.client.application.main;

import com.google.gwt.http.client.RequestException;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import com.google.inject.Inject;

/**
 * Class for implementing the concept of Local Storage
 * 
 * @author Debasmita
 *
 */
public class CustomLocalStorage {
	
	public static Storage patientprotalStorage = Storage.getLocalStorageIfSupported();
	public static StorageMap patientprotalStorageMap = new StorageMap(patientprotalStorage);
	String responseJSONStorage;
	String responseJSONServer;
	int flag;
	private String JSON_URL ;
	@Inject
	public CustomLocalStorage(ActionServletURL actionServletURL)
	{
		this.JSON_URL=actionServletURL.getBaseURL();
	}
	
	/**
	 * This method is called to get the required response from Local Storage else from the server directly.
	 * 
	 * @param isBackgroundTask is a Boolean value which indicates whether the task is a background task or not
	 * @param key is the key whose value is set as the response received from the server
	 * @param customRequestCallBack is the CustomRequestCallBack object
	 */
	public void getLocalData(int isBackgroundTask, final String key, final CustomRequestCallback customRequestCallBack,CustomResponse customResponse){
		
		flag = 0;
		
		if (patientprotalStorage != null && patientprotalStorage.getLength() > 0 && patientprotalStorageMap.containsKey(key) == true) {
			//Checks whether the key is present in the local storage or not
			responseJSONStorage = patientprotalStorageMap.get(key);
			customResponse.setLocalStatusCode(200);
			customResponse.setLocalDataValue(responseJSONStorage);
			customRequestCallBack.onResponseReceived(null, customResponse);
		} 
		else {
			flag = 1;
		}
		
		
		if (flag == 1) {
			//This part executes when the key is not present in the local storage
			URLCollection urlCollection=new URLCollection();
			String url=urlCollection.getValue(key);
			String actualUrl = JSON_URL + url.toString();
			CustomRequestBuilder builder1 = new CustomRequestBuilder(
					CustomRequestBuilder.GET, actualUrl,isBackgroundTask);
			try {
				builder1.sendRequest(null,new CustomRequestCallback() {	
					@Override
					public void onResponseReceived(CustomRequest request, CustomResponse response) {
						customRequestCallBack.onResponseReceived(request, response);
						if(response.getStatusCode()==200){
							responseJSONServer=response.getDataValue();
							
							//After the response is received, its stored in the local storage
							if (patientprotalStorage != null) {
								patientprotalStorage.setItem(key,responseJSONServer);
							}
						}
					}
					
					@Override
					public void onError(CustomRequest request, Throwable exception) {
						customRequestCallBack.onError(request, exception);
					}
				});
			} catch (RequestException e) {
			}

		}
	}
}