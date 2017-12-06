'use strict';

angular.module('comments', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('comments', {
                parent: 'app',
                url: '/owners/:ownerId/pets/:petId/visits',
                template: '<comments></comments>'
            })
    }]);
