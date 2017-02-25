angular.module('foodbook').controller('managerSetLayoutController', function($scope, $http, $state, $stateParams, sessionService, notifyService) {
	
	$scope.activePageNumber = 4;
	
	var canvas = new fabric.CanvasEx('restaurant_canvas');
	$scope.canvas = canvas;
	
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
	
	canvas.backgroundColor = '#d0d5dd';
	
	canvas.renderAll();
	
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
	
	$scope.createTable = function() {
		
		$scope.tables.push($scope.table);
        for (var i in $scope.areas) {
            var a = $scope.areas[i];

            if (a.id == $scope.table.area) {
                $scope.table.fabricTable.fill = a.color;
                break;
            }
        }
        $scope.canvas.add($scope.table.fabricTable);
        
		$('#newTableModal').modal('hide');
	};
	
	$scope.cancelTable = function () {
		$('#newTableModal').modal('hide');
    };
	
	$scope.openDialogForTable = function () {
		$scope.table = {};
		
		$('#newTableModal').modal();
	};
	
	$scope.redrawTables = function () {
		canvas.clear();
		
		for (var i in $scope.tables) {
			
            var table = $scope.tables[i];
            
            var t = JSON.parse(table.fabricTable);
            
            var fabricTable;
            
            fabricTable = new fabric.Rect(t);
            canvas.add(fabricTable);
            table.fabricTable = fabricTable;

        }
		
        canvas.renderAll();
	};
	
	$scope.refreshLayout = function() {
		$http.get('/restaurants/tables/getForManager/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.tables = data;
					$scope.redrawTables();
		});
	};
	
	$scope.refreshTables();
	
	$scope.addTable = function () {
		
		$scope.openDialogForTable();
		
		var rect = new fabric.Rect({
            left: 100, top: 100, fill: 'blue', width: 50, height: 50, opacity: 0.8
        });
		
		$scope.table.fabricTable = rect;
	};
	
	
	$('#modifyTableArea').on('change', function () {
		var optionSelected = $("option:selected", this);
        
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
		
		for (var i in $scope.tables) {
            
			var t = $scope.tables[i];
            
			t.fabricTable.opacity = 1;
            
			t.fabricTable = JSON.stringify(t.fabricTable);
            
			if (t.area.id) {
                t.area = t.area.id;
            }
        }
		
		$http.post('/restaurants/tables/update/'+sessionService.getUserInfo().userId, $scope.tables,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno ažuriran raspored stolova.');
					$scope.tables = data;
					$scope.redrawTables();
				})
				.error(function() {
					notifyService.showError('Greška prilikom ažuriranja rasporeda stolova.');
				})
	};
	
    canvas.on('mouse:down', function (options) {
    	
        for (var i in $scope.tables) {
            
        	var t = $scope.tables[i];
            
        	if (t.fabricTable == options.target) {
                $scope.$apply(function () {
                    $scope.table = t;
                });
                break;
            }
        }
    });
	
	canvas.on('object:selected', function() {
        $scope.$apply(function () {
            $scope.table = {};
        });
    });

    canvas.on('selection:cleared', function() {
            $scope.table = {};
    });

    canvas.on('before:selection:cleared', function() {
        $scope.$apply(function () {
            $scope.table = {};
        });
    });
    
    
});