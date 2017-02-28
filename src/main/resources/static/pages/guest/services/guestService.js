/**
*  @author Jelena Jankovic RA139-2013
 * Handles calls to Guest REST backend
 */
(function () {
    'use strict';

    angular.module('foodbook').service('guestService', guestService);

    guestService.$inject = ['$http', 'sessionService'];

    // hail satan
    function guestService($http, sessionService) {


        return {
            getProfilePageInfo: getProfilePageInfo,
            register: register,
            verifyGuest: verifyGuest,
            getHomePageInfo: getHomePageInfo,
            getFriendsPageInfo: getFriendsPageInfo,
            updateProfileInfo: updateProfileInfo,
            getRestaurantsPageInfo: getRestaurantsPageInfo,
            getReserve1PageInfo: getReserve1PageInfo,
            getReserve2PageInfo: getReserve2PageInfo,
            getGuestFriends: getGuestFriends,
            createReservation: createReservation,
            getGuestReservations: getGuestReservations
        };


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getProfilePageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-profile-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        function register(newGuest) {

            return $http({
                method: 'POST',
                data: newGuest,
                url: '/rest/guest/register-guest'
            });
        }


        function verifyGuest(id) {
            // encodeURL
            return $http({
                method: 'GET',
                url: '/rest/guest/confirm-registration/' + id
            });
        }


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getHomePageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-home-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getFriendsPageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-friends-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function updateProfileInfo(name, surname, address, id) {
            var payload = {
                name: name,
                surname: surname,
                address: address
            };

            return $http({
                method: 'POST',
                data: payload,
                url: '/rest/guest/update-profile-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getRestaurantsPageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-restaurants-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getReserve1PageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-reserve1-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
      * @param {int} id - GuestID
      */
        function getReserve2PageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-reserve2-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getGuestFriends(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-guest-friends-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /**
         * @param  {object} reservationInfo - date required to create a new reservation
         */
        function createReservation(reservationInfo) {

            return $http({
                method: 'POST',
                data: reservationInfo,
                url: '/rest/guest/create-new-reservation',
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

        /** getProfileDetails (secured)
                * @param {int} id - GuestID
                */
        function getGuestReservations(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-guest-reservations/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }

    }

})();
