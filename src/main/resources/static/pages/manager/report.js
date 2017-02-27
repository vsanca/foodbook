angular.module('foodbook').controller('managerReportController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.restaurant = {};
	$scope.restaurantGrades = [];

	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
				
				$scope.getGrades();
	});
	
	$scope.getGrades = function() {
	
		$http.get('/data/restaurant/'+ $scope.restaurant.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.restaurantGrades = data;
					
					$scope.labels = [];
					$scope.data = [];
					
					var avg = 0;
					var cnt = 0;
					
					for(i=0; i<data.length; i++){
						$scope.labels.push(data[i].grade);
						$scope.data.push(data[i].value);
						avg = avg + data[i].grade*data[i].value;
						cnt = cnt + data[i].value;
					}

					
					$('#chartHeader').text('Ocene restorana');
					$('#additionalData').text('Prosečna ocena: '+(avg/cnt).toFixed(2));
		});
		
	};
	
});