define([
	'jquery',
    './module',
],function ($, directives) {
	'use strict';
	directives.factory('languageManager', ['$http', '$rootScope', 'localize', function ($http, $rootScope, localize) 
	{
        var languageManager = 
        {
        	setUrl: function(value) 
            {
        		var url = value+".js";
                localize.setUrl(url);
            }
         };
            
         return languageManager;
    }])
    .directive('languageDropdown', ['languageManager',function(languageManager){
        var languageDropdownDirective = {
            restrict: 'EA',

            transclude: true,
            
            replace: true,
            
            scope:{},
            
            controller: ['$scope','$http', function ($scope, $http) {
            	
	            // array to hold the list of languages
            	$scope.languageList = [];
	            // flag to indicate the service loaded the list of languages
            	var languageListLoaded = false;
            	
            	$scope.selectedLanguage = '';
	
	            var getLanguageResourceUrl = function() {
	                return 'i18n/resources-locale_';
	            };
	            
	            var getAvailableLanguages =  function()
	            {
	                var url = getLanguageResourceUrl();
	                url = url+"list.js";
	                // request the resource file
	                $http({ method:"GET", url:url, cache:false }).success(function (data) {
	                	
	                    // store the returned array in the languageList
	                    $scope.languageList = data;
	                    // set the flag that the resource are loaded
	                    languageListLoaded = true;
	                    
	                    if($scope.languageList.length>0){
	                    	$scope.selectedLanguage = $scope.languageList[0].label;
	                    }
	                	
	                }).error(function () {
	                    console.log("Could not load the language list, Please try after some time")
	                });
	            };
            	
                $scope.changeLanguage = function ($event) 
                {
                	$event.preventDefault();
                    var targetEl = $($event.target);
                    var anchorEl = undefined;
                    if (!(targetEl.is('a'))) {
                        anchorEl = targetEl.parent();
                    } else {
                        anchorEl = targetEl;
                    }

                    var language = $(anchorEl).attr("data-lang-value");
                    var langLabel = $(anchorEl).attr("data-lang-label");

                	var url = getLanguageResourceUrl();
                	url = url+language;
                	languageManager.setUrl(url);
                	
                	$scope.selectedLanguage = langLabel;
                };
                
                getAvailableLanguages();
            }],
            
            template:'<li class="dropdown">'+
            		'<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{{selectedLanguage}} <span class="caret"></span></a>'+
            		'<ul class="dropdown-menu"><li ng-repeat="language in languageList"><a href="javascript:void(0)" ng-click="changeLanguage($event)" data-lang-value="{{language.value}}" data-lang-label="{{language.label}}">{{language.label}}</a></li></ul>'+
            		'</li>',

        };

        return languageDropdownDirective;
    }]);
});