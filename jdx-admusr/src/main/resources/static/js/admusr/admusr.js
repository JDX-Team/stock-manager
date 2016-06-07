'use strict';

var app = angular.module('app.admusr', [
                                        'app.common',
                                        'app.admusr.controller',
                                        'app.admusr.directive'
                                        ]).config([
                                                   '$httpProvider',
       function ($httpProvider) {
    	   /**
       	 	* Control de token de seguridad
       	 	*/
    	   //CSRF headers names to fit spring csrf filter
           	$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
           	$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
       }]);