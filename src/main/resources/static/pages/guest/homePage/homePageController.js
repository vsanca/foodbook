/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('HomePageController', HomePageController);

  HomePageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', 'authenticationService'];

  function HomePageController($scope, $state, $location, sessionService, guestService, authenticationService) {
    $scope.userInfo = sessionService.getUserInfo();

    $scope.homePage = []; 

    guestService.getHomePageInfo($scope.userInfo.userId).then(function (response) { 
        $scope.homePage = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login'); 
    
    }
   
  }
})();

 
    