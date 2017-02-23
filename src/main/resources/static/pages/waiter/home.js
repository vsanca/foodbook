angular.module('foodbook').controller('waiterHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.user = {};
	
	$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	$scope.modifyWaiter = function() {
		$http.post('/user/waiter/update', $scope.user,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.user = data;
		});
	};
	
	$scope.getWaiter = function() {
		$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId).success(function (data) {
			$scope.user = data;
		});
	};
	
});