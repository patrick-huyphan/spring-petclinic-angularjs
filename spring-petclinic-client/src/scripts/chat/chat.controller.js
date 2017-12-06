'use strict';
angular.module('chat',[]).constant('urls', {
    DOC_URL: 'http://localhost:8080/doc/'
}).controller('UploadController',
    ['$scope', '$rootScope','chatService','$http', function($scope, $rootScope, chatService, $http) {

        $scope.file = '';

        $scope.upload = function(){
            var file = $scope.file;
            chatService.saveDoc(file)
                .then(
                    function (response) {
                        alert("file uploaded successfully.");
                        $http.get("http://localhost:8080/doc/").success(
                            function(response) {
                                $rootScope.docList = response;
                            });
                    },
                    function (errResponse) {

                    }
                );
        }
    }
    ]).directive('fileModel', [ '$parse', function($parse) {
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
} ]).run(function($rootScope, $http) {
    $http.get("http://localhost:8080/doc/").success(
        function(response) {
            $rootScope.docList = response;
        });
});