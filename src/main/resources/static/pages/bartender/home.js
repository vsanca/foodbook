angular.module('foodbook').controller('bartenderHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.user = {};
	
	$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	$scope.modifyBartender = function() {
		$http.post('/user/bartender/update', $scope.user,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.user = data;
		});
	};
	
	$scope.getbartender = function() {
		$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId).success(function (data) {
			$scope.user = data;
		});
	};
	
	$scope.changePassword = function() {
		$state.go('bartender.changePassword');
	};
	
});