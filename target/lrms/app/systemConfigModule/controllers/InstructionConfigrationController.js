/**
 * Created by kelly on 10/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('InstructionConfigurationController',InstructionConfigurationController);

    InstructionConfigurationController.$inject = ['lodash','InstructionConfigurationService'];

    function InstructionConfigurationController(_,InstructionConfigurationService){
        var me = this;
        me.instructionConfiguration = {};
        me.getAllInstructionConfigurationController = getAllInstructionConfigurationController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.instructionConfigurations = [];
        me.deactivateItem = deactivateItem;

        me.instructionConfigurations = [
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

        getAllInstructionConfigurationController();

        function getAllInstructionConfigurationController(){
            console.log(_.isArray(me.instructionConfigurations));
            //InstructionConfigurationService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.instructionConfiguration);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.instructionConfiguration.id){
                InstructionConfigurationService.editInstructionConfiguration(me.instructionConfiguration, me.instructionConfiguration.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.instructionConfigurations,{id:me.instructionConfiguration.id});
                    if(index > -1){
                        _.extend(me.instructionConfigurations[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.instructionConfigurations.push(me.instructionConfiguration);
                InstructionConfigurationService.saveInstructionConfiguration(me.instructionConfiguration).then(function(data){
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
            me.instructionConfiguration =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.instructionConfigurations,{id:data.id});
            if(index > -1){
                me.instructionConfigurations.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.instructionConfiguration = {};
        }
    }
})();


