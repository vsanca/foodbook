angular.module('foodbook').controller('managerRegisterWorkerController', function($scope, $http, $state, sessionService, notifyService) {
	
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
					$http.post('/'+$scope.selected+'/create', $scope.worker,
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								notifyService.showSuccess('Uspešno dodat radnik.');
							})
							.error(function() {
								notifyService.showError('Greška prilikom dodavanja radnika.');
							});
					
		});
		
		
		
	}
});