
define([
    'jquery',
    './../module',
],  function ($, services) {
    'use strict';
	 return services.factory('CarCustMService',['CarCustMImplService', function ($CarCustMImplService) {
		 	
		var serviceAPI = {};
		
		serviceAPI.doSearch = function (dataStr) {
		    return $CarCustMImplService.doSearch(dataStr);
		    };
		    		    	        
		return serviceAPI;
	
	 }]);
});	 
