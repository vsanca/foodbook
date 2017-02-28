angular.module('foodbook').controller('managerSetTimetableController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.shift = {};
	$scope.restaurant = {};
	$scope.worker = {};
	
	$scope.areas = [];
	$scope.shifts = [];
	$scope.workers = [];
	$scope.assignedAreas = [];
	
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
										color: "#4995ff",
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
													color: "#38bc5e",
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
																color: "#d83131",
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
	
	
	$scope.openModal = function(worker) {
		
		$scope.worker = worker;
		$scope.shift.workerId = $scope.worker.id;
		$scope.shift.workerType = $scope.worker.type;
		
		$scope.assignedAreas = [];
		
		for(i=0; i< $scope.areas.length; i++) {
			$scope.assignedAreas.push(false);
		}
		
		$('#newShiftModal').modal();
	};
	
	$scope.closeModal = function() {
		
		$('#newShiftModal').modal('hide');

	};
	
	$scope.createShift = function() {
		
		if($scope.worker.bartender) {
			
			$http.post('/user/bartender/newShift', $scope.shift,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						notifyService.showSuccess('Uspešno dodata smena za baristu.');
						
						calendarShift = {
								color: "#4995ff",
								title: $scope.worker.name + " " + $scope.worker.surname,
								start: $scope.shift.startDate + "T" + $scope.shift.startTime,
								end: $scope.shift.endDate + "T" + $scope.shift.endTime
							};
						
						$scope.shifts.push(calendarShift);
						
					})
					.error(function() {
						notifyService.showError('Greška prilikom dodavanja smene za baristu.');
					});
			
		} else if ($scope.worker.chef) {
			
			$http.post('/user/chef/newShift', $scope.shift,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						notifyService.showSuccess('Uspešno dodata smena za kuvara.');
						
						calendarShift = {
								color: "#38bc5e",
								title: $scope.worker.name + " " + $scope.worker.surname,
								start: $scope.shift.startDate + "T" + $scope.shift.startTime,
								end: $scope.shift.endDate + "T" + $scope.shift.endTime
							};
						
						$scope.shifts.push(calendarShift);
						
					})
					.error(function() {
						notifyService.showError('Greška prilikom dodavanja smene za kuvara.');
					});
			
		} else if ($scope.worker.waiter) {
			
			$scope.shift.areas = [];
			
			for(i=0; i<$scope.areas.length; i++) {
				if($scope.assignedAreas[i] == true) {
					$scope.shift.areas.push($scope.areas[i].id);
				}
			}
			
			$http.post('/user/waiter/newShift', $scope.shift,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						notifyService.showSuccess('Uspešno dodata smena za kuvara.');
						
						calendarShift = {
								color: "#d83131",
								title: $scope.worker.name + " " + $scope.worker.surname,
								start: $scope.shift.startDate + "T" + $scope.shift.startTime,
								end: $scope.shift.endDate + "T" + $scope.shift.endTime
							};
						
						$scope.shifts.push(calendarShift);

					})
					.error(function() {
						notifyService.showError('Greška prilikom dodavanja smene za kuvara.');
					});
			
		} else {
			notifyService.showError('Greška, nepoznat tip radnika.');
		}
		
		$scope.closeModal();
		
	};
	
});