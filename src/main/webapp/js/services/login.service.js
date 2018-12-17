define([
    './module',
], function (services) {
    'use strict';
    return services.factory('LoginService', ['$http', function ( $http) {    	
    	    	
    	var loginServiceAPI = {};
    	loginServiceAPI.login = function (username, password) {
    		
    		var formData = 'j_username=' + username + '&j_password=' + password;
    		 
	        return $http({
	            method: 'POST',
	            url:  'j_spring_security_check',
	            data: formData,
	            headers: {
	                "Content-Type": "application/x-www-form-urlencoded",
                    "X-Login-Ajax-call": 'true'
	            }
	          });
	    };
        
        return loginServiceAPI;
    }]);
});