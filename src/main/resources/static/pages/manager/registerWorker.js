angular.module('foodbook').controller('managerRegisterWorkerController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.activePageNumber = 3;
	
	$scope.worker = {};
	
	$scope.cancel = function() {
		$scope.worker = {};
	}
	
	$scope.createWorker = function() {
		
		$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.restaurant = data;
					
					$scope.worker.restaurantId = $scope.restaurant.id;
					
					//alert($scope.selected);
					$http.post('/user/'+$scope.selected+'/create', $scope.worker,
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								notifyService.showSuccess('Uspešno dodat radnik.');
								$scope.worker = {};
							})
							.error(function() {
								notifyService.showError('Greška prilikom dodavanja radnika.');
							});
					
		});
		
		
		
	}
});