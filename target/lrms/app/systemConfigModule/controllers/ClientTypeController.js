/**
 * Created by kelly on 11/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('ClientTypeController',ClientTypeController);

    ClientTypeController.$inject = ['lodash','ClientTypeService'];

    function ClientTypeController(_,ClientTypeService){
        var me = this;
        me.clientType = {};
        me.getAllClientTypeController = getAllClientTypeController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.clientTypes = [];
        me.deactivateItem = deactivateItem;

        me.clientTypes = [
            {
                id:1,
                active:true,
                code:'code 1',
                name:"name 1",
                description:'description'
            },{
                id:2,
                active:false,
                code:'code 2',
                name:"name 2",
                description:'description'
            },{
                id:3,
                active:false,
                code:'code 3',
                name:"name 3",
                description:'description'
            },{
                id:4,
                active:true,
                code:'code 4',
                name:"name 4",
                description:'description'
            },{
                id:5,
                active:true,
                code:'code 5',
                name:"name 5",
                description:'description'
            }
        ];

        getAllClientTypeController();

        function getAllClientTypeController(){
            console.log(_.isArray(me.clientTypes));
            //ClientTypeService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.ClientType);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.clientType.id){
                ClientTypeService.editClientType(me.clientType, me.clientType.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.clientTypes,{id:me.clientType.id});
                    if(index > -1){
                        _.extend(me.clientTypes[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.clientTypes.push(me.clientType);
                ClientTypeService.saveClientType(me.clientType).then(function(data){
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
            me.clientType =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.clientTypes,{id:data.id});
            if(index > -1){
                me.clientTypes.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.clientType = {};
        }
    }
})();



