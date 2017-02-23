angular.module('foodbook').controller('managerWorkersController', function($scope, $http, $state, sessionService) {
	
	$scope.restaurant = {};
	$scope.workers = {};
	
	var waiters = [];
	var bartenders = [];
	var chefs = [];
	
	
	$scope.setShift = function () {
		$state.go('manager.setTimetable');
	};
	
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
				
				
	});
	
});