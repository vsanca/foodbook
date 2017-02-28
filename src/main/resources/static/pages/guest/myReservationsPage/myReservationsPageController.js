/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('MyReservationsPageController', MyReservationsPageController);

  MyReservationsPageController.$inject = ['$scope', '$state', 'sessionService', 'guestService', 'notifyService', 'authenticationService'];

  function MyReservationsPageController($scope, $state, sessionService, guestService, notifyService, authenticationService) {
    $scope.userInfo = sessionService.getUserInfo();
  
    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login'); 
    
    }

    guestService.getGuestReservations($scope.userInfo.userId).then(function (response) {
       $scope.myReservations = response.data; 
    }, function (error) {
      notifyService.showError('Greska pri ucitavanju podataka za prikaz reservacija!');
    });
   
  }
})();

 
