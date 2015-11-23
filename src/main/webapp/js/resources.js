/**
 * The resources.
 * Created by Stephan on 10.12.2014.
 */
'use strict';

// Create the resources module.
var resources = angular.module('resources', []);

// Register a model object using the factory.
resources.factory('Ticket', function () {
	return function (username, description, headline, author, state, created) {
		return {
			'username': username,
			'description': description,
			'headline': headline,
			'author': author,
			'state': state,
			'created': created
		};
	};
});

resources.factory('Developer', function () {
	return function (username, name, email , password) {
		return {
			'username': username,
			'name': name,
			'email': email,
			'password': password
		};
	};
});
