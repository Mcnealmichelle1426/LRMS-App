/**
 * Created by kelly on 11/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('IdentificationTypeController',IdentificationTypeController);

    IdentificationTypeController.$inject = ['lodash','IdentificationTypeService'];

    function IdentificationTypeController(_,IdentificationTypeService){
        var me = this;
        me.identificationType = {};
        me.getAllIdentificationTypeController = getAllIdentificationTypeController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.identificationTypes = [];
        me.deactivateItem = deactivateItem;

        me.identificationTypes = [
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

        getAllIdentificationTypeController();

        function getAllIdentificationTypeController(){
            console.log(_.isArray(me.identificationTypes));
            //IdentificationTypeService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.IdentificationType);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.identificationType.id){
                IdentificationTypeService.editIdentificationType(me.identificationType, me.identificationType.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.identificationTypes,{id:me.identificationType.id});
                    if(index > -1){
                        _.extend(me.identificationTypes[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.identificationTypes.push(me.identificationType);
                IdentificationTypeService.saveIdentificationType(me.identificationType).then(function(data){
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
            me.identificationType =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.identificationTypes,{id:data.id});
            if(index > -1){
                me.identificationTypes.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.identificationType = {};
        }
    }
})();



