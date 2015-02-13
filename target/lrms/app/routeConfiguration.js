/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';

    angular.module('VetechLawApp')
        .config([
            "$stateProvider",
            "$urlRouterProvider",
            routeConfig
        ])
        .config([
            //'ConfigurationServiceProvider',
            //'LoginServiceProvider',
            //'MenuServiceProvider',
            serviceConfig
        ]);



    function routeConfig($stateProvider,$urlRouterProvider){

        $urlRouterProvider.otherwise('/dash-board');
        $stateProvider.state('/case-classification', {
            url: '/case-classification',
            templateUrl: 'app/systemConfigModule/views/CaseClassification.html'
        });

        $stateProvider.state('/instruction-configuration', {
            url: '/instruction-configuration',
            templateUrl: 'app/systemConfigModule/views/InstructionConfiguration.html'
        });

        $stateProvider.state('/job-title', {
            url: '/job-title',
            templateUrl: 'app/systemConfigModule/views/JobTitle.html'
        });

        $stateProvider.state('/identification-type', {
            url: '/identification-type',
            templateUrl: 'app/systemConfigModule/views/IdentificationTypes.html'
        });

        $stateProvider.state('/branches', {
            url: '/branches',
            templateUrl: 'app/systemConfigModule/views/Branch.html'
        });
        $stateProvider.state('/roles', {
            url: '/roles',
            templateUrl: 'app/systemConfigModule/views/Roles.html'
        });
        $stateProvider.state('/role-group', {
            url: '/role-group',
            templateUrl: 'app/systemConfigModule/views/RoleGroup.html'
        });
        $stateProvider.state('/client-type', {
            url: '/client-type',
            templateUrl: 'app/systemConfigModule/views/ClientType.html'
        });
        $stateProvider.state('/client-registration', {
            url: '/client-registration',
            templateUrl: 'app/clientModule/views/ClientRegistration.html'
        });


    }

    function serviceConfig(){
        console.log('services');
    }
})();
