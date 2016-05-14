var app = angular.module('admusr', ['common',
                          'common.interceptors',
                          'common.service',
                          'admusr.user',
                          'admusr.role',
                  //        'admusr.service',
                          'ngRoute',
                          'ngCookies',
                          'angular-loading-bar',
                          'ngAnimate',
                          'ui.bootstrap',
                          'datatables',
                          'checklist-model',
                          'angular.translate']).
    config(['$routeProvider','$httpProvider','cfpLoadingBarProvider', '$translateProvider',
            function ($routeProvider,$httpProvider,$cfpLoadingBarProvider,$translateProvider) {

        //User module mapping
        $routeProvider.when('/user-list',{templateUrl: 'user-list.html', controller: 'UserListController'});
        $routeProvider.when('/user-detail/:id', {templateUrl: 'user-detail.html', controller: 'UserDetailController'});
       
        //Role module mapping
        $routeProvider.when('/role-list',{templateUrl: 'role-list.html', controller: 'RoleListController'});
        $routeProvider.when('/role-detail/:id', {templateUrl: 'role-detail.html', controller: 'RoleDetailController'});

        //Default template
        $routeProvider.otherwise({templateUrl: 'user-list.html', controller: 'UserListController'});
        
        //CSRF headers names to fit spring csrf filter
        $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
        
        //Desactiva spinner loading bar
        $cfpLoadingBarProvider.includeSpinner  = false;

        //httpInterceptor for manage request errors
		$httpProvider.interceptors.push('httpErrorsInterceptor');
		
		//Translate config
		$translateProvider.preferredLanguage('en'); //this config will be ignored, backend have the locale config
		$translateProvider.useLoader('translateLoader', {url:'./messages'});
		$translateProvider.useSanitizeValueStrategy('escaped');
		
		
}]);

//app.run(function($rootScope) {
//		//Check if on route change an redirect to login caused by an end of session is performed
//		$rootScope.$on( "$routeChangeSuccess", function(event, next, current) {
//			if(next.locals.$scope == undefined){
//				console.error("sesion timeout");
//				//window.location.replace("./public/login");
//			}
//		});
//	});

//secure-action directive, check if current user have rights for provided operation, else hide the element
app.directive('secureActionShow', function() {

	return {
		link : function ( $scope, $element, $attributes) {
			//Check if secure-action value exists in userRights array
			if($.inArray($attributes.secureActionShow, userRights)<0) {
				$element.hide();
			}else{
				$element.show();
			}
		},
		restrict : "A"
	};
});
//secure-action directive, check if current user have rights for provided operation, else disable the element
app.directive('secureActionEnable', function() {

	return {
		link : function ( $scope, $element, $attributes) {
			//Check if secure-action value exists in userRights array
			if($.inArray($attributes.secureActionEnable, userRights)<0) {
				$element.attr('disabled',true);
			}else{
				$element.attr('disabled',false);
			}
		},
		restrict : "A"
	};
});

app.factory('translateLoader', function ($http, $q) {
    // return loaderFn
    return function (options) {
    	var defered = $q.defer();
    	var promise = defered.promise;
    	
		$http.get(options.url).success(function(data) {
			defered.resolve(data);
		}).error(function(err) {
			defered.reject(err);
		});
        return promise
    };
});





//Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function ($rootScope, $templateCache) {
//    $rootScope.$on('$viewContentLoaded', function () {
//        $templateCache.removeAll();
//    });
//});