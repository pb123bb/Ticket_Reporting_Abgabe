/**
 * The application.
 * Created by Stephan on 10.12.2014.
 */
'use strict';

// Create the module for the application. Depends on the controllers module.
var application = angular.module('ticket_reporting', ['controllers', 'resources', 'services', 'ngRoute', 'ngCookies', 'ngTable'])
    .config(config)
    .run(run);


function config ($routeProvider) {
    $routeProvider.when("/login", {
        controller: 'developerController',
        templateUrl: "../views/login.view.html"
    }).when("/ticketAll", {
        controller: 'ticketController',
        templateUrl: "../views/ticketAll.view.html"
    }).when("/register", {
        controller: 'developerController',
        templateUrl: "../views/register.view.html"
    }).when("/ticketCreate", {
        controller: 'ticketController',
        templateUrl: "../views/ticketCreate.view.html"
    }).when("/ticketAlter", {
        controller: 'ticketController',
        templateUrl: "../views/ticketAlter.view.html"
    }).otherwise({
        redirectTo: "/"
    });
}

run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
function run($rootScope, $location, $cookieStore, $http) {
    // Laden der Login Variablen aus dem Cookie
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        //Erneutes Setzen des Headerfeld f�r die Authorisierung
        $http.defaults.headers.common['Authorization'] = $rootScope.globals.currentUser.authdata;
    }

    //Event f�r Navigation�nderungen
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // Falls der Nutzer nicht eingeloggt ist wird er wieder zur�ck geleitet. Ausnahmen sind hier definiert
        var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
}