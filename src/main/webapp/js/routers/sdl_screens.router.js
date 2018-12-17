
define([
    'angular',
],  function (ng) {
	'use strict';
	var app =  ng.module('app.sdl.screens.router', ['ui.router']);
	
	app.config(['$stateProvider', '$urlRouterProvider',
	    function ($stateProvider,   $urlRouterProvider) {
		    
	    //////////////////////////////
	    // Redirects and Otherwise //
	    /////////////////////////////
	      $stateProvider
	        ////////////////////
	        // Search Results //
	        ///////////////////
	        .state('sdl_screens', {

	          // With abstract set to true, that means this state can not be explicitly activated.
	          // It can only be implicitly activated by activating one of it's children.
	          abstract: true,
	          // This abstract state will prepend '/sdl_screens' onto the urls of all its children.
	          url: '/sdl_screens',
	          templateUrl: 'js/partials/sdl_screens.html',
	          controller: 'sdl.screens.controller'
	        })
 
			.state('sdl_screens.carcustsel', {
            url: '/carcustsel',
            templateUrl: 'js/partials/sdl_screens/carcustsel.html',
            controller: 'carcustsel.controller'   
          })
			.state('sdl_screens.carcustmaint', {
            url: '/carcustmaint',
            templateUrl: 'js/partials/sdl_screens/carcustmaint.html',
            controller: 'carcustmaint.controller'   
          })
			.state('sdl_screens.CarCustM', {
            url: '/CarCustM',
            templateUrl: 'js/partials/sdl_screens/CarCustM.html',
            controller: 'CarCustM.controller'   
          })
			.state('login', {
	        url: '/login',
	        templateUrl: 'js/partials/login.html',
	        controller: 'login.controller'
	      })                   
 		}
	  ]);
	  
	  app.config(['$httpProvider', function ($httpProvider) {
	    //$httpProvider.interceptors.push('authInterceptorService');
	    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
	        return {
	            request: function (request) {
	                if ($rootScope.isAuth != undefined && $rootScope.isAuth) {
	                    //request.headers.Authorization = 'Bearer ' + $rootScope.token;
	                	//request.headers['X-Auth-Token'] = $rootScope.authToken;
	                }
	                else {
	                    $location.path('/login');
	                }	                
	                return request;
	            },
	            response: function (data) {
	                return data;
	            },
	            // This is the responseError interceptor
	            responseError: function (rejection) {
	                if (rejection.status === 401) {
	                    // Return a new promise
	                    //return userService.authenticate().then(function () {
	                    //    return $injector.get('$http')(rejection.config);
	                    //});
	                }

	                /* If not a 401, do nothing with this error.
                     * This is necessary to make a `responseError`
                     * interceptor a no-op. */
	                return $q.reject(rejection);
	            }
	        };
	    });
	}]);
	
	return app;
});