/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('ClientTypeService',ClientTypeServiceProvider);

    function ClientTypeServiceProvider(){

        var me = this;
        me.ClientTypeBaseUrl = 'rest/ClientType/';
        me.$get = ClientTypeService;

        ClientTypeService.$inject = ["$http", "$q"];

        function ClientTypeService($http, $q){
            return {
                getClientType : getClientType,
                saveClientType: saveClientType,
                editClientType: editClientType
            };
            function getClientType(){
                var url = me.ClientTypeBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveClientType(data){
                var url = me.ClientTypeBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editClientType(data,id){
                var url = me.ClientTypeBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
