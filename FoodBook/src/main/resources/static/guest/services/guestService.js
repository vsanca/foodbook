
     (function() {
         'use strict';

         angular
             .module('foodbook').service('guestService', guestService);
             
             
             guestService.$inject = ['$scope', '$http' /*'sessionService' */ ];
         function guestService($scope, $http/*, sessionService*/) {


         return {
             getProfileDetail: getProfileDetail
         };

         /** getProfileDetails (secured)
         * @param {int} id - GuestID
         */
         function getProfileDetails(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-profile-page-info/' + id,
                headers: {
                   /* 'Authorization': "Bearer " + sessionService.getSessionData().accessToken */
                 'JeleninHeader' : 'POYY'
                }
            });
         }


         }

    
     })();
