angular.module('foodbook').controller('managerSetLayoutController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.user = {};
	
	$scope.colors = ['white', 'red', 'blue', 'green', 'yellow'];
	
	$scope.areas = [];
	
	$scope.tables = [];
	
	$scope.area = {};
	
	$scope.table = {};
	
	$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	$http.get('/restaurants/areas/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.areas = data;
	});
	
	$scope.createArea = function () {
		
		if ($scope.area.name && $scope.area.color) {
			
			$scope.area.restaurantManagerId = sessionService.getUserInfo().userId;
			
			$http.post('/restaurants/areas/new', $scope.area,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						notifyService.showSuccess('Uspešno dodat segment.');
						$scope.areas.push(data);
					})
					.error(function() {
						notifyService.showError('Greška prilikom dodavanja segmenta.');
					});
			
			$scope.area = {};
		}
	};
	
	var canvas = new fabric.CanvasEx('restaurant_layout');
	$scope.canvas = canvas;
	
	canvas.backgroundColor = '#d0d5dd';
	canvas.renderAll();
	
	$scope.startCreateTable = function () {
		$scope.table = {};
		
		$('#newTableModal').modal();
		
	};
	
	$scope.createTable = function() {
		
		var square = new fabric.Rect({
			left: 50,
			top: 50,
			fill: 'blue',
			width: 25,
			height: 25,
			opacity: 0.5
		});
		
		$scope.table.fabric = square;
		
		for(var i in $scope.areas) {
			var a = $scope.areas[i];
			if(a.id == $scope.table.area) {
				$scope.table.fabric.fill = a.color;
				break;
			}
		}
		
		$scope.tables.push($scope.table);
		
		console.log($scope.table.fabric);
		$scope.canvas.add($scope.table.fabric);
		
		$('#newTableModal').modal('hide');
		
	    canvas.renderAll();
	};
	
	$scope.redraw = function () {
		canvas.clear();
		
		for(var i in $scope.tables) {
			var table = $scope.tables[i];
			var parsed = JSON.parse(table.fabric);
			
			var fabric;
			
			fabric = new fabric.Rect(parsed);
			canvas.add(fabric);
			table.fabric = fabric;
			
		}
		
		canvas.renderAll();
	};
	
	
	$scope.refreshLayout = function() {
		$http.get('/restaurants/tables/getForManager/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.tables = data;
					$scope.redraw();
		});
	};
    
	$scope.refreshLayout();
	
	
	$('#modifyTableArea').on('change', function () {
        var valueSelected = this.value;
        for (var i in $scope.areas) {
            var a = $scope.areas[i];
            if (a.id == valueSelected) {
                $scope.table.fabricTable.fill = a.color;
                canvas.renderAll();
                break;
            }
        }
    });
	
	$scope.saveLayout = function () {
		
		for(var i in $scope.tables) {
			var t = $scope.tables[i];
			
			t.fabric.opacity = 1;
			t.fabric = JSON.stringify(t.fabric);
			
			if(t.area.id) {
				t.area = t.area.id;
			}
		}
		
		$http.post('/restaurants/tables/update'+sessionService.getUserInfo().userId, $scope.tables,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno ažuriran raspored stolova.');
					$scope.tables = data;
					$scope.redraw();
				})
				.error(function() {
					notifyService.showError('Greška prilikom ažuriranja rasporeda stolova.');
				});
	};
	
});