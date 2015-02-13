/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('InstructionConfigurationService',InstructionConfigurationServiceProvider);

    function InstructionConfigurationServiceProvider(){

        var me = this;
        me.InstructionConfigurationBaseUrl = 'rest/InstructionConfiguration/';
        me.$get = InstructionConfigurationService;

        InstructionConfigurationService.$inject = ["$http", "$q"];

        function InstructionConfigurationService($http, $q){
            return {
                getInstructionConfiguration : getInstructionConfiguration,
                saveInstructionConfiguration: saveInstructionConfiguration,
                editInstructionConfiguration: editInstructionConfiguration
            };
            function getInstructionConfiguration(){
                var url = me.InstructionConfigurationBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveInstructionConfiguration(data){
                var url = me.InstructionConfigurationBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editInstructionConfiguration(data,id){
                var url = me.InstructionConfigurationBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
