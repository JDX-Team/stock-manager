var app = angular.module('app', [
	                          'app.sige',
	                          'ngCookies',
	                          'angular-loading-bar',
	                          'ngAnimate',
	                          'ui.bootstrap',
	                          'ui.router',
	                          'angular.translate'
	                          ])
   .config(['$stateProvider', '$urlRouterProvider','$httpProvider','cfpLoadingBarProvider', '$translateProvider',
            function ($stateProvider,$httpProvider,$cfpLoadingBarProvider,$translateProvider) {
    	
    	//Evaluamos el resto
    	$stateProvider
    	 .state('otherwise', {
               url: '',
               redirectTo: 'kpi.metricas.sitActual'
         })
         .state('kpi', {
               url: '/kpi',
               redirectTo: 'kpi.metricas.sitActual',
               data: {
                   displayName: 'KPI'
               }
         })
         
          .state('kpi.metricas', {
               url: '/metricas',
               redirectTo: 'kpi.metricas.sitActual',
               data: {
                   displayName: 'Métricas'
               }
         })
         .state('kpi.metricas.sitActual', {
               url: '/sitActual',
               views: {
                   'content@': {
                       templateUrl: 'sitActual.html',
                       controller: 'SitActualController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Situación actual de los indicadores'
               }
         })
         .state('kpi.metricas.gestComer', {
               url: '/GestComer',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Gestión Comercial',
                   metrica: 'operativaComercial'
               }
         })
         .state('kpi.metricas.dComerInm', {
               url: '/DComerInm',
               views: {
                   'content@': {
                	   templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Depuración Comercial de Inmuebles',
                   metrica: 'depuracionInmueble'
               }
         })
         .state('kpi.metricas.expCompl', {
               url: '/ExpCompl',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Expedientes Completos',
                   metrica: 'documentacion'
               }
         })
         .state('kpi.metricas.sanJuri', {
               url: '/SanJuri',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Saneamiento Jurídico',
                   metrica: 'saneamientoJuridico'
               }
         })
         .state('kpi.metricas.segObras', {
               url: '/SegObras',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Seguimiento Obras',
                   metrica: 'obraEnCurso'
               }
         })
         .state('kpi.metricas.gestSuelo', {
               url: '/GestSuelo',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Gestión Suelos',
                   metrica: 'gestionSuelo'
               }
         })
         .state('kpi.metricas.gastoCoste', {
               url: '/GastoCoste',
               views: {
                   'content@': {
                       templateUrl: 'detalleMetrica.html',
                       controller: 'MetricaController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Gastos / Costes',
                   metrica: 'gastosCostes'
               }
         })
         .state('kpi.informes', {
               url: '/informes',
               views: {
                   'content@': {
                       templateUrl: 'cuadroMando.html',
                       controller: 'CuadroMandoController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Informes'
               }
         })
         .state('kpi.informes.cuadroMando', {
               url: '/cuadroMando',
               views: {
                   'content@': {
                       templateUrl: 'cuadroMando.html',
                       controller: 'CuadroMandoController',
                       controllerAs: 'ctrl'
                   }
               },
               data: {
                   displayName: 'Cuadro de Mandos'
               }
         })
    	.state('kpi.informes.manual', {
            url: '/manualUsuario',
            fileDownload: '../static/docs/Manual_de_Usuario_Metricas_v.1.3.pdf',
            data: {
                displayName: 'Cuadro de Mandos'
            }
      });
    	
        //CSRF headers names to fit spring csrf filter
     //   $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
    //    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
        
        //Desactiva spinner loading bar
        $cfpLoadingBarProvider.includeSpinner  = false;

        //httpInterceptor for manage request errors
	//	$httpProvider.interceptors.push('httpErrorsInterceptor');
		
		//Translate config
//		$translateProvider.preferredLanguage('en'); //this config will be ignored, backend have the locale config
//		$translateProvider.useLoader('translateLoader', {url:'./messages'});
//		$translateProvider.useSanitizeValueStrategy('escaped');
		
		
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



