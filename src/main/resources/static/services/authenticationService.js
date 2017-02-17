/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('foodbook')
        .service('authenticationService', authenticationService);
    
    
    authenticationService.$inject = ['$q', '$http', 'sessionService'];

    function authenticationService($q, $http, sessionService) {

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
            
            // prepare a deffered promise that will be resolved after auth. was completed
            var deferred = $q.defer();

             $http({
                method: 'POST',
                data: payload,
                url: '/rest/auth/login'
            }).then(function(response) {
                let authToken = response.headers('Authorization');
                sessionService.getAuthToken(response.data.token);
                let userObj = {
                    userId: response.data.userId,
                    role: response.data.role
                };

                sessionService.setUserInfo(userObj);
                // resolve promise in order to notify controller 
                // that login was successfull 
                deferred.resolve(userObj); 
            }, function(error) {
                // reject promise in order to notify controller that 
                // login failed
                deferred.reject("Authorization failed!");
            });
            
            // return promise to controller, promise will be resolved after authentication was completed
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