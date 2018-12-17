define([
    'require',
    'angular',
    'angular-animate',
    'bootstrap',
    'sanitize',  
    './app',
    './routes'
], function (require, ng) {
    'use strict';

    require(['domReady!'], function (document) {
        ng.bootstrap(document, ['app']);
    });
});