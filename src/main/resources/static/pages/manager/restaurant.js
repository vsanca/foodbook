angular.module('foodbook').controller('managerRestaurantController', function($scope, $http, $state, sessionService) {
	
	$scope.restaurant = {};
	
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
	});
	
	//name, description, address, email, phone
	$scope.modifyRestaurant = function() {
		$http.post('/restaurants/update', $scope.restaurant,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
				})
				.error(function() {
					alert('Greška prilikom modifikacije.');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.restaurant = data;
		});
	};
});