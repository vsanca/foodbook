angular.module('foodbook').controller('sysmanagerAddRestaurantManagerController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.manager_new = {};
	
	$scope.cancel = function () {
		$scope.manager_new = {};
	};
	
	$scope.restaurants = [];
	
	$http.get('restaurants/all', 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurants = data;
	});
	
	$scope.createRestaurantManager = function() {
		
		$http.post('user/rmanager/create', $scope.manager_new,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno dodat menadžer restorana.');
					$scope.manager_new = {};
				})
				.error(function() {
					notifyService.showError('Greška prilikom dodavanja menadžera restorana.');
				});
	}
});