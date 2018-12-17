define([
    'angular',
    'uiRouter',
    'angular-animate',
    'sanitize',
    'angular-messages',
    'appLib',
    './directives/index',
    './controllers/index',
    './services/index',
    './data/index',
    './routers/sdl_screens.router'
], function (ng) {
    'use strict';

    return ng.module('app', [
        'ui.router',
        'ngAnimate',
        'ngSanitize',
        'ngMessages',
        'app.directives',
        'app.services',
        'app.controllers',
        'application.data',
        'app.sdl.screens.router'
    ])
});      
