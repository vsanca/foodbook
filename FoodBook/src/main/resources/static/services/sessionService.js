/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('foodBook')
        .service('sessionService', sessionService);
    
    sessionService.inject

    function sessionService() {

        return {
        	getAuthToken: getAuthToken,
        	setAuthToken: setAuthToken,
            getUserId: getUserId,
            setUserId: setUserId
        };

        
        /**
         * Sets JWT auth token in localStorage
         * @param  {string} token - value received as Authorization header value
         */
        function setAuthToken(token) {
        	if(token === null) {
        		localStorage.removeItem('token');
        		return;
        	}
            localStorage.setItem('token', token);
        }
        
        /**
         * @returns JWT auth token
         */
        function getAuthToken() {
        	return localStorage.getItem('token');
        }
        /**
         * Stores userId , if null is specified, deletes userId from localStorage
         * @param  {long} userId
         */
        function setUserId(userId) {
            if(userId === null) {
                localStorage.removeItem('userId');
                return;
            }
            localStorage.setItem('userId', userId);
        }

        

    }

})();