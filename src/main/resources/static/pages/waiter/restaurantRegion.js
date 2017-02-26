angular.module('foodbook').controller('waiterRestaurantRegionController', function($scope, $http, $state, $stateParams, sessionService, notifyService) {
	
	$scope.activePageNumber = 2;
	
	var canvas = new fabric.CanvasEx('restaurant_canvas');
	$scope.canvas = canvas;
	
	$scope.user = {};
	
	$scope.colors = ['white', 'red', 'blue', 'green', 'yellow'];
	
	$scope.areas = [];
	
	$scope.tables = [];
	
	$scope.area = {};
	
	$scope.table = {};
	
	$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
				
	});
	
	
	
	canvas.backgroundColor = '#d0d5dd';
	
	canvas.renderAll();
	
	
});