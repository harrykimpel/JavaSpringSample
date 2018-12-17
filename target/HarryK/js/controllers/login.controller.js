
define([
    './module',
], function (controllers) {
    'use strict';

    controllers.controller('login.controller', ['$rootScope', '$scope', '$location', 'LoginService',	function ($rootScope, $scope, $location, loginService) {
				    $scope.login = function () {
				    	loginService.login($scope.username, $scope.password).success(function(_data){
				    		//var authToken = _data.tokenTransfer.token;
							//$rootScope.authToken = authToken;
				    		$rootScope.error = "";
				    		$rootScope.isAuth = true;
				    		
				    		$location.path('/sdl_screens/carcustsel');   	
							   				             
				    	}).error(function(_data){
				    		$rootScope.error = "Failed to login";
				    		$location.path('/login');				    		
				    	})
				    };
				}]);
});