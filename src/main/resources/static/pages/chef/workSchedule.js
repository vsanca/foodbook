angular.module('foodbook').controller('chefWorkScheduleController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.activePageNumber = 1;
	
	
	
	$scope.shifts = [];
	
	$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
				
				$http.get('/user/chef/allMyShifts/'+sessionService.getUserInfo().userId, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
								var calendarShift;
								for(i=0; i<data.length; i++) {
									calendarShift = {
											color: "#ff0000",
											title: $scope.user.name+" "+$scope.user.surname,
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
							
						
						 })
						 .error(function() {
								notifyService.showError('Nema smena za prikaz!');
						 });
			
	});
	
	
	
});