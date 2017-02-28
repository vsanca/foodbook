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

	
	canvas.backgroundColor = '#d0d5dd';
	canvas.renderAll();
	
	$http.get('/restaurants/tables/currentTables/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
					$scope.tables = data;
					if($scope.tables){
						
						for(var j in $scope.tables){
							
					   	   var table = $scope.tables[j];
					   
						   var t = JSON.parse(table.fabricTable);
				           var fabricTable;
				           
				           fabricTable = new fabric.Rect(t);
				           table.fabricTable = fabricTable;
				           canvas.add(fabricTable);
				       }
						canvas.selection = false;
						canvas.forEachObject(function(o) {
						  o.selectable = false;
						});
						canvas.renderAll();
					}
					else{
						alert("Nema stolova");
					}
			       
			  })
			  .error(function() {
					notifyService.showError('Nemate trenutno stolove u svojoj nadležnosti');
			  });
	
	
});