/**
 * Created by anft on 19.12.2014.
 */
'use strict'; // Use Javascript Strict mode

var services = angular.module('services', ['resources']);

// Ticket service
services.service('ticketService', ['$http', function ($http) {

    // Loads all tickets
    this.listAllTickets = function () {
        return $http.get('rest/allTickets').then(this.handleSuccess, this.handleError('Tickets loaded'))
    };

    this.createTicket = function (ticket) {
        return $http.put('rest/createTicket', ticket).then(this.handleSuccess, this.handleError('Tickets could not be retrieved'));
    };

    // private Funktionen

    this.handleSuccess = function(res) {
        return res.data;
    };

    this.handleError = function(error) {
        return function () {
            return { success: false, message: error };
        };
    };

}]);


// Developer service
services.service('developerService', ['$http', '$rootScope', '$cookieStore', function ($http, $rootScope, $cookieStore) {

    // create developer
    this.createDeveloper = function (developer) {
        return $http.put('rest/developer/create', developer);
    };



    this.loginDeveloper = function(benutzerkennung, passwort, callback) {
        $http.post('rest/developer/checkPassword', { username: benutzerkennung, password: passwort })
            .success(function (response) {
                callback(response);
            });

    };

    this.setCredentials = function(username, password) {

        $rootScope.globals = {
            currentUser: {
                username: username,
                password: password
            }
        };

        $http.defaults.headers.common['Authorization'] = username + ':' + password;
        $cookieStore.put('globals', $rootScope.globals);
    };

    //Alle Daten vom Login zur�cksetzen
    this.clearCredentials = function() {
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = '';
    };


    // private Funktionen

    this.handleSuccess = function(res) {
        return res.data;
    };

    this.handleError = function(error) {
        return function () {
            return { success: false, message: error };
        };
    };
}]);