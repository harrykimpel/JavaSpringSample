define([
	'./module',
], function (applicationData) {
    'use strict';
    return applicationData.factory('AppData', [function(){
    	  var cachedData = {};

    	  cachedData.employeeList;
    	  cachedData.hasEmployeeResults = false;
    	  cachedData.employeeSearchKeyword = "";

    	  cachedData.setEmployeeList = function(empList){
    		  cachedData.employeeList = empList;
    	  };
    	  
    	  return cachedData;
    	}]); 
});
