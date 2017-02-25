angular.module('foodbook').controller('managerSetTimetableController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.shift = {};
	$scope.restaurant = {};
	
	$scope.areas = [];
	$scope.shifts = [];
	$scope.workers = [];
	
	var bartenders = [];
	var chefs = [];
	var waiters = [];
	
	$http.get('/restaurants/areas/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.areas = data;
	});
	
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
				
				$scope.shift.restaurantId = $scope.restaurant.id;
				
				$http.get('/user/bartender/forRestaurant/'+ $scope.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							
							bartenders = data;
							
							for(i=0; i<bartenders.length; i++) {
								bartenders[i].bartender = true;
								bartenders[i].chef = false;
								bartenders[i].waiter = false;
							}
							
							$http.get('/user/chef/forRestaurant/'+ $scope.restaurant.id, 
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										
										chefs = data;
										
										for(i=0; i<chefs.length; i++) {
											chefs[i].bartender = false;
											chefs[i].chef = true;
											chefs[i].waiter = false;
										}
										
										$http.get('/user/waiter/forRestaurant/'+ $scope.restaurant.id, 
												{ headers: { 'Authorization': sessionService.getAuthToken() } })
												.success(function (data) {
													
													waiters = data;
													
													for(i=0; i<waiters.length; i++) {
														waiters[i].bartender = false;
														waiters[i].chef = false;
														waiters[i].waiter = true;
													}
													
													$scope.workers = bartenders.concat(chefs).concat(waiters);													
										});
							});
				});
				
				
				
				$http.get('/user/bartender/forRestaurantShifts/'+ $scope.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							
							var calendarShift;
							
							for(i=0; i<data.length; i++) {
								calendarShift = {
										eventColor: "blue",
										title: data[i].bartender.name+" "+data[i].bartender.surname,
										start: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.startTime,
										end: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.endTime
									};
								
								$scope.shifts.push(calendarShift);
							}
							
							$http.get('/user/chef/forRestaurantShifts/'+ $scope.restaurant.id, 
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										
										var calendarShift;
										
										for(i=0; i<data.length; i++) {
											calendarShift = {
													eventColor: "green",
													title: data[i].chef.name+" "+data[i].chef.surname,
													start: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.startTime,
													end: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.endTime
												};
											
											$scope.shifts.push(calendarShift);
										}
										
										$http.get('/user/waiter/forRestaurantShifts/'+ $scope.restaurant.id, 
												{ headers: { 'Authorization': sessionService.getAuthToken() } })
												.success(function (data) {
													
													var calendarShift;
													
													for(i=0; i<data.length; i++) {
														calendarShift = {
																eventColor: "red",
																title: data[i].waiter.name+" "+data[i].waiter.surname,
																start: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.startTime,
																end: new Date(data[i].shift.day).toISOString().substring(0, 10) + "T" + data[i].shift.endTime
															};
														
														$scope.shifts.push(calendarShift);
													}
													
													$('#timetable').fullCalendar({
														header: {
															left: 'prev,next today',
															center: 'title',
															right: 'month, agendaWeek, agendaDay'
														},
														editable: false,
														events: $scope.shifts
													});											
										});
							});
				});
	});
	
	
	
});