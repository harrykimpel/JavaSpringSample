requirejs.config({
    baseUrl: 'js/',
  	
    paths: {
    	jquery: '../components/jquery/dist/jquery.min',
    	appLib:'lib/applib',
        bootstrap: '../components/bootstrap/dist/js/bootstrap.min',
        angular: '../components/angular-1.4/angular.min',
        'angular-animate': '../components/angular-1.4/angular-animate.min',
        'uiRouter': '../components/angular-ui-router/angular-ui-router',
        domReady: '../components/requirejs-domready/domReady',
        'sanitize': '../components/angular-1.4/angular-sanitize.min',
        'angular-messages': '../components/angular-1.4/angular-messages.min'
    },

    shim: {
        'jquery': {
            exports: '$'
        },
        'appLib':{
        	deps: ['jquery']
        },
        angular: {
            exports:'angular',
            deps: ['jquery']
        },
        'angular-animate': {
            deps: ['angular']
        },
        'uiRouter': {
        	deps: ['angular']
    	},         
        bootstrap: {
            deps: ["jquery"]
        },
        'sanitize': {
            deps: ["angular"]
        },
        'angular-messages':{
        	deps: ['angular']
        }
    }
});
