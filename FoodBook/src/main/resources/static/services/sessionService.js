/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('website')
        .service('sessionService', sessionService);
    
    function sessionService() {

        return {
        	getAuthToken: getAuthToken,
        	setAuthToken: setAuthToken
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

    }

})();