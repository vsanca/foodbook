
     (function() {
         'use strict';

         angular
             .module('foodbookApp.guest').service('GuestService', GuestService);
             
             
             GuestService.$inject = ['$scope', '$http' /*'sessionService' */ ];
         function GuestService($scope, $http/*, sessionService*/) {


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
