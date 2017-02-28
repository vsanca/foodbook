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
       for(let i = 0; i < response.data.length; i++) {
         response.data[i].terminOdStr = "";
         let asDate = new Date(response.data[i].terminOd);
          response.data[i].terminOdStr = "" + (asDate.getMonth() + 1) + "." + asDate.getDate() + "." + asDate.getFullYear();
          asDate = new Date(response.data[i].terminDo);         
          response.data[i].terminDoStr = "" + (asDate.getMonth() +1 ) + "." + asDate.getDate() + "." + asDate.getFullYear();

       }
       
       $scope.myReservations = response.data; 

       alert("MYRESERVATION!");
    }, function (error) {
      notifyService.showError('Greska pri ucitavanju podataka za prikaz reservacija!');
    });
   
    $scope.openReservationDetails = function(id) {
      $state.go('reservation.details/:reservationId', {reservationId: id}); 
    };

  }
})();

 
