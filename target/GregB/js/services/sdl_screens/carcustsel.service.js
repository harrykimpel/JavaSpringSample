
define([
    'jquery',
    './../module',
],  function ($, services) {
    'use strict';
	 return services.factory('carcustselService',['carcustselImplService', function ($carcustselImplService) {
		 
		var serviceAPI = {};

		serviceAPI.doDefaultAction = function (dataStr) {
		    return $carcustselImplService.doDefaultAction(dataStr);
		    };
			
		return serviceAPI;	
	 }]);
});	 
