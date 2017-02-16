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
    
    
    authorizationService.$inject = ['$q'];

    function authorizationService($q) {

        return {
        	login: login,
        	logoff: logoff
        };

        
      
        /**
         * Abstracts away login mechanicm, returns a new Q deffered promise to  Controller
         * After performing a successful login, stores the token and user id to Session Service
         * 
         * @param  {string} username
         * @param  {string} password
         * @returns {Promise} - deffered promise 
         */
        function login(username, password) {
            var payload = {
                username: username,
                password: password
            };
            
            var deferred = $q.defer();

             $http({
                method: 'GET',
                body: payload,
                url: '/rest/guest/get-profile-page-info/' + id,
                headers: {
                   /* 'Authorization': "" + sessionService.getSessionData().accessToken */
                 'JeleninHeader' : 'POYY'
                }
            }).then(function(response) {
                response.   
                
                deferred.resolve(); 
            }, function(error) {

            });
            

             return deferred.promise;
        }
        
        /**
         * @returns JWT auth token
         */
        function logoff() {
        	return localStorage.getItem('token');
        }

    }

})();