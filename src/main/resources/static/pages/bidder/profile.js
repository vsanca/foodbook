angular.module('foodbook').controller('bidderProfileController', function($scope, $http, $state, authenticationService, sessionService, notifyService, $interval) {
	
	$state.transitionTo('bidder.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('bidder.home');
    };

    $scope.overview = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li3').addClass('active');
        $state.transitionTo('bidder.overview');
    };
    
    $scope.biddings = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li4').addClass('active');
        $state.transitionTo('bidder.biddings');
    };

    $scope.newBidding = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li2').addClass('active');
        $state.transitionTo('bidder.newBidding');
    };
	
    $scope.logout = function () {
    	authenticationService.logoff();
        $state.transitionTo('login');
    };
    
    $scope.numberOfActiveBiddings = 0;
    $scope.numberOfRejectedBiddings = 0;
    
    $http.get('/getNumberOfActiveBiddingsForBidder/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.numberOfActiveBiddings = data;
				alert(data);
	});
    
    $http.get('/getNumberOfRejectedBiddingsForBidder/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.numberOfRejectedBiddings = data;
				alert(data);
	});
    
    var pingProcess;
    
    $scope.startPing = function(frequency) {
    	if(angular.isDefined(pingProcess)) {
    		return;
    	}
    	
    	pingProcess = $interval(function() {
    		
    		$http.get('/getNumberOfActiveBiddingsForBidder/'+sessionService.getUserInfo().userId, 
    				{ headers: { 'Authorization': sessionService.getAuthToken() } })
    				.success(function (data) {
    					
    					if($scope.numberOfActiveBiddings!=data) {
    						$scope.numberOfActiveBiddings = data;
    						notifyService.showSuccess('Ponuda je prihvaćena');
    					}
    		});
    		
    		$http.get('/getNumberOfRejectedBiddingsForBidder/'+sessionService.getUserInfo().userId, 
    				{ headers: { 'Authorization': sessionService.getAuthToken() } })
    				.success(function (data) {
    					
    					if($scope.numberOfRejectedBiddings!=data) {
    						$scope.numberOfRejectedBiddings = data;
    						notifyService.showError('Ponuda je odbijena');
    					}
    		});
    		
    	}, frequency);
    };
    
    $scope.stopPing = function() {
    	if(angular.isDefined(pingProcess)) {
    		$interval.cancel(pingProcess);
    		pingProcess = undefined;
    	}
    };
    
    $scope.$on('$destroy', function() {
    	$scope.stopPing();
    });
    
    $scope.startPing(500000);
    
    
    // WEBSOCKET TEST
    $(document).ready(function() {
    	var socket = new SockJS('/ws');
    	var stompClient = Stomp.over(socket);
    	
    	stompClient.connect({}, function(frame){
    		stompClient.subscribe("/notifyBidder/"+sessionService.getUserInfo().userId, function(data){
    			
    			notifyService.showSuccess(data.body);
    			
    		});
    	});
    });
    
});