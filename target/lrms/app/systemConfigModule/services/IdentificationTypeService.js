/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('IdentificationTypeService',IdentificationTypeServiceProvider);

    function IdentificationTypeServiceProvider(){

        var me = this;
        me.IdentificationTypeBaseUrl = 'rest/IdentificationType/';
        me.$get = IdentificationTypeService;

        IdentificationTypeService.$inject = ["$http", "$q"];

        function IdentificationTypeService($http, $q){
            return {
                getIdentificationType : getIdentificationType,
                saveIdentificationType: saveIdentificationType,
                editIdentificationType: editIdentificationType
            };
            function getIdentificationType(){
                var url = me.IdentificationTypeBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveIdentificationType(data){
                var url = me.IdentificationTypeBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editIdentificationType(data,id){
                var url = me.IdentificationTypeBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
