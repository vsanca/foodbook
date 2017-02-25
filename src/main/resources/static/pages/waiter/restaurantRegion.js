angular.module('foodbook').controller('waiterRestaurantRegionController', function($scope, $http, $state, $stateParams, sessionService, notifyService) {
	
	$scope.activePageNumber = 2;
	
	var canvas = new fabric.CanvasEx('restaurant_canvas');
	$scope.canvas = canvas;
	
	canvas.backgroundColor = '#d0d5dd';
	
	canvas.renderAll();
});