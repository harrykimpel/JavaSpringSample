define([
    './module',
], function (services) {
    'use strict';
    return services.factory('LogoutService', ['$http', function ($http) {
    	var logoutServiceAPI = {};
    	logoutServiceAPI.logout = function () {
	        return $http({
	            method: 'GET',
	            url: 'j_spring_security_logout',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            }
	          });
	    };
        
        return logoutServiceAPI;
    }]);
});