/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('foodBook')
        .service('authorizationService', authorizationService);
    
    function authorizationService() {

        return {
        	login: login,
        	logoff: logoff
        };

        
        /**
         * Sets JWT auth token in localStorage
         * @param  {string} token - value received as Authorization header value
         */
        function login(username, password) {
            var payload = {
                username: username,
                password: password
            };
            return $http({
                method: 'GET',
                body: payload,
                url: '/rest/guest/get-profile-page-info/' + id,
                headers: {
                   /* 'Authorization': "Bearer " + sessionService.getSessionData().accessToken */
                 'JeleninHeader' : 'POYY'
                }
            });
        }
        
        /**
         * @returns JWT auth token
         */
        function logoff() {
        	return localStorage.getItem('token');
        }

    }

})();