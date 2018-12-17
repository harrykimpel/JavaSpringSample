
define([
    'jquery',
    './../module',
],  function ($, services) {
    'use strict';
	 return services.factory('carcustmaintService',['carcustmaintImplService', function ($carcustmaintImplService) {
		 
		var serviceAPI = {};

		serviceAPI.doDefaultAction = function (dataStr) {
		    return $carcustmaintImplService.doDefaultAction(dataStr);
		    };
			
		return serviceAPI;	
	 }]);
});	 
