angular.module('foodbook').controller('managerHomeController', function($scope, $http, $state, sessionService) {
	
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
					
				})
				.error(function() {
					alert('Greška prilikom modifikacije.');
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