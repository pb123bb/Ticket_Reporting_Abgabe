/**
 * The controllers.
 * Created by Stephan on 10.12.2014.
 */
'use strict';

// Create the module for the controllers. Depends on the resources module.
var controllers = angular.module('controllers', ['services', 'resources']);

controllers.controller('ticketController', ['$scope', 'Ticket', 'ticketService', '$rootScope', function ($scope, Ticket, ticketService, $rootScope) {

    // Create scope model
    $scope.model = {
        tickets: []
    };

    // Load all tickets
    ticketService.listAllTickets(function (response) {
        if (response.success) {
            $scope.model.tickets = data;
        } else {
        }
    });

    this.createTicket = function() {
        var ticket = $scope.model.ticket;
        ticket.username = $rootScope.globals.currentUser.username;
        ticketService.createTicket(ticket, function (response) {
            if (response.success) {
                $scope.model.error = "Ticket was created.";
            } else {
                $scope.model.error = "Ticket couldn't be created";
            }
        });
    }
}]);

controllers.controller('developerController', ['$scope', 'Developer', 'developerService', '$location', function ($scope, Developer, developerService, $location) {

    // Create scope model
    $scope.model = {
        Developer: Developer
    };

    this.createDeveloper = function () {
        var developer = $scope.model.developer;
        developerService.createDeveloper(developer)
            .success(function (data) {
                $scope.model.error = "Register successful.";
            })
            .error(function (error) {
                $scope.model.error = "Register was not successful.";
            });
    };

    developerService.clearCredentials();

    this.loginDeveloper = function () {
        developerService.loginDeveloper($scope.model.developer.username, $scope.model.developer.password, function (response) {
            if (response.success) {
                developerService.setCredentials($scope.model.developer.username, $scope.model.developer.password);
                $location.path('/ticketAll');
            } else {
                $scope.model.error = response.message;
            }
        });
    }

}]);

