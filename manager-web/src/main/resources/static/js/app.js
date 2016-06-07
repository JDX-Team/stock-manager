var app = angular.module('app', [
                              'app.common',
	                          'app.admusr',
	                          'app.stkmng',//Referencia al aplicativo stockManager
	                          'ngCookies',
	                          'angular-loading-bar',
	                          'ngAnimate',
	                          'ui.bootstrap',
	                          'ui.router',
	                          'angular.translate'
	                          ])
   .config([
            '$stateProvider',
            '$urlRouterProvider',
            '$httpProvider',
            'cfpLoadingBarProvider',
            '$translateProvider', 
            function ($stateProvider, $urlRouterProvider, $httpProvider, $cfpLoadingBarProvider, $translateProvider) {

            	/**
            	 * Control de token de seguridad
            	 */
                //CSRF headers names to fit spring csrf filter
//            	$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
//            	$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

            	
            	
            	/**
            	 * Desactiva spinner loading bar
            	 */
            	$cfpLoadingBarProvider.includeSpinner  = false;

            	/**
            	 * httpInterceptor for manage request errors
            	 */
//      		$httpProvider.interceptors.push('httpErrorsInterceptor');
            	
            	/**
            	 * Translate config
            	*/
            	$translateProvider.preferredLanguage('en'); //this config will be ignored, backend have the locale config
            	$translateProvider.useLoader('translateLoader', {url:'./messages'});
            	$translateProvider.useSanitizeValueStrategy('escaped');
            	
            	
            	/**
           	 	* Control de navegación
           	 	*/
            	
		    	$stateProvider
		    	 .state('otherwise', {
		               url: '',
		               redirectTo: 'index'
		         })
		         .state('index', {
		        	   url: '/index',
		               views: {
		                   'content@': {
		                       templateUrl: 'user-list.html',	//aún sin definir la pantalla incial
		                       controller: 'UserListController', //aún sin definir la pantalla incial
		                       controllerAs: 'ctrl'
		                   }
		               },
		               data: {
		                   displayName: 'AdmUser Manager Index'
		               }
		         })
		         .state('admusr', {
		        	 url: '/admusr',
		        	 redirectTo: 'admusr.user'
		//        	 views: {
		//                 'content@': {
		//                     templateUrl: 'user-list.html',	
		//                     //aún sin definir la pantalla incial de ADMUSR redirigimos a user-list.html
		//                     controller: 'UserListController', 
		//                     //aún sin definir la pantalla incial de ADMUSR reifigimos al usercontroller
		//                     controllerAs: 'ctrl'
		//                 }
		//             },
		//             data: {
		//                 displayName: 'AdmUser Manager Index'
		//             }
		         })
		         .state('admusr.user', {
		        	 url: '/admusr/user',
		        	 views: {
		                 'content@': {
		                     templateUrl: 'user-list.html',	
		                     controller: 'UserListController', 
		                     controllerAs: 'ctrl'
		                 }
		             },
		             data: {
		                 displayName: 'User Manager Panel'
		             }
		         })
		         .state('admusr.roles', {
		        	 url: '/admusr/roles',
		        	 views: {
		                 'content@': {
		                     templateUrl: 'role-list.html',	
		                     controller: 'RoleListController', 
		                     controllerAs: 'ctrl'
		                 }
		             },
		             data: {
		                 displayName: 'Role Manager Panel'
		             }
		         });
}]);

//Enable redirectTo on states
app.run(['$rootScope', '$state', function($rootScope, $state) {

    $rootScope.$on('$stateChangeStart', function(evt, to, params) {
      if (to.redirectTo) {
        evt.preventDefault();
        $state.go(to.redirectTo, params)
      }
      if (to.fileDownload) {
          evt.preventDefault();
          window.open(to.fileDownload);
        }
    });
}]);






//Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function ($rootScope, $templateCache) {
//    $rootScope.$on('$viewContentLoaded', function () {
//        $templateCache.removeAll();
//    });
//});



