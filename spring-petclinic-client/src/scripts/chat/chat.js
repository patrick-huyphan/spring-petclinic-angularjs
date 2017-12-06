'use strict';

angular.module('chat', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('chat', {
                parent: 'app',
                url: '/chat',
                template: '<chat></chat>'
            })
    }]).factory('chatService', ['$http', '$q', 'urls', function ($http, $q, urls) {

            var factory = {
                connect: connect,
                disconnect: disconnect,
				sendName: sendName,
				showGreeting: showGreeting
            };

            return factory;

            function connect() {
				var socket = new WebSocket('ws://localhost:8080/greeting');
				ws = Stomp.over(socket);

				ws.connect({}, function(frame) {
					ws.subscribe("/user/queue/errors", function(message) {
						alert("Error " + message.body);
					});

					ws.subscribe("/user/queue/reply", function(message) {
						showGreeting(message.body);
					});
				}, function(error) {
					alert("STOMP error " + error);
				});
			}

			function disconnect() {
				if (ws != null) {
					ws.close();
				}
				setConnected(false);
				console.log("Disconnected");
			}

			function sendName() {
				ws.send($("#name").val());
			}

			function showGreeting(message) {
				$("#greetings").append(" " + message + "");
			}
        }
    ]);
	
	