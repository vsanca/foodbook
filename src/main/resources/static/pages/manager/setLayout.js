angular.module('foodbook').controller('managerSetLayoutController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.user = {};
	
	$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	var canvas = new fabric.CanvasEx('restaurant_layout');
	$scope.canvas = canvas;
	
	canvas.backgroundColor = '#d0d5dd';
	canvas.renderAll();
	
	$scope.refreshLayout = function() {
		
	};
    
	$scope.drawTable = function() {
		
	};
	
});