'use strict';

angular.module('commentDetails')
    .controller('CommentDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;
		var petId = $stateParams.petId || 0;
        var url = "companies/" + ($stateParams.ownerId || 0) + "/comments";
        self.date = new Date();
        self.desc = "";
		
		$http.get('companies/' + $stateParams.comId).then(function (resp) {
            self.companyDetails = resp.data;
        });

				
        self.submit = function () {
            var data = {
                date: $filter('date')(self.date, "yyyy-MM-dd"),
                description: self.desc
            };

            $http.post(url, data).then(function () {
                $state.go('commentDetails', { ownerId: $stateParams.ownerId });
            });
        };
    }]);
