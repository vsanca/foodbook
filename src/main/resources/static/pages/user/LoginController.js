/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('LoginController', LoginController);
  LoginController.$inject = ['$scope', '$http', 'authenticationService', 'userRoles', '$location', '$state', 'sessionService', 'GoogleSignin', 'notifyService', '$facebook'];

  function LoginController($scope, $http, authenticationService, userRoles, $location, $state, sessionService, GoogleSignin, notifyService, $facebook) {
    console.log("LoginController ready!");
    
    // možda ovakva provera da li je već ulogovan, pa odgovarajući redirect, isprobano - radi ali zakomentarisano da bi se moglo lakše testirati
    /*
    if(sessionService.getUserInfo() !== null) {
    	var role = sessionService.getUserInfo().role
    	
    	if (role === userRoles["GUEST"]) {
        	$state.go('guest-profile')
        } else if (role === userRoles['CHEF']) {
        	$state.go('chef')
        } else if (role === userRoles['WAITER']) {
        	$state.go('waiter')
        } else if (role === userRoles['MANAGER']) {
        	$state.go('manager');
        } else if (role === userRoles['BIDDER']) {
        	$state.go('bidder');
        } else if (role === userRoles['BARTENDER']) {
        	$state.go('bartender')
        } else if (role === userRoles['SYS_MANAGER']) {
        	$state.go('sysmanager');
        }
    	
    }
    */
    
    $scope.user = {
      username: null,
      password: null
    };

    $scope.login = function () {
      authenticationService.login($scope.user.username, $scope.user.password).then(function (data) {
    	  
        if (data.role === userRoles["GUEST"]) {
        	$state.go('guest-home')
        } else if (data.role === userRoles['CHEF']) {
        	$state.go('chef')
        } else if (data.role === userRoles['WAITER']) {
        	$state.go('waiter')
        } else if (data.role === userRoles['MANAGER']) {
        	$state.go('manager');
        } else if (data.role === userRoles['BIDDER']) {
        	$state.go('bidder');
        } else if (data.role === userRoles['BARTENDER']) {
        	$state.go('bartender')
        } else if (data.role === userRoles['SYS_MANAGER']) {
        	$state.go('sysmanager');
        } else {
          notifyService.showError('LoginController::Invalid user role:::' + data.role);
          $location.path("/");
        }
      }, function (error) {
        notifyService.showError('Login failed!');
      });
    };


    $scope.register = function () {
      $location.path("/guest/register");
    }

    $scope.loginWithGoogle = function() {
        GoogleSignin.signIn().then(function (user) {
            console.log(user);
        }, function (err) {
            console.log(err);
        });
    }; 
    $scope.loginWithFacebook = function() {
      $facebook.login().then(function() {
        fetchUserData();
      });
    }
  function fetchUserData() {
    $facebook.api("/me?fields=name,email").then( 
      function(response) {
        console.log(response);
       
      },
      function(err) {
        $scope.welcomeMsg = "Please log in";
      });
  }
  


  }

})();