angular.module('foodbook').controller('managerReportController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.restaurantGrades = [];
	$scope.mealGrades = [];
	$scope.waiterGrades = [];
	$scope.waiters = [];
	$scope.meals = [];
	
	$scope.restaurant = {};
	$scope.selectedMeal = {};
	$scope.selectedWaiter = {};
	$scope.start_date = {};
	$scope.end_date = {};
	
	$scope.type = 'bar';
	$scope.options = {
			scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	};
	
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				
				$scope.restaurant = data;
				
				$scope.getGrades();
				
				$http.get('/user/waiter/forRestaurant/'+ $scope.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							$scope.waiters = data;
				});
				
				$http.get('/restaurant/menuItem/all/'+ $scope.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							
							for(i=0; i<data.length; i++) {
								if(data[i].item.itemType.name == 'FOOD') {
									$scope.meals.push(data[i]);
								}
							}
							
							console.log($scope.meals);
				});
				
	});
	
	$scope.getGrades = function() {
		
		$scope.type = 'bar';
		
		$scope.options = {
				scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
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
	
	$scope.getGradesForMeal = function() {
		
		$scope.type = 'bar';
		
		$scope.options = {
				scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
		$http.get('/data/meal/'+ $scope.restaurant.id + '/' + $scope.selectedMeal.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.mealGrades = data;
					
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

					
					$('#chartHeader').text('Ocene jela: '+ $scope.selectedMeal.item.name);
					$('#additionalData').text('Prosečna ocena: '+(avg/cnt).toFixed(2));
		});
		
	};
	
	$scope.getGradesForWaiter = function() {
		
		$scope.type = 'bar';
		
		$scope.options = {
				scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
		$http.get('/data/waiter/'+ $scope.selectedWaiter.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.waiterGrades = data;
					
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

					
					$('#chartHeader').text('Ocene konobara: '+ $scope.selectedWaiter.name + ' ' + $scope.selectedWaiter.surname);
					$('#additionalData').text('Prosečna ocena: '+(avg/cnt).toFixed(2));
		});
		
	};
	
	$scope.getRevenueByWaiters = function() {
		
		$scope.type = 'bar';
		
		$scope.options = {
				scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
		$http.get('/data/revenueByWaiter/'+ $scope.restaurant.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.labels = [];
					$scope.data = [];
					
					var totalRevenue = 0;
					
					for(i=0; i<data.length; i++){
						$scope.labels.push(data[i].name);
						$scope.data.push(data[i].value);
						totalRevenue = totalRevenue + data[i].value;
					}

					
					$('#chartHeader').text('Prihodi po konobarima');
					$('#additionalData').text('Ukupni prihod: '+totalRevenue);
		});
		
	};
	
	$scope.getRevenueInDateInterval = function() {
		
		$scope.type = 'line';
		
		$scope.options = {
				scales: {
					xAxes: [{
					    type: 'time',
					    ticks: {
					        autoSkip: true,
					        maxTicksLimit: 20
					    }
					}],
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
		var reportDTO = {
				'start_interval': $scope.start_date,
				'end_iterval': $scope.end_date
			}
		
		$http.post('/data/revenue/'+ $scope.restaurant.id, reportDTO,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.labels = [];
					$scope.data = [];
					
					var totalRevenue = 0;
					
					for(i=0; i<data.length; i++){
						$scope.labels.push(data[i].name);
						$scope.data.push(data[i].value);
						totalRevenue = totalRevenue + data[i].value;
					}

					
					$('#chartHeader').text('Prihodi u periodu');
					$('#additionalData').text('Ukupni prihod u periodu: '+totalRevenue);
		});
		
	};
	
	$scope.getVisits = function() {
		
		$scope.type = 'line';
		
		$scope.options = {
				scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		};
		
		var reportDTO = {
				'start_interval': $scope.start_date,
				'end_iterval': $scope.end_date
			}
		
		$http.get('/data/visits/'+ $scope.restaurant.id, reportDTO, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.labels = [];
					$scope.data = [];
					
					var totalRevenue = 0;
					
					for(i=0; i<data.length; i++){
						$scope.labels.push(data[i].name);
						$scope.data.push(data[i].value);
						totalRevenue = totalRevenue + data[i].value;
					}

					
					$('#chartHeader').text('Prihodi po konobarima');
					$('#additionalData').text('Ukupni prihod: '+totalRevenue);
		});
		
	};
	
});