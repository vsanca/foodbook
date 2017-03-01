/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('ProfilePageController', ProfilePageController);

  ProfilePageController.$inject = ['$scope', '$state', 'sessionService', 'guestService', 'notifyService', 'authenticationService', '$route'];

  function ProfilePageController($scope, $state, sessionService, guestService, notifyService, authenticationService, $route) {
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
  

 guestService.addPeople($scope.userInfo.userId).then(function (response) {
      $scope.addPeople = response.data;
    }, function (error) {

    });

    $scope.dodaj = function (id) {
      guestService.sendFriendshipRequest(id).then(function (response) {

        notifyService.showInfo("uspesno kreiran zahtev za prijateljstvo");
        $route.reload();
      }, function (error) {

      });

    };

    guestService.acceptPeople($scope.userInfo.userId).then(function (response) {
      $scope.acceptPeople = response.data;
    }, function (error) {

    });

    $scope.prihvati = function (id) {
      guestService.acceptFriendshipRequest(id).then(function (response) {

        notifyService.showInfo("uspesno prihvacen zahtev za prijateljstvo");
        $state.go('guest-friends'); 

      }, function (error) {

      });
    }

    $scope.odbaci = function (id) {
      guestService.rejectFriendshipRequest(id).then(function (response) {

        notifyService.showInfo("uspesno odbijen zahtev za prijateljstvo");
        $route.reload();

      }, function (error) {

      });
    }

$scope.ukloni = function (id) {
      guestService.deleteFriend(id).then(function (response) {

        notifyService.showInfo("uspesno izbrisan prijatelj" + id);
        $route.reload();

      }, function (error) {

      });
    }



    $scope.logoff = function() {
      notifyService.showInfo("logoff called");
      authenticationService.logoff(); 
      $state.go('login'); 
    
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

 
