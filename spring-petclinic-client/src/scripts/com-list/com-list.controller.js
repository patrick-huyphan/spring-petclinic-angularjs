'use strict';

angular.module('comList')
    .controller('ComListController', ['$http', function ($http) {
        var self = this;

        $http.get('companies/list').then(function (resp) {
            self.companies = resp.data;
        });
    }]);
