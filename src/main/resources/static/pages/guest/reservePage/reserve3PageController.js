/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

    'use strict';

    angular.module('foodbook').controller('Reserve3PageController', Reserve3PageController);

    Reserve3PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService', '$interval'];

    function Reserve3PageController($scope, $state, $location, sessionService, guestService, $stateParams, authenticationService, $interval) {
        $scope.reserve3Page = sessionService.getReservationInfo();


        $scope.userInfo = sessionService.getUserInfo();


        $scope.date = $scope.reserve3Page.date.toString();

        console.log("Reservation page 3 ready!");
        $scope.models = {
            selected: null,
            lists: { "allFriends": [], "invitedFriends": [] }
        };

       guestService.getGuestFriends($scope.userInfo.userId).then(function (response) {
            $scope.friendsDTO = response.data;
            // Generate initial model
            for (let i = 0; i < $scope.friendsDTO.length; i++) {
                $scope.models.lists.allFriends.push({ id:$scope.friendsDTO[i].id, label: $scope.friendsDTO[i].nameAndSurname });

            }
        });


        $scope.logoff = function () {
            alert("logoff called");
            authenticationService.logoff();
            $state.go('login');
        }

        $scope.reserve = function (id) {
            let reservationInfo = sessionService.getReservationInfo();
            for(let i = 0; i < $scope.models.lists.invitedFriends.length; i++) {
                reservationInfo.invitedFriends.push($scope.models.lists.invitedFriends[i].id)
            }
            sessionService.setReservationInfo(null);
            guestService.createReservation(reservationInfo).then(function(response) {
                 console.log(response);
                 $state.go('reserve3');
            }, function(err) {
            
        });
           
        }



    }


})();


