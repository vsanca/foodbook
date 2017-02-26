angular.module('foodbook').controller('waiterRestaurantRegionController', function($scope, $http, $state, $stateParams, sessionService, notifyService) {
	
	$scope.activePageNumber = 2;
	
	var canvas = new fabric.CanvasEx('restaurant_canvas');
	$scope.canvas = canvas;
	
	$scope.user = {};
	
	$scope.areas = [];
	
	$scope.tables = [];
	
	
	$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
				$scope.user = sessionService.getUserInfo().userId;
				
	});
	/*
	$http.post('/restaurants/areas/waiter/', +sessionService.getUserInfo().userId,
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				//notifyService.showSuccess('Radi');
				$scope.areas = data;
			})
			.error(function() {
				notifyService.showError('Ne postoji segment/i za prikaz');
			});
	
	$http.post('/restaurants/tables/getForWaiterIdAllTables/', +sessionService.getUserInfo().userId,
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				//notifyService.showSuccess('Radi');
				$scope.tables = data;
			})
			.error(function() {
				notifyService.showError('Ne postoje stolovi za prikaz');
			});
	*/
	
	canvas.backgroundColor = '#d0d5dd';
	canvas.renderAll();
	
	$http.get('/user/waiter/profileTEST/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.tables = data;
				if($scope.tables){
					alert("Nije prazno");
					for(var j in $scope.tables){
						
				   	   var table = $scope.tables[j];
				   
					   var t = JSON.parse(table.fabricTable);
			           var fabricTable;
			           
			           fabricTable = new fabric.Rect(t);
			           table.fabricTable = fabricTable;
			           canvas.add(fabricTable);
			       }
					canvas.renderAll();
				}
				else{
					alert("Nema stolova");
				}
			       
	});
	
	
	
	
	

        
      
	
	
	
	
});