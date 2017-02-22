/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('ProfilePageController', ProfilePageController);

  ProfilePageController.$inject = ['$scope', '$location', 'sessionService', 'guestService', 'notifyService'];

  function ProfilePageController($scope, $location, sessionService, guestService, notifyService) {
    $scope.userInfo = sessionService.getUserInfo();

    $scope.profilePage = {
        name: null, 
        surname: null, 
        address: null, 
        numberOfVisits: null, 
        friends: []
    }; 

    $scope.saveChanges = function() {
        guestService.updateProfileInfo($scope.profilePage.name, $scope.profilePage.surname, $scope.profilePage.address, $scope.userInfo.userId).then(function (response) {
        notifyService.showSuccess('Izmena je uspesno izvrsena!'); 
  }, function (error) {
       notifyService.showError('Izmena nije uspesno izvrsena!'); 
  });

    }

    guestService.getProfilePageInfo($scope.userInfo.userId).then(function (response) {
        $scope.profilePage.name = response.data.name;
        $scope.profilePage.surname = response.data.surname;
        $scope.profilePage.address = response.data.address;
        $scope.profilePage.numberOfVisits = response.data.numberOfVisits;
        $scope.profilePage.friends = response.data.friends;
        
    }, function (error) {
      notifyService.showError('Greska pri ucitavanju podataka za prikaz profilnih informacija!');
    });
   
  }
})();

 
