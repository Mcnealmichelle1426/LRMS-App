/**
 * Created by kelly on 10/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('ClientRegistrationController',ClientRegistrationController);

    ClientRegistrationController.$inject = ['lodash','ClientRegistrationService'];

    function ClientRegistrationController(_,ClientRegistrationService){
        var me = this;
        me.clientRegistration = {};
        me.getAllClientRegistrationController = getAllClientRegistrationController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.clientRegistrations = [];
        me.deactivateItem = deactivateItem;

        me.clientRegistrations = [
            {
                id:1,
                active:true,
                code:'code 1',
                name:"name 1",
                description:'description',
                charge: '2000'
            },{
                id:2,
                active:false,
                code:'code 2',
                name:"name 2",
                description:'description',
                charge: '2000'
            },{
                id:3,
                active:false,
                code:'code 3',
                name:"name 3",
                description:'description',
                charge: '2000'
            },{
                id:4,
                active:true,
                code:'code 4',
                name:"name 4",
                description:'description',
                charge: '2000'
            },{
                id:5,
                active:true,
                code:'code 5',
                name:"name 5",
                description:'description',
                charge: '2000'
            }
        ];

        getAllClientRegistrationController();

        function getAllClientRegistrationController(){
            console.log(_.isArray(me.clientRegistrations));
            //ClientRegistrationService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.clientRegistration);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.clientRegistration.id){
                ClientRegistrationService.editClientRegistration(me.clientRegistration, me.clientRegistration.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.clientRegistrations,{id:me.clientRegistration.id});
                    if(index > -1){
                        _.extend(me.clientRegistrations[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.clientRegistrations.push(me.clientRegistration);
                ClientRegistrationService.saveClientRegistration(me.clientRegistration).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }
        }
        function editData(data){
            me.clientRegistration =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.clientRegistrations,{id:data.id});
            if(index > -1){
                me.clientRegistrations.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.clientRegistration = {};
        }
    }
})();


