angular.module('foodbook').controller('managerHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.activePageNumber = 0;
	
	$scope.user = {};
	
	$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
				
	});
	
	$scope.modifyManager = function() {
		$http.post('/user/rmanager/update', $scope.user,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izvršena izmena.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene.');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.user = data;
		});
	};
	
	$scope.getManager = function() {
		$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId).success(function (data) {
			$scope.user = data;
		});
	};
	
});