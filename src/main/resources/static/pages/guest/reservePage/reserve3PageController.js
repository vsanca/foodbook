/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

    'use strict';

    angular.module('foodbook').controller('Reserve3PageController', Reserve3PageController);

    Reserve3PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService'];

    function Reserve3PageController($scope, $state, $location, sessionService, guestService, $stateParams, authenticationService) {
        $scope.reserve3Page = sessionService.getReservationInfo();
       

        $scope.userInfo = sessionService.getUserInfo();

       
        $scope.date = $scope.reserve3Page.date.toString();

        console.log("Reservation page 3 ready!");
        $scope.models = {
            selected: null,
            lists: {"allFriends": [], "invitedFriends": []}
        };

        // Generate initial model
    for (var i = 1; i <= 3; ++i) {
        $scope.models.lists.allFriends.push({label: "Item A" + i});
        $scope.models.lists.invitedFriends.push({label: "Item B" + i});
    }

        $scope.logoff = function () {
            alert("logoff called");
            authenticationService.logoff();
            $state.go('login');
        }

        $scope.reserve = function (id) {
            $state.go('reserve3');
        }



    }


})();


