'use strict';

angular.module('comDetails')
    .controller('ComDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;

        $http.get('companies/' + $stateParams.comId).then(function (resp) {
            self.companyDetails = resp.data;
        });
    }]);
