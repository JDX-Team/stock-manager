var app = angular.module('common',[]);

//Controller for modal
app.controller('modalController', ['$scope','$uibModalInstance', 'title', 'msg',
   function($scope, $uibModalInstance,title,msg){
		$scope.title = title;	
		$scope.msg = msg;
		
		//Close callback, close modal
		$scope.close = function () 
		{
			$uibModalInstance.dismiss('close');
		};
		
		//Accept callback, close modal and accept operation
		$scope.accept = function () 
		{
			$uibModalInstance.close();
		};
		
		//Cancel callback, close modal and dismiss operation
		$scope.cancel = function () 
		{
			$uibModalInstance.dismiss('close');
		};
}]);

//Controller for menu
app.controller('menuController', ['$scope','$location',
   function($scope, $location){
	
	/**
	 * Méthod to paint de selected index
	 */
	    $scope.isActive = function (viewLocation) { 
	        return viewLocation === $location.path();
	    };
}]);



