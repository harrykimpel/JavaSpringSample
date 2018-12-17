
define([
    'jquery',
    './../module',
],  function ($, services) {
    'use strict';
	 return services.factory('CarCustMImplService',['$http', function ($http) {
		 	
		var serviceAPI = {};
		
		serviceAPI.doSearch = function (dataStr) {
		     var result = $http({
		        method: 'POST',			/*HTTP Method should be changed based on the service HTTP method call*/
		        url: 'momentum/searchCustomer',		/* customization: URL updated to call the search customer method */	/*URL need to be updated based on the service call*/
		        data: '{"customerNumber": "'+dataStr.customerNumber+'"}' /* customization: preparing the correct JSON data format */ //dataStr
		          });
		     //Data Manipulations
		    
		    	return result;
		          
		    };
		    		    	        
		return serviceAPI;
	
	 }]);
});	 
