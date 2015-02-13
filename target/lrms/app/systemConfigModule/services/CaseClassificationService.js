/**
 * Created by kelly on 10/02/2015.
 */
/**
 * Created by kelly on 09/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('CaseClassificationService',CaseClassificationServiceProvider);

    function CaseClassificationServiceProvider(){

        var me = this;
        me.CaseClassificationBaseUrl = 'rest/caseClass/';
        me.$get = CaseClassificationService;

        CaseClassificationService.$inject = ["$http", "$q"];

        function CaseClassificationService($http, $q){
            return {
                getCaseClassification : getCaseClassification,
                saveCaseClassification: saveCaseClassification,
                editCaseClassification: editCaseClassification
            };
            function getCaseClassification(){
                var url = me.CaseClassificationBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveCaseClassification(data){
                var url = me.CaseClassificationBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editCaseClassification(data,id){
                var url = me.CaseClassificationBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();


