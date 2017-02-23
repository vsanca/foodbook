angular.module('foodbook').controller('chefHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.user = {};
	
	$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	$scope.modifyChef = function() {
		$http.post('/user/chef/update', $scope.user,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.user = data;
		});
	};
	
	$scope.getchef = function() {
		$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId).success(function (data) {
			$scope.user = data;
		});
	};
	
});