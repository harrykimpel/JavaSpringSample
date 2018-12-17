
define([
'./../module'
], function (controllers) {
    'use strict';
    controllers.controller('CarCustM.controller', ['$scope', '$location', '$state', 'CarCustMService', 'AppData' ,function ($scope, $location, $state, CarCustMService, appData) {
		
		$scope.carCustM = {};
		$scope.message="";		
		$scope.doSearch = function()
		{
			CarCustMService.doSearch($scope.carCustM).success(function (_data)
			{
			  $scope.message = _data.responseMessage;							
			}).error(function(_data)
			{
			  $scope.message = _data.responseMessage;	
			});
		};
 
	
	
	}]);
});