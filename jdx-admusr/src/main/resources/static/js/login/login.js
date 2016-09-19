'use strict';

var app = angular.module('login',
		[ 'login.controller', 'ngMaterial', 'angular.translate' ]).config(
		[ '$translateProvider', '$mdThemingProvider',
				function($translateProvider, $mdThemingProvider) {
					/**
					 * Translate config
					 */
					// $translateProvider.preferredLanguage('en'); //this config
					// will be ignored, backend have the locale config
					$translateProvider.useLoader('translateLoader', {
						url : './messages'
					});
					$translateProvider.useSanitizeValueStrategy('escaped');

					/**
					 * Theme config Material angular
					 */
					 $mdThemingProvider.theme('default')
					    .primaryPalette('orange')
					    .accentPalette('pink');
				} ]);