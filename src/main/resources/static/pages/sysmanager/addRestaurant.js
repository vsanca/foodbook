angular.module('foodbook').controller('sysmanagerAddRestaurantController', function($scope, $http, $state, sessionService, notifyService) {

	$scope.restaurant = {};
	
	$scope.cancel = function () {
		$scope.restaurant = {};
	};
	
	$scope.createRestaurant = function() {
		$http.post('restaurants/create', $scope.restaurant,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno dodat restoran.');
					$scope.restaurant = {};
				})
				.error(function() {
					notifyService.showError('Greška prilikom dodavanja restorana.');
				});
	};
	
});