/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('ClientRegistrationService',ClientRegistrationServiceProvider);

    function ClientRegistrationServiceProvider(){

        var me = this;
        me.ClientRegistrationBaseUrl = 'rest/ClientRegistration/';
        me.$get = ClientRegistrationService;

        ClientRegistrationService.$inject = ["$http", "$q"];

        function ClientRegistrationService($http, $q){
            return {
                getClientRegistration : getClientRegistration,
                saveClientRegistration: saveClientRegistration,
                editClientRegistration: editClientRegistration
            };
            function getClientRegistration(){
                var url = me.ClientRegistrationBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveClientRegistration(data){
                var url = me.ClientRegistrationBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editClientRegistration(data,id){
                var url = me.ClientRegistrationBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
