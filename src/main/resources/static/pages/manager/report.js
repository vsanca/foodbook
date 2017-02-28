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
	$scope.reportDTO = {};
	$scope.selectedOption = {};
	
	
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

		$http.post('/data/revenue/'+ $scope.restaurant.id, $scope.reportDTO,
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
		
		console.log($scope.reportDTO.type);
		
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
		
		console.log($scope.reportDTO.type);
		
		$http.post('/data/visits/'+ $scope.restaurant.id, $scope.reportDTO, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.labels = [];
					$scope.data = [];
					
					var totalVisits = 0;
					
					if($scope.reportDTO.type == 'DNEVNI') {
						for(i=0; i<data.length; i++){
							$scope.data.push(data[i].value);
							$scope.labels.push(data[i].name);
							totalVisits = totalVisits + data[i].value;
						}
						
						for(i=data.length; i<24; i++) {
							$scope.data.push(0);
							if(i<10) {
								$scope.labels.push('0'+i+':00');
							} else {
								$scope.labels.push(i+':00');
							}
						}
						
						$('#chartHeader').text('Posećenost restorana na dnevnom nivou');
						$('#additionalData').text('Ukupna posećenost na dnevnom nivou: '+totalVisits);
						
					} else if($scope.reportDTO.type == 'NEDELJNI') {
						for(i=0; i<data.length; i++){
							$scope.data.push(data[i].value);
							totalVisits = totalVisits + data[i].value;
						}
						
						$scope.labels = ['Mon','Tue','Wed','Thu','Fri','Sat','Sun'];
						
						$('#chartHeader').text('Posećenost restorana na nedeljnom nivou');
						$('#additionalData').text('Ukupna posećenost na nedeljnom nivou: '+totalVisits);
					}
					
					
		});
		
	};
	
});