define([
    './module'
], function (controllers) {
    'use strict';
    controllers.controller('logout.controller', ['$rootScope', '$scope', '$location','$state', 'LogoutService', function ($rootScope, $scope, $location, $state, logoutService) {	
    	$scope.message;
    	$scope.logout = function()
    	{
    		logoutService.logout().success(function (_data) {   
    			$rootScope.isAuth = false;
    			//delete $rootScope.authToken;
    			$location.path('/login');   			  
          	}).
     	   error(function(_data){
     		  $location.path('/login'); 
     	   });
    		
    	};
    }]);
});