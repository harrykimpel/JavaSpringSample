
define(['./app'], function(app) {
    'use strict';
   
    	return app.run(['$rootScope', '$state', '$stateParams',function ($rootScope,   $state,   $stateParams) {

    	    // It's very handy to add references to $state and $stateParams to the $rootScope
    	    // so that you can access them from any scope within your applications.
    		
    	    $rootScope.$state = $state;
    	    $rootScope.$stateParams = $stateParams;
         }])

        .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider,   $urlRouterProvider) {

    	    // Use $urlRouterProvider to configure any redirects (when) and invalid urls (otherwise).
    	    $urlRouterProvider
    	
    	    // The `when` method says if the url is ever the 1st param, then redirect to the 2nd param
    	    // Here we are just setting up some convenience urls.
    	    .when('/l', '/logout')

    	    // If the url is ever invalid, e.g. '/asdf', then redirect to '/home' aka the home state
    	    .otherwise('/login');        	
      	
          //////////////////////////
          // State Configurations //
          /////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
          
         //////////////
         // Search ///
         /////////////

         .state('logout', {
           url: '/logout',
           templateUrl: 'js/partials/logout.html',
           controller: 'logout.controller'   
         })          
      }])
});