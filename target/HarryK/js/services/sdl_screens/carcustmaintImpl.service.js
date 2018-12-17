
define([
    'jquery',
    './../module',
],  function ($, services) {
    'use strict';
	 return services.factory('carcustmaintImplService',['$http', function ($http) {
		 
		var serviceAPI = {};

		serviceAPI.doDefaultAction = function (dataStr) {
		    var result = $http({
		        method: 'POST',			/*HTTP Method should be changed based on the service HTTP method call*/
		        url: 'momentum/',			/*URL need to be updated based on the service call*/
		        data:dataStr
		          });
		     //Data Manipulations
		    
		    	return result;
		          
		    };
			
		return serviceAPI;	
	 }]);
});	 
