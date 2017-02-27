/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('foodbook')
        .service('sessionService', sessionService);
    
    function sessionService() {

        return {
        	getAuthToken: getAuthToken,
        	setAuthToken: setAuthToken,
            getUserInfo: getUserInfo,
            setUserInfo: setUserInfo, 
            getReservationInfo: getReservationInfo,
            setReservationInfo: setReservationInfo
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
         *    @typedef UserInfo
         *    @type {object}
         *    @property {int} role - user role 
         *    @property {long} userId - user ID
         */

        /**
         * Stores userInfo as a JSON string within localStorage , if null is specified, deletes userId from localStorage
         * @param  {UserInfo} userInfo
         */
        function setUserInfo(userInfo) {
            if(userInfo === null) {
                localStorage.removeItem('userInfo');
                return;
            }
            localStorage.setItem('userInfo', JSON.stringify(userInfo));
        }

         /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {UserInfo} userInfo as an object
         */
        function getUserInfo() {
            let value = localStorage.getItem('userInfo');
            if(value == null) {
                return null;
            }
            return JSON.parse(value);
        }


        /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {UserInfo} userInfo as an object
         */
        function getReservationInfo() {
            let value = localStorage.getItem('reservationInfo');
            if(value === null) {
                return {
                    name: "",
                    date: new Date(),
                    arrival: new Date(),
                    duration: 0,
                    tables: [],
                    invitedFriends: [], 
                    restaurantId: 0
                };
            }
            return JSON.parse(value);
        }


        /**
         *    @typedef ReservationInfo
         *    @type {object}
         *    @property {string} role - user role 
         *    @property {long} userId - user ID
         */

        /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {ReservationInfo} reservationInfo as an object
         */
        function setReservationInfo(reservationInfo) {
            if(reservationInfo === null) {
                localStorage.removeItem('reservationInfo');
                return;
            }
            localStorage.setItem('reservationInfo', JSON.stringify(reservationInfo));
        }

    }

})();